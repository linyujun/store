package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.system.SysRoleDto;
import com.mtstore.server.beans.entity.sys.SysRoleEntity;
import com.mtstore.server.service.SysRoleService;
import com.mtstore.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping({ "/api/sys/role" })
@RequiredArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysUserService sysUserService;

    @PostMapping
    public Object save(@RequestBody @Validated SysRoleDto dto) {
        log.info("dto {}", dto);
        SysRoleEntity entity = new SysRoleEntity();
        BeanUtils.copyProperties(dto, entity);
        log.info("entity {}", entity);
        sysRoleService.saveOrUpdate(entity);

        return R.ok("保存成功", entity);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        if (LoggedUser.get().getRole().equals("SA")) {
            queryWrapper.lambda().eq(SysRoleEntity::getIsSystem, true);
        } else {
            queryWrapper.lambda().eq(SysRoleEntity::getIsSystem, false);
        }

        return R.ok("获取成功", sysRoleService.getPageList(pageDto, queryWrapper));
    }

    @GetMapping("{id}")
    @ResponseBody
    public Object findOne(@PathVariable("id") Integer id) {

        return R.ok("获取成功", sysRoleService.getById(id));
    }

    @GetMapping("getAll")
    @ResponseBody
    public Object getAll() {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        //TODO 获取
        if (!LoggedUser.get().getRole().equals("SA")) {
            queryWrapper.lambda().eq(SysRoleEntity::getIsHidden, false);
        }
        if (LoggedUser.get().getRole().equals("ADMIN")) {
            queryWrapper.lambda().eq(SysRoleEntity::getTenantId, 0);
        } else {
            queryWrapper.lambda().eq(SysRoleEntity::getTenantId, LoggedUser.get().getTenantId());
        }

        return R.ok("获取成功", sysRoleService.list(queryWrapper));
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Object deleteOne(@PathVariable("id") Integer id) {
        Optional.ofNullable(sysUserService.findAllByRoleId(id)).ifPresent(e -> {
            throw new RuntimeException("该角色下尚有用户，不可删除");
        });
        sysRoleService.removeById(id);
        return R.ok("操作成功");
    }
}
