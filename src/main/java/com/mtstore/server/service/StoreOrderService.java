package com.mtstore.server.service;

import com.mtstore.server.beans.dto.express.ExpressInfo;
import com.mtstore.server.beans.dto.mall.order.OrderDto;
import com.mtstore.server.beans.dto.mall.order.OrderDeliveryDto;
import com.mtstore.server.beans.dto.order.OrderStatusVo;
import com.mtstore.server.beans.dto.order.OrderCartDto;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.beans.enums.PayTypeEnum;
import com.mtstore.server.beans.vo.OrderTotalVo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author songsir
* 管理后台-订单，包含多个商品
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
