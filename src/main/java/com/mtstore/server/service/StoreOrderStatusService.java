package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.order.OrderStatusDto;
import com.mtstore.server.beans.entity.StoreOrderStatusEntity;

import java.util.List;

/**
* @author songsir
* 订单状态
*/
public interface StoreOrderStatusService extends IKService<StoreOrderStatusEntity, OrderStatusDto>{

    void create(String orderId);

    void paid(String orderId);

    void send(String orderId);

    void receive(String orderId);

    void commented(String orderId);

    void finish(String orderId);

    void verify(String orderId);

    void close(String orderId);

    void afterSales(String orderId);

    List<StoreOrderStatusEntity> findAllByOrderId(String orderId);
}
