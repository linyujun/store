package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserBillDto;
import com.mtstore.server.beans.entity.UserBillEntity;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.beans.mapper.UserBillMapper;
import com.mtstore.server.service.SysPropertyService;
import com.mtstore.server.service.UserBillService;
import com.mtstore.server.service.UserService;
import com.mtstore.server.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * @author songsir
 * 账单记录
 */
@Service
@RequiredArgsConstructor
public class UserBillServiceImp extends ServiceImpl<UserBillMapper, UserBillEntity> implements UserBillService {

    final UserService userService;

    final SysPropertyService sysPropertyService;

    /**
     * 收入，积分或者余额
     */
    @Override
    @Transactional
    public void income(Integer uid, String action, String category, BigDecimal amount, String description) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (BillEnum.BILL_CATEGORY_BALANCE.equalsIgnoreCase(category)) {
            totalAmount = userService.incBalance(uid, amount);
        }
        if (BillEnum.BILL_CATEGORY_CREDIT.equalsIgnoreCase(category)) {
            totalAmount = userService.incCredit(uid, amount);
        }
        UserBillDto dto = new UserBillDto()
                .setAction(action)
                .setCategory(category)
                .setUid(uid)
                .setDirection(BillEnum.BILL_DIRECTION_INCR)
                .setTitle(BillEnum.BILL_ACTION_MAP.get(action))
                .setAmount(amount)
                .setTotalAmount(totalAmount)
                .setDescription(description);

        saveOrUpdate(dto);
    }

    /**
     * 购物或者签到送积分收入
     */
    @Override
    public void incomeCredit(Integer uid, String action) {
        String value = sysPropertyService.getValue(StringUtils.toCamelCase(action.toLowerCase()), "10");
        income(uid,
                action,
                BillEnum.BILL_CATEGORY_CREDIT,
                new BigDecimal(value),
                String.format("%s赠送%.0f积分", BillEnum.BILL_ACTION_MAP.get(action), BigDecimal.TEN));
    }

    /**
     * 消费，积分或者余额
     */
    @Override
    public void expand(Integer uid, String action, String category, BigDecimal amount, String description) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (BillEnum.BILL_CATEGORY_BALANCE.equalsIgnoreCase(category)) {
            totalAmount = userService.decBalance(uid, amount);
        }
        if (BillEnum.BILL_CATEGORY_CREDIT.equalsIgnoreCase(category)) {
            totalAmount = userService.decCredit(uid, amount);
        }
        UserBillDto dto = new UserBillDto()
                .setAction(action)
                .setCategory(category)
                .setUid(uid)
                .setDirection(BillEnum.BILL_DIRECTION_DECREASE)
                .setTitle(BillEnum.BILL_ACTION_MAP.get(action))
                .setAmount(amount)
                .setTotalAmount(totalAmount)
                .setDescription(description);

        saveOrUpdate(dto);
    }

    /**
     * 直接修改最终余额
     */
    @Override
    public void last(Integer uid, String action, String category, BigDecimal amount, String description) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (BillEnum.BILL_CATEGORY_BALANCE.equalsIgnoreCase(category)) {
            totalAmount = userService.setBalance(uid, amount);
        }
        if (BillEnum.BILL_CATEGORY_CREDIT.equalsIgnoreCase(category)) {
            totalAmount = userService.setCredit(uid, amount);
        }
        UserBillDto dto = new UserBillDto()
                .setAction(action)
                .setCategory(category)
                .setUid(uid)
                .setDirection(BillEnum.BILL_DIRECTION_LASt)
                .setTitle(BillEnum.BILL_ACTION_MAP.get(action))
                .setAmount(amount)
                .setTotalAmount(totalAmount)
                .setDescription(description);

        saveOrUpdate(dto);
    }

    /**
     * 积分明细分页
     */
    @Override
    public Page<UserBillEntity> getCreditPageList(PageDto dto, QueryWrapper wrapper) {
        QueryWrapper queryWrapper = Optional.ofNullable(wrapper).orElseGet(() -> new QueryWrapper());
        queryWrapper.eq("category", BillEnum.BILL_CATEGORY_CREDIT);

        return getPageList(dto, queryWrapper);
    }

    /**
     * 余额明细分页
     */
    @Override
    public Page<UserBillEntity> getBalancePageList(PageDto dto, QueryWrapper wrapper) {
        QueryWrapper queryWrapper = Optional.ofNullable(wrapper).orElseGet(() -> new QueryWrapper());
        queryWrapper.eq("category", BillEnum.BILL_CATEGORY_BALANCE);

        return getPageList(dto, queryWrapper);
    }


    /**
     * 查看某个动作的积分总额
     */
    @Override
    public BigDecimal getTotalByAction(String action) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("ifnull(sum(amount),0) as total");
        queryWrapper.eq("action", action);
        queryWrapper.eq("uid", LoggedUser.get().getUserId());
        Map<String, Object> result = getMap(queryWrapper);

        return new BigDecimal(String.valueOf(result.get("total")));
    }

    /**
     * 查看某个分类的积分总额
     */
    @Override
    public BigDecimal getTotalByCategory(String category) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("ifnull(sum(amount),0) as total");
        queryWrapper.eq("category", category);
        queryWrapper.eq("uid", LoggedUser.get().getUserId());
        Map<String, Object> result = getMap(queryWrapper);

        return new BigDecimal(String.valueOf(result.get("total")));
    }
}
