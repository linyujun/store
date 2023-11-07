package com.mtstore.server.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.OrderEntity;
import com.mtstore.server.beans.enums.PayBizEnum;
import org.springframework.beans.BeanUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author songsir
 * 用户端订单
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 简化添加和更新逻辑
     * @param dto
     * @return
     */
    @Transactional
    default boolean saveOrUpdate(OrderEntity dto){
        OrderEntity entity;
        if (null!= dto.getId() && dto.getId() > 0) {
            entity = getById(dto.getId());
            BeanUtils.copyProperties(dto, entity);
        } else {
            entity = new OrderEntity();
            BeanUtils.copyProperties(dto, entity);
        }

        return saveOrUpdate(entity);
    }

    /**
     * 修改支付订单金额
     * @param orderId
     * @param total
     */
    default void updateOrderTotal(String orderId, BigDecimal total) {
        lambdaUpdate()
                .eq(OrderEntity::getOrderId, orderId)
                .set(OrderEntity::getTotal, total)
                .update();
    }

    /**
     * 通过生成的订单ID查找订单信息
     * @param orderId
     * @return
     */
    default OrderEntity findByOrderId(String orderId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", orderId);

        return getBaseMapper().selectOne(queryWrapper);
    }

    /**
     * 订单退款
     * @param orderId
     */
    default void refundOrder(String orderId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.set("status", 5);
        updateWrapper.set("status_desc", "订单已退款");

        this.getBaseMapper().update(null, updateWrapper);
    }

    /**
     * 订单核销
     * @param orderId
     */
    default void confirmOrder(String orderId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.set("status", 3);
        updateWrapper.set("status_desc", "订单已核销");
        updateWrapper.set("confirm_time", LocalDateTime.now());

        this.getBaseMapper().update(null, updateWrapper);
    }

    /**
     * 配合前端二次封装分页查询
     * @param pageDto
     * @param wrapper
     * @return
     */
    Page getPageList(PageDto pageDto, QueryWrapper wrapper);

    /**
     * 订单入库操作
     * @param orderId
     * @param orderDto
     * @param orderResult
     */
    void saveOrderInfo(String orderId, BigDecimal total, PayBizEnum bizType, JSONObject orderDto, WxPayMpOrderResult orderResult);

    /**
     * 订单支付成功
     * @param orderId
     * @param transactionId
     */
    void orderPaySuccess(String orderId, String transactionId, String payTime);

    /**
     * 订单支付超时
     * @param orderId
     */
    void orderPayTimeOut(String orderId);

    /**
     * 统计最近7天数据
     * @return
     */
    List getWeekData();

    BigDecimal getTodayTotal();

    BigDecimal getYesterdayTotal();

    BigDecimal getCurrentMonthTotal();

    BigDecimal getTotalPrice();

    Long getTotal();

    BigDecimal getTotalRefundPrice();

    Long getTotalRefund();

    Long rechargedTotal(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal averageTotal(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal rechargedTotal();

    BigDecimal orderTotalPrice(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal orderProductTotalPrice(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal orderRechargedTotalPrice(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal orderRefundTotalPrice(LocalDateTime startTime, LocalDateTime endTime);

    Map<LocalDate, List<BigDecimal>> getLast30daysTotal();
}
