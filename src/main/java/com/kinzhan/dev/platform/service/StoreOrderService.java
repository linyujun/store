package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.express.ExpressInfo;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderDto;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderDeliveryDto;
import com.kinzhan.dev.platform.beans.dto.order.OrderStatusVo;
import com.kinzhan.dev.platform.beans.dto.order.OrderCartDto;
import com.kinzhan.dev.platform.beans.entity.StoreOrderEntity;
import com.kinzhan.dev.platform.beans.enums.PayTypeEnum;
import com.kinzhan.dev.platform.beans.vo.OrderTotalVo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author songsir
* @date 2023-04-20
*/
public interface StoreOrderService extends IKService<StoreOrderEntity, OrderDto>{

    StoreOrderEntity getByOrderId(String orderId);

    StoreOrderEntity findByOrderId(String orderId);

    StoreOrderEntity findByVerifyCode(String verifyCode);

    StoreOrderEntity createOrderByCart(OrderCartDto dto, PayTypeEnum payType);

    OrderTotalVo getOrderPrice(OrderCartDto dto);

    void paid(String orderId);

    void send(String orderId, OrderDeliveryDto dto);

    void receive(String orderId);

    void verify(String orderId);

    void comment(String orderId);

    void close(String orderId);

    void finish(String orderId);

    void refund(String orderId);

    void afterSales(String orderId);

    List<OrderStatusVo> findOrderStatusGroup(Integer userId);

    ExpressInfo getExpressInfo(String orderId);

    void initComments(String orderId);

    BigDecimal getTotalFee(Integer userId);

    Long getTotalPromoteOrder(Integer userId);

    List<Map<String, Object>> groupByStatus();

    Long orderTotal(String when);

    Integer orderTotal(LocalDateTime startTime, LocalDateTime endTime);

    Integer dealTotal(LocalDateTime startTime, LocalDateTime endTime);

    Map<String, List<Object>> groupByDate(String date);

    BigDecimal getSumPrice();

    /**
     * 余额支付金额
     * @return
     */
    BigDecimal balanceTotalPrice(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal brokerageTotalPrice(LocalDateTime startTime, LocalDateTime endTime);
}
