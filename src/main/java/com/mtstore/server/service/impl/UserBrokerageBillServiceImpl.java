package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.user.UserBrokerageBillDto;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.service.UserService;
import com.mtstore.server.beans.entity.UserBrokerageBillEntity;
import com.mtstore.server.beans.mapper.UserBrokerageBillMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.UserBrokerageBillService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-19
*/
@Service
@RequiredArgsConstructor
public class UserBrokerageBillServiceImpl extends ServiceImpl<UserBrokerageBillMapper, UserBrokerageBillEntity> implements UserBrokerageBillService {

    final UserService userService;

    /**
     * 收入
     * @param uid
     * @param action
     * @param amount
     * @param description
     */
    @Override
    @Transactional
    public void income(Integer uid, String action, BigDecimal amount, String description) {
        BigDecimal totalAmount = userService.incBrokerage(uid, amount);
        UserBrokerageBillDto dto = new UserBrokerageBillDto()
                .setAction(action)
                .setUid(uid)
                .setDirection(BillEnum.BILL_DIRECTION_INCR)
                .setTitle(BillEnum.BILL_ACTION_MAP.get(action))
                .setAmount(amount)
                .setTotalAmount(totalAmount)
                .setDescription(description);

        saveOrUpdate(dto);
    }

    /**
     * 支出
     * @param uid
     * @param action
     * @param amount
     * @param description
     */
    @Override
    @Transactional
    public void consume(Integer uid, String action, BigDecimal amount, String description) {
        BigDecimal totalAmount = userService.decBrokerage(uid, amount);
        UserBrokerageBillDto dto = new UserBrokerageBillDto()
                .setAction(action)
                .setUid(uid)
                .setDirection(BillEnum.BILL_DIRECTION_DECREASE)
                .setTitle(BillEnum.BILL_ACTION_MAP.get(action))
                .setAmount(amount)
                .setTotalAmount(totalAmount)
                .setDescription(description);

        saveOrUpdate(dto);
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public BigDecimal getWithdrawTotal() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(amount) as totalPrice");
        queryWrapper.eq("action", BillEnum.BILL_ACTION_WITHDRAW_BROKERAGE);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(UserBrokerageBillEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public BigDecimal getPendingTotal() {

        return null;
    }
}
