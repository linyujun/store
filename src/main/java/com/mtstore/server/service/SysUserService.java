package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.SysUserInfoDto;
import com.mtstore.server.beans.dto.user.UserRegisterDto;
import com.mtstore.server.beans.entity.sys.SysRoleEntity;
import com.mtstore.server.beans.entity.sys.SysUserEntity;
import com.mtstore.server.beans.mapper.SysUserMapper;
import com.mtstore.server.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserService extends ServiceImpl<SysUserMapper, SysUserEntity>{

    public final String DEFAULT_PWD = "mt123456";

    private final SysUserMapper sysUserMapper;

    private final SysRoleService sysRoleService;


    public SysUserEntity findUserByAny(String needle) {

        return sysUserMapper.selectByAny(needle);
    }

    /**
     * 修改手机号
     * @param userId
     * @param phone
     */
    public void changePhone(Integer userId, String phone) {
        lambdaUpdate().eq(SysUserEntity::getId, userId).set(SysUserEntity::getPhone, phone).update();
    }

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    @Transactional
    public SysUserEntity create(UserRegisterDto userDto) {
        String password = null != userDto.getPassword() ? userDto.getPassword(): DEFAULT_PWD;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        if (null == userDto || null == userDto.getPhone()) {
            throw new RuntimeException("手机号必填");
        }
        SysRoleEntity roleEntity = sysRoleService.findByRoleName(userDto.getRole());
        SysUserEntity entity = Optional.ofNullable(sysUserMapper.selectByPhone(userDto.getPhone())).orElse(new SysUserEntity());
        if (null != entity.getId()) {
            throw new RuntimeException(String.format("手机号已注册成%s，请更换其他手机号~", null != entity.getRole() ?entity.getRole().getRoleName():""));
        }
        BeanUtils.copyProperties(userDto, entity);
        entity.setUserName(null != userDto.getUsername() ? userDto.getUsername(): userDto.getPhone());
        entity.setEnabled(true);
        entity.setPassword(hashed);
        entity.setRoleId(roleEntity.getId());
        saveOrUpdate(entity);

        return entity;
    }

    /**
     * 用户修改资料,支持修改自己的资料和上级修改下级资料
     * @param userDto
     * @return
     */
    public SysUserEntity save(SysUserInfoDto userDto, Boolean isParent) {
        Integer userId;
        if (isParent && null != userDto.getId()) {
            userId = userDto.getId();
        } else {
            userId = LoggedUser.get().getUserId();
        }
        SysUserEntity entity = findById(userId);
        if (null == entity) {
            throw new RuntimeException("未找到用户");
        }
        BeanUtils.copyProperties(userDto, entity);
        sysUserMapper.insert(entity);
        return entity;
    }

    public SysUserEntity update(SysUserEntity sysUserEntity) {
        sysUserMapper.updateById(sysUserEntity);

        return sysUserEntity;
    }

    /**
     * 获取所有用户分页数据
     * @param page
     * @param wrappers
     * @return
     */
    public Page<SysUserEntity> findAll(Page page, Wrappers wrappers) {

        return sysUserMapper.selectPage(page, null);
    }


    /**
     * 配合前端二次封装分页查询
     * @param pageDto
     * @param wrapper
     * @return
     */
    public Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        Page page = new Page<SysUserEntity>(pageDto.getPage(),pageDto.getSize());
        wrapper.eq("tenant_id", LoggedUser.get().getTenantId());

        return baseMapper.getPage(page, wrapper);
    }

    /**
     * 查找用户
     * @param userId
     * @return
     */
    public SysUserEntity findById(Integer userId) {

        return sysUserMapper.selectById(userId);
    }

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    public SysUserEntity findByPhone(String phone) {

        return sysUserMapper.selectByPhone(phone);
    }


    public SysUserEntity findByUserName(String userName) {

        return getOne(lambdaQuery().eq(SysUserEntity::getUserName, userName).getWrapper());
    }

    /**
     * 查找某个角色下的所有用户
     * @param role
     * @return
     */
    public List<SysUserEntity> findAllByRole(String role) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("enabled", 1);
        queryWrapper.inSql("role_id", String.format("select id from kz_sys_role where role_name ='%s'", role));

        return sysUserMapper.selectList(queryWrapper);
    }

    /**
     * 通过角色id查找用户
     * @param roleId
     * @return
     */
    public List<SysUserEntity> findAllByRoleId(Integer roleId) {
        return lambdaQuery()
                .eq(SysUserEntity::getRoleId, roleId)
                .eq(SysUserEntity::getIsDelete, 0).list();
    }

    /**
     * 查找当前用户
     * @return
     */
    public SysUserEntity findCurrentUser() {

        return getById(LoggedUser.get().getUserId());
    }

    /**
     * 校验密码
     * @param plaintext
     * @param hashed
     * @return
     */
    public boolean checkPwd(String plaintext, String hashed) {
        boolean flag = BCrypt.checkpw(plaintext, hashed);
        log.info("result {},{}, {}", plaintext, hashed, flag);

        return flag;
    }

    /**
     * 重置密码
     *
     * @return
     */
    public void resetPwd(String oldPwd, String newPwd) {
        SysUserEntity sysUserEntity = findById(LoggedUser.get().getUserId());
        if (null == sysUserEntity) {
            throw new RuntimeException("记录不存在");
        }
        if (!checkPwd(oldPwd, sysUserEntity.getPassword())) {
            throw new RuntimeException("原始密码不正确，请重试！");
        };
        String hashed = BCrypt.hashpw(newPwd, BCrypt.gensalt());
        sysUserEntity.setPassword(hashed);

        sysUserMapper.updateById(sysUserEntity);
    }

    /**
     * 强制重置密码
     */
    public void resetPwd(Integer userId, String newPassword) {
        String password = null != newPassword ? newPassword: DEFAULT_PWD;
        SysUserEntity sysUserEntity = findById(userId);
        if (null == sysUserEntity) {
            throw new RuntimeException("记录不存在");
        }
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        sysUserEntity.setPassword(hashed);

        sysUserMapper.updateById(sysUserEntity);
    }

    @Cacheable(cacheNames = "sysUsers", key = "#id")
    public String selectByCreateUser(Integer id) {
        return sysUserMapper.selectByCreateUser(id);
    }

    public void forceDeleteByPhone(String phone) {
        baseMapper.forceDeleteByPhone(phone);
    }

    public void forceDeleteById(Integer id) {
        baseMapper.forceDeleteById(id);
    }
}

