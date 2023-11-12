package com.mtstore.server.controller.system;


import com.mtstore.server.beans.annotion.AccessControl;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.menu.SysMenuDto;
import com.mtstore.server.beans.entity.sys.SysMenuEntity;
import com.mtstore.server.service.SysMenuService;
import com.mtstore.server.util.tree.PermissionBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统菜单表
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/menu")
public class SysMenuController {
    final SysMenuService sysMenuService;

    @PostMapping
    public Object save(@RequestBody @Validated SysMenuDto dto) {
        Boolean result = sysMenuService.saveOrUpdate(dto);

        return R.ok("保存成功", result);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Object getOne(@PathVariable("id") Integer id) {
        SysMenuEntity entity = sysMenuService.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @AccessControl(roles = {"ADMIN"})
    public Object deleteOne(@PathVariable("id") Integer id) {
        return R.ok("操作成功", sysMenuService.removeById(id));
    }

    @GetMapping("/copy/{id}")
    public Object copyOne(@PathVariable("id") Integer id){
        sysMenuService.copyOne(id);

        return R.ok("操作成功", true);
    }


    @PostMapping("/getPageList")
    @ResponseBody
    @AccessControl(roles = {"ADMIN"})
    public Object getPageList(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", sysMenuService.getPageList(pageDto, null));
    }

    @GetMapping("/getAll")
    @ResponseBody
    @AccessControl(roles = {"ADMIN"})
    public Object getAll() {
        List<SysMenuEntity> menuList = sysMenuService.getList();

        return R.ok("获取成功", menuList);
    }

    @GetMapping("/getPermissions")
    @ResponseBody
    public Object getPermissions() {
        //TODO 如果不是超级管理员，分配的权限，小于等于自己
        Map data = new HashMap();
        List<SysMenuEntity> menuList = sysMenuService.findAllByUserId(LoggedUser.get().getUserId());
        List<PermissionBuilder.Node> nodes = new ArrayList();
        menuList.forEach(entity ->{
            PermissionBuilder.Node node = new PermissionBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            node.setLabel(entity.getTitle());
            nodes.add(node);
        });
        log.info("dict {}", nodes);
        List<PermissionBuilder.Node> result = new PermissionBuilder().buildTree(nodes);
        data.put("list", result);

        return R.ok("获取成功", result);
    }

    @GetMapping("/getTree")
    @ResponseBody
    public Object getTree() {
        Map data = new HashMap();
        data.put("list", sysMenuService.getTree());

        return R.ok("获取成功", data);
    }
}
