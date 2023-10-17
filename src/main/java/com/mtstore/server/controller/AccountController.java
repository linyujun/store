package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.annotion.AccessControl;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.AccountEntity;
import com.mtstore.server.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 收款账户表
 * </p>
 *
 * @author songsir
 * @since 2022-04-05
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    final AccountService service;

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        AccountEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    @AccessControl(roles = {"ADMIN"})
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", service.getPageList(pageDto, null));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    @AccessControl(roles = {"ADMIN"})
    public Object findAll() {
        QueryWrapper<AccountEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }
}
