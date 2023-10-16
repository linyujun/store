package com.kinzhan.dev.platform.controller;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.annotion.AccessControl;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawConfirmDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawRejectDto;
import com.kinzhan.dev.platform.beans.entity.WithdrawEntity;
import com.kinzhan.dev.platform.service.StudentService;
import com.kinzhan.dev.platform.service.UserService;
import com.kinzhan.dev.platform.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 提现分润记录表
 * </p>
 *
 * @author songsir
 * @since 2022-09-20
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
