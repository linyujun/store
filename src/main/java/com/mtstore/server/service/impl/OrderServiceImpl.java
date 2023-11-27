package com.mtstore.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.order.OrderTotalPriceVo;
import com.mtstore.server.beans.entity.OrderEntity;
import com.mtstore.server.beans.enums.PayBizEnum;
import com.mtstore.server.beans.mapper.OrderMapper;
import com.mtstore.server.service.OrderQueueService;
import com.mtstore.server.service.OrderService;
import com.mtstore.server.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author songsir
 * 用户端订单
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    final OrderQueueService orderQueueService;

    @Override
    public Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        Page page = new Page<OrderEntity>(pageDto.getPage(),pageDto.getSize());

        return baseMapper.getPage(page, wrapper);
    }

    /**
     * 写入订单队列和订单详情表
     * @param orderId
     * @param orderDto
     * @param orderResult
     */
    @Override
    @Transactional
    public void saveOrderInfo(String orderId,
                              BigDecimal total,
                              PayBizEnum bizType,
                              JSONObject orderDto,
                              WxPayMpOrderResult orderResult) {
        OrderEntity orderEntity = Optional.ofNullable(findByOrderId(orderId)).orElse(new OrderEntity());
        orderQueueService.saveOrUpdateByOrderId(orderId);
        orderEntity.setOrderId(orderId)
                .setPrepayId(orderResult.getPackageValue())
                .setOpenId(LoggedUser.get().getOpenId())
                .setTotal(total)
                .setBizType(bizType)
                .setExtra(JSONObject.toJSONString(orderDto));

        saveOrUpdate(orderEntity);
    }

    /**
     * 订单支付完成
     * @param orderId
     * @param transactionId
     */
    @Override
    public void orderPaySuccess(String orderId, String transactionId, String payTime) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.set("status", 1);
        updateWrapper.set("status_desc", "已支付");
        updateWrapper.set("transaction_id", transactionId);
        updateWrapper.set("pay_time", payTime);

        baseMapper.update(null, updateWrapper);
    }

    @Override
    public void orderPayTimeOut(String orderId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.set("status", 4);
        updateWrapper.set("status_desc", "过期");

        baseMapper.update(null, updateWrapper);
    }

    @Override
    public List getWeekData() {

        return baseMapper.getWeek();
    }

    @Override
    public BigDecimal getTodayTotal() {

        return Optional.ofNullable(baseMapper.getToday()).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getYesterdayTotal() {

        return Optional.ofNullable(baseMapper.getYesterday()).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getCurrentMonthTotal() {

        return baseMapper.getCurrentMonth();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = Optional.ofNullable(
                lambdaQuery().select(OrderEntity::getTotalPrice)
                    .eq(OrderEntity::getStatus, 1)
                    .one()
                )
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public Long getTotal() {

        return lambdaQuery()
                .eq(OrderEntity::getStatus, 1)
                .count();
    }

    @Override
    public BigDecimal getTotalRefundPrice() {
        BigDecimal totalPrice = Optional.ofNullable(
                lambdaQuery().select(OrderEntity::getTotalPrice)
                        .eq(OrderEntity::getStatus, 5)
                        .one()
        )
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public Long getTotalRefund() {

        return lambdaQuery()
                .eq(OrderEntity::getStatus, 5)
                .count();
    }


    @Override
    public Long rechargedTotal(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("biz_type",2);

        return count(queryWrapper);
    }

    @Override
    public BigDecimal averageTotal(LocalDateTime startTime, LocalDateTime endTime) {

        return baseMapper.getAveragePrice(startTime, endTime);
    }

    @Override
    public BigDecimal rechargedTotal() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total) as totalPrice");
        queryWrapper.eq("biz_type", PayBizEnum.RECHARGE);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    /**
     * 交易总额
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public BigDecimal orderTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total) as totalPrice");
        queryWrapper.between("create_time", startTime, endTime);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    /**
     * 商品支付总额
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public BigDecimal orderProductTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total) as totalPrice");
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("biz_type", PayBizEnum.STORE);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    /**
     * 充值总额
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public BigDecimal orderRechargedTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total) as totalPrice");
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("biz_type", PayBizEnum.RECHARGE);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    /**
     * 退款
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public BigDecimal orderRefundTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(total) as totalPrice");
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("status", 5);
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(OrderEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public Map<LocalDate, List<BigDecimal>> getLast30daysTotal() {
        Map<LocalDate, List<BigDecimal>> result = new LinkedHashMap();
        result.putAll(defaultLast30days());
        List<OrderTotalPriceVo> records = baseMapper.groupByLast30days();
        if (CollectionUtils.isNotEmpty(records)) {
            Map<LocalDate, List<BigDecimal>> existMap = new LinkedHashMap();
            records.stream().forEach(v -> {
                existMap.put(v.getDate(), Arrays.asList(
                        v.getTotalPrice(),
                        v.getProductPrice(),
                        v.getRechargePrice()
                ));
            });
            result.putAll(existMap);
        }

        return result;
    }


    private Map<LocalDate, List<BigDecimal>> defaultLast30days() {
        LocalDate today = LocalDate.now();
        Map<LocalDate, List<BigDecimal>> last30DaysList = new LinkedHashMap<>();
        for (int i = 30; i > 0; i--) {
            LocalDate date = today.minusDays(i);
            last30DaysList.put(date, Arrays.asList(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
        }

        return last30DaysList;
    }
}
