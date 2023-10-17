package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.user.SystemRechargeDto;
import com.mtstore.server.beans.dto.user.UserInfoDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.service.UserBillService;
import com.mtstore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2021-11-23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    final UserService service;
    final UserBillService userBillService;

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        UserEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody UserInfoDto dto){
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
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

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }

    /**
     * 管理员操作余额
     * @param dto
     * @return
     */
    @PostMapping("/recharge")
    public Object recharge(@RequestBody @Validated SystemRechargeDto dto) {
        if (dto.getAction().equals("add")) {
            userBillService.income(dto.getUid(), BillEnum.BILL_ACTION_SYSTEM_USER, dto.getBizType(), dto.getAmount(), dto.getDescription());
        }
        if (dto.getAction().equals("reduce")) {
            userBillService.expand(dto.getUid(), BillEnum.BILL_ACTION_SYSTEM_USER, dto.getBizType(), dto.getAmount(), dto.getDescription());
        }
        if (dto.getAction().equals("total")) {
            userBillService.last(dto.getUid(), BillEnum.BILL_ACTION_SYSTEM_USER, dto.getBizType(), dto.getAmount(), dto.getDescription());
        }

        return R.ok("操作成功",true);
    }
}
