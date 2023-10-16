package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.order.OrderStatusDto;
import com.kinzhan.dev.platform.beans.entity.StoreOrderStatusEntity;

import java.util.List;

/**
* @author songsir
* @date 2023-04-20
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
