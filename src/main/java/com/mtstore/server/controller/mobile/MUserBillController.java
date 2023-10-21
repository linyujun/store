package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserBillQueryDto;
import com.mtstore.server.beans.dto.user.UserBrokerageCollectDto;
import com.mtstore.server.beans.entity.UserBillEntity;
import com.mtstore.server.beans.entity.UserBrokerageBillEntity;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.service.UserBillService;
import com.mtstore.server.service.UserBrokerageBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "移动端-用户账单")
@RequestMapping("/app/user/bill")
public class MUserBillController {
    final UserBillService service;
    final UserBrokerageBillService userBrokerageBillService;

    @ApiOperation(value = "用户积分记录分页")
    @PostMapping("/credit/pageList")
    public Object getCreditPageList(@RequestBody QueryDto<UserBillQueryDto> pageDto) {
        QueryWrapper<UserBillEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(UserBillEntity::getId);
        wrapper.lambda().eq(UserBillEntity::getUid, LoggedUser.get().getUserId());
        wrapper.lambda().eq(UserBillEntity::getCategory, BillEnum.BILL_CATEGORY_CREDIT);

        return R.ok("获取成功", service.pageList(pageDto, wrapper));
    }

    @ApiOperation(value = "用户余额记录分页")
    @PostMapping("/balance/pageList")
    public Object getBalancePageList(@RequestBody QueryDto<UserBillQueryDto> pageDto) {
        QueryWrapper<UserBillEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(UserBillEntity::getId);
        wrapper.lambda().eq(UserBillEntity::getUid, LoggedUser.get().getUserId());
        wrapper.lambda().eq(UserBillEntity::getCategory, BillEnum.BILL_CATEGORY_BALANCE);

        return R.ok("获取成功", service.pageList(pageDto, wrapper));
    }

    @ApiOperation(value = "用户佣金记录分页")
    @PostMapping("/brokerage/pageList")
    public Object getBrokeragePageList(@RequestBody QueryDto<UserBillQueryDto> pageDto) {
        QueryWrapper<UserBrokerageBillEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(UserBrokerageBillEntity::getId);
        wrapper.lambda().eq(UserBrokerageBillEntity::getUid, LoggedUser.get().getUserId());

        return R.ok("获取成功", userBrokerageBillService.pageList(pageDto, wrapper));
    }

    @ApiOperation(value = "佣金提取到余额")
    @PostMapping("/brokerage/collect")
    public Object collectBrokerage(@Validated @RequestBody UserBrokerageCollectDto dto) {
        final Integer uid = LoggedUser.get().getUserId();
        userBrokerageBillService.consume(uid, BillEnum.BILL_ACTION_WITHDRAW_BROKERAGE, dto.getTotal(), null);
        service.income(uid,
                BillEnum.BILL_ACTION_WITHDRAW_BROKERAGE,
                BillEnum.BILL_CATEGORY_BALANCE,
                dto.getTotal(),
                null);
        log.info(LoggedUser.get().getUserId() + " 佣金提取到余额： {}" , dto);
        return R.ok("操作成功", dto);
    }
}
