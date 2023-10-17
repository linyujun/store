package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.withdraw.WithdrawCancelDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawConfirmDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawRejectDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.entity.WithdrawEntity;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.beans.mapper.WithdrawMapper;
import com.mtstore.server.service.SysPropertyService;
import com.mtstore.server.service.UserBillService;
import com.mtstore.server.service.UserService;
import com.mtstore.server.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 提现分润记录表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-09-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawServiceImp extends ServiceImpl<WithdrawMapper, WithdrawEntity> implements WithdrawService {

    final UserService userService;
    final UserBillService userBillService;
    final SysPropertyService propertyService;

    @Override
    public WithdrawEntity saveOrUpdate(WithdrawDto dto) {
        BigDecimal rebate = Optional.ofNullable(propertyService.getValue("rebate")).map(BigDecimal::new).orElse(BigDecimal.ZERO);
        UserEntity userEntity = userService.getById(LoggedUser.get().getUserId());
        WithdrawEntity entity = Optional.ofNullable(dto.getId()).map(this::getById).orElse(new WithdrawEntity());
        //TODO 判断是否有进行中的提现申请记录
        if (null == dto.getId() && isExist(LoggedUser.get().getUserId())) {
            throw new RuntimeException("有正在申请的提现记录，不可重复申请！");
        }
        //TODO 判断提现金额，是否小于余额
        if (userEntity.getBalance().compareTo(dto.getTotalPrice()) < 0) {
            throw new RuntimeException("余额不足，不可提现！");
        }
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(0);
        entity.setPayPrice(dto.getTotalPrice());
        if (rebate.compareTo(BigDecimal.ZERO) > 0) {
            //计算手续费
            BigDecimal payPrice = dto.getTotalPrice().subtract(dto.getTotalPrice().multiply(rebate).divide(BigDecimal.valueOf(100)));
            entity.setPayPrice(payPrice);
        }

        entity.setStatusDesc("等待审核");
        saveOrUpdate(entity);

        return entity;
    }

    /**
     * 检查用户是否有正在申请中的记录，一次只能发起一个提现申请
     * @return
     */
    private Boolean isExist(Integer uid) {
        return lambdaQuery().eq(WithdrawEntity::getUid, uid)
                .lt(WithdrawEntity::getStatus, 0).exists();
    }


    @Override
    public Boolean reject(WithdrawRejectDto dto) {
        WithdrawEntity entity = Optional.of(getById(dto.getId())).orElseThrow(() -> new RuntimeException("提现记录不存在！"));
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(1);
        entity.setStatusDesc("驳回");
        return saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public Boolean confirm(WithdrawConfirmDto dto) {
        WithdrawEntity entity = Optional.of(getById(dto.getId())).orElseThrow(() -> new RuntimeException("提现记录不存在！"));
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(2);
        entity.setStatusDesc("已打款");
        entity.setOrderTime(LocalDateTime.now());
        userBillService.expand(entity.getUid(), BillEnum.BILL_ACTION_WITHDRAW, BillEnum.BILL_CATEGORY_BALANCE, entity.getTotalPrice(), "用户申请提现");

        return saveOrUpdate(entity);
    }

    @Override
    public Boolean cancel(WithdrawCancelDto dto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", dto.getId());
        queryWrapper.eq("uid", LoggedUser.get().getUserId());
        WithdrawEntity entity = Optional.of(getOne(queryWrapper, false))
                .orElseThrow(() -> new RuntimeException("提现记录不存在！"));
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(-1);
        entity.setStatusDesc("已取消");

        return saveOrUpdate(entity);
    }

    @Override
    public BigDecimal expendTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total_price) as sumPrice");
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("status", 2);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(WithdrawEntity::getSumPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }
}
