package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserBillDto;
import com.mtstore.server.beans.entity.UserBillEntity;
import com.mtstore.server.service.UserBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 用户账单，余额，积分等
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
        log.info(LoggedUser.get().getUserId() + " 更新用户账单，余额，积分 {}" , dto);
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
        log.info(LoggedUser.get().getUserId() + " 删除用户账单，余额，积分记录" + id);
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
