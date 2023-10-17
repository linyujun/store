package com.mtstore.server.controller.system;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.permission.PermissionDto;
import com.mtstore.server.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-04-02
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/permission")
public class SysPermissionController {
    final SysPermissionService sysPermissionService;

    @PostMapping
    public Object save(@RequestBody @Validated PermissionDto dto) {
        sysPermissionService.savePermissions(dto);

        return R.ok("保存成功", dto);
    }

    @GetMapping("{roleId}")
    public Object findByRole(@PathVariable("roleId") Integer roleId) {
        List permissions = sysPermissionService.findPermissionByRoleId(roleId);

        return R.ok("获取成功", permissions);
    }

    @GetMapping("current")
    public Object findCurrent() {
        List permissions = sysPermissionService.findMenuByCurrentUser();
        if (CollectionUtils.isEmpty(permissions)) {
            throw new RuntimeException("当前角色未分配权限，请在系统管理-角色管理中分配");
        }
        Map result = new HashMap();
        result.put("list", permissions);

        return R.ok("获取成功",  result);
    }
}
