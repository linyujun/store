package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawConfirmDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawRejectDto;
import com.mtstore.server.beans.entity.WithdrawEntity;
import com.mtstore.server.service.UserService;
import com.mtstore.server.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 提现记录
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/withdraw")
public class WithdrawController {
    final WithdrawService service;

    final UserService userService;

    @PostMapping
    public Object save(@RequestBody @Validated WithdrawDto dto) {
        WithdrawEntity result = service.saveOrUpdate(dto);

        return R.ok("保存成功", result);
    }

    @PostMapping("/reject")
    public Object reject(@RequestBody @Validated WithdrawRejectDto dto) {
        Boolean result = service.reject(dto);

        return R.ok("操作成功", result);
    }

    @PostMapping("/audit")
    public Object audit(@RequestBody @Validated WithdrawConfirmDto dto) {
        Boolean result = service.confirm(dto);

        return R.ok("操作成功", result);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        WithdrawEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page<WithdrawEntity> page = service.getPageList(pageDto, queryWrapper);

        return R.ok("获取成功", page);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<WithdrawEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }
}
