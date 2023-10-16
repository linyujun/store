package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.user.UserBillDto;
import com.kinzhan.dev.platform.beans.entity.UserBillEntity;
import com.kinzhan.dev.platform.service.UserBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户账单，余额，积分等
 * </p>
 *
 * @author songsir
 * @since 2022-10-12
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/bill")
public class UserBillController {
    final UserBillService service;

    @PostMapping
    public Object save(@RequestBody @Validated UserBillDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        UserBillEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", service.getPageList(pageDto, null));
    }

    @PostMapping("/getCreditPageList")
    public Object getCreditPageList(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", service.getCreditPageList(pageDto, null));
    }

    @PostMapping("/getBalancePageList")
    public Object getBalancePageList(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", service.getBalancePageList(pageDto, null));
    }
}
