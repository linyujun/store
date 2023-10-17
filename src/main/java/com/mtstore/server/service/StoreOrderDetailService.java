package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.order.OrderDetailDto;
import com.mtstore.server.beans.entity.StoreOrderDetailEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
* @author songsir
* @date 2023-04-20
*/
public interface StoreOrderDetailService extends IKService<StoreOrderDetailEntity, OrderDetailDto>{


    List<StoreOrderDetailEntity> findAllByOrderId(String orderId);

    Map<LocalDate, Long> getLast30daysTotal();
}
