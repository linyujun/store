package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.tenant.TenantDto;
import com.mtstore.server.beans.dto.user.UserRegisterDto;
import com.mtstore.server.beans.entity.sys.SysTenantEntity;
import com.mtstore.server.beans.entity.sys.SysUserEntity;
import com.mtstore.server.beans.mapper.TenantMapper;
import com.mtstore.server.service.SysUserService;
import com.mtstore.server.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 代理商表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-02-09
 */
@Service
@RequiredArgsConstructor
public class TenantServiceImp extends ServiceImpl<TenantMapper, SysTenantEntity> implements TenantService {

    final SysUserService sysUserService;

    @Override
    @Transactional
    public Boolean save(TenantDto dto){
        if (null != dto.getId()) {
            update(dto);
        } else {
            create(dto);
        }

        return true;
    }

    private void create(TenantDto dto) {
        SysUserEntity sysUserEntity = sysUserService.findByPhone(dto.getMobile());
        if (null != sysUserEntity) {
            throw new RuntimeException("该手机号已被占用，请更换其他手机号");
        }
        //注册系统用户
        SysTenantEntity sysTenantEntity = BeanUtil.copyProperties(dto, SysTenantEntity.class);
        saveOrUpdate(sysTenantEntity);
        UserRegisterDto userRegisterDto = BeanUtil.copyProperties(dto, UserRegisterDto.class, "id",  "isDelete",  "tenantId");
        userRegisterDto.setRole("COMPANY");
        userRegisterDto.setPhone(dto.getMobile());
        userRegisterDto.setNickName(dto.getShortName());
        userRegisterDto.setTenantId(sysTenantEntity.getId());
        sysUserService.create(userRegisterDto);
    }

    private void update(TenantDto dto) {
        SysTenantEntity entity = getById(dto.getId());
        Optional.ofNullable(entity).orElseThrow(() -> new RuntimeException("机构不存在，请确认"));
        SysUserEntity sysUserEntity = sysUserService.findByPhone(dto.getMobile());
        if (!dto.getMobile().equals(entity.getMobile())) {
            if(null != sysUserEntity){
                throw new RuntimeException("该手机号已被占用，请更换其他手机号");
            };
            //删除原先账户
            sysUserService.forceDeleteByPhone(entity.getMobile());
            //注册系统用户
            UserRegisterDto userRegisterDto = BeanUtil.copyProperties(dto, UserRegisterDto.class, "id",  "isDelete",  "tenantId");
            userRegisterDto.setRole("COMPANY");
            userRegisterDto.setPhone(dto.getMobile());

            sysUserEntity = sysUserService.create(userRegisterDto);
        }
        if (!dto.getPayType().equals(entity.getPayType())
                || !dto.getStartTime().equals(entity.getStartTime())
                || !dto.getEndTime().equals(entity.getEndTime())
        ) {
        }
        SysTenantEntity sysTenantEntity = BeanUtil.copyProperties(dto, SysTenantEntity.class);
        saveOrUpdate(sysTenantEntity);

        BeanUtil.copyProperties(dto, sysUserEntity, "id",  "isDelete",  "tenantId", "password", "userName");
        sysUserEntity.setTenantId(sysTenantEntity.getId());
        sysUserService.saveOrUpdate(sysUserEntity);
    }


    @Override
    public SysTenantEntity getCurrent() {

        return getById(LoggedUser.get().getTenantId());
    }
}
