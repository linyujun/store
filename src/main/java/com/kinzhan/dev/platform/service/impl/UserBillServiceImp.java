package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.user.UserBillDto;
import com.kinzhan.dev.platform.beans.entity.UserBillEntity;
import com.kinzhan.dev.platform.beans.enums.BillEnum;
import com.kinzhan.dev.platform.beans.mapper.UserBillMapper;
import com.kinzhan.dev.platform.service.SysPropertyService;
import com.kinzhan.dev.platform.service.UserBillService;
import com.kinzhan.dev.platform.service.UserService;
import com.kinzhan.dev.platform.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 账单记录 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-10-12
 */
@Service
@RequiredArgsConstructor
public class UserBillServiceImp extends ServiceImpl<UserBillMapper, UserBillEntity> implements UserBillService {

    final UserService userService;

    final SysPropertyService sysPropertyService;

    /**
     * 收入，积分或者余额
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
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
     * 积分收入
     * @param uid
     * @param action
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
     * 积分或者余额
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
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
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
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
     * @param dto
     * @param wrapper
     * @return
     */
    @Override
    public Page<UserBillEntity> getCreditPageList(PageDto dto, QueryWrapper wrapper) {
        QueryWrapper queryWrapper = Optional.ofNullable(wrapper).orElseGet(() -> new QueryWrapper());
        queryWrapper.eq("category", BillEnum.BILL_CATEGORY_CREDIT);

        return getPageList(dto, queryWrapper);
    }

    /**
     * 余额明细分页
     * @param dto
     * @param wrapper
     * @return
     */
    @Override
    public Page<UserBillEntity> getBalancePageList(PageDto dto, QueryWrapper wrapper) {
        QueryWrapper queryWrapper = Optional.ofNullable(wrapper).orElseGet(() -> new QueryWrapper());
        queryWrapper.eq("category", BillEnum.BILL_CATEGORY_BALANCE);

        return getPageList(dto, queryWrapper);
    }


    /**
     * 查看某个动作的积分总额
     * @param action
     * @return
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
     * @param category
     * @return
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
