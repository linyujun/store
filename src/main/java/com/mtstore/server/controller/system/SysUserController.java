package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.ResetPwdDto;
import com.mtstore.server.beans.dto.user.ResetUserDto;
import com.mtstore.server.beans.dto.user.SysUserInfoDto;
import com.mtstore.server.beans.entity.sys.SysUserEntity;
import com.mtstore.server.service.SysPermissionService;
import com.mtstore.server.service.SysRoleService;
import com.mtstore.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping({ "/api/sys/user" })
@RequiredArgsConstructor
public class SysUserController {
    public final String DEFAULT_PWD = "mt123456";

    private final SysUserService sysUserService;

    private final SysPermissionService sysPermissionService;

    private final SysRoleService sysRoleService;

    @RequestMapping({ "/current", "/userInfo" })
    public Object currentUser() {
        try {
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            SysUserEntity sysUserEntity = sysUserService.findCurrentUser();
            Optional.ofNullable(sysUserEntity)
                    .ifPresent(this::addPermissionInfo);

            return R.ok("成功", sysUserEntity);
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 获取权限信息
     * @param entity
     */
    private void addPermissionInfo(SysUserEntity entity) {
        Optional.ofNullable(entity).ifPresent(e -> {
            List<String> roleList = new ArrayList<>();
            List<String> permissionList = sysPermissionService.findPermissionListByRoleId(entity.getRoleId());
            if (null != entity.getDataScope() && "AGENT".equals(entity.getDataScope())) {
                roleList.add("COMPANY");
            } else if (null != entity.getDataScope() && "STORE".equals(entity.getDataScope())) {
                roleList.add("COMPANY");
            } else {
                roleList.add(sysRoleService.getById(entity.getRoleId()).getRoleName());
            }

            entity.setRoles(roleList);

            entity.setPermissions(permissionList);
        });
    }

    @PostMapping
    public Object save(@RequestBody @Validated SysUserInfoDto dto) {
        log.info("保存系统用户信息 {}", dto);
        SysUserEntity entity;
        if (null != dto.getId()) {
            entity = sysUserService.findById(dto.getId());
            if (!dto.getPhone().equals(entity.getPhone())) {
                Optional.ofNullable(sysUserService.findByPhone(dto.getPhone())).ifPresent(e -> {
                    throw new RuntimeException("手机号已被注册，请更换~");
                });
            }

            if (!dto.getUserName().equals(entity.getUserName())) {
                Optional.ofNullable(sysUserService.findByUserName(dto.getUserName())).ifPresent(e -> {
                    throw new RuntimeException("用户名已被注册，请更换~");
                });
            }

        } else {
            Optional.ofNullable(sysUserService.findByPhone(dto.getPhone())).ifPresent(e -> {
                throw new RuntimeException("手机号已被注册，请更换~");
            });

            Optional.ofNullable(sysUserService.findByUserName(dto.getUserName())).ifPresent(e -> {
                throw new RuntimeException("用户名已被注册，请更换~");
            });

            entity = new SysUserEntity();
        }
        BeanUtils.copyProperties(dto, entity);
        String password = null != dto.getPassword() ? dto.getPassword(): DEFAULT_PWD;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        entity.setPassword(hashed);
        sysUserService.saveOrUpdate(entity);
        log.info("保存系统用户信息成功 {}", entity);

        return R.ok("保存成功", entity);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", sysUserService.getPageList(pageDto, null));
    }

    @GetMapping("{id}")
    @ResponseBody
    public Object findOne(@PathVariable("id") Integer id) {

        return R.ok("获取成功", sysUserService.getById(id));
    }

    @GetMapping("/valid/{phone}")
    @ResponseBody
    public Object findByPhone(@PathVariable("phone") String phone) {

        return R.ok("获取成功", sysUserService.findByPhone(phone));
    }

    @GetMapping("/roleUser/{role}")
    @ResponseBody
    public Object findAllByRole(@PathVariable("role") String role) {

        return R.ok("获取成功", sysUserService.findAllByRole(role));
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Object deleteOne(@PathVariable("id") Integer id) {
        log.info("删除系统用户： " + id);
        if (LoggedUser.get().getUserId().equals(id)) {
            throw new RuntimeException("不可以删除自己~");
        }
        sysUserService.forceDeleteById(id);
        return R.ok("操作成功");
    }

    /**
     * 修改当前密码
     * @return
     */
    @PostMapping("/resetPwd")
    public Object resetPwd(@RequestBody @Validated ResetPwdDto resetPwdDto) {
        log.info("修改系统用户密码 {}", resetPwdDto);
        if (!resetPwdDto.getConfirmPwd().equals(resetPwdDto.getNewPwd())) {
            throw new RuntimeException("新密码和确认密码不一致！");
        }
        if (null != resetPwdDto.getPassword()) {
            sysUserService.resetPwd(resetPwdDto.getPassword(), resetPwdDto.getNewPwd());
        } else {
            sysUserService.resetPwd(LoggedUser.get().getUserId(), resetPwdDto.getNewPwd());
        }

        return R.ok("修改成功！");
    }

    /**
     * 重置用户密码
     * @return
     */
    @PostMapping("/resetUser")
    public Object resetUser(@RequestBody @Validated ResetUserDto userDto) {
        sysUserService.resetPwd(userDto.getId(), null);
        log.info("重置系统用户密码 {}", userDto);
        return R.ok("密码已经重置，请及时登陆修改！");
    }

    /**
     * 搜索自动完成
     * @param name
     * @return
     */
    @GetMapping("/search/{name}")
    public Object search(@PathVariable("name") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (LoggedUser.get().getRole().equals("ADMIN")) {
            queryWrapper.eq("tenant_id", 0);
        } else {
            queryWrapper.eq("tenant_id", LoggedUser.get().getTenantId());
        }
        queryWrapper.like("nick_name", name);

        return R.ok("获取成功", sysUserService.list(queryWrapper));
    }


    /**
     * 全部
     * @return
     */
    @GetMapping("/getAll")
    public Object getAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (LoggedUser.get().getRole().equals("ADMIN")) {
            queryWrapper.eq("tenant_id", 0);
        } else {
            queryWrapper.eq("tenant_id", LoggedUser.get().getTenantId());
        }

        return R.ok("获取成功", sysUserService.list(queryWrapper));
    }
}
