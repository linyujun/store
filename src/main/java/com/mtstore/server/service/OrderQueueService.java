package com.mtstore.server.service;

import com.mtstore.server.beans.dto.order.OrderQueueDto;
import com.mtstore.server.beans.entity.OrderQueueEntity;

import java.util.List;

/**
 * <p>
 *  订单队列
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
public interface OrderQueueService extends IKService<OrderQueueEntity, OrderQueueDto> {
    /**
     * 完成订单
     * @param orderId
     */
    void finishOrder(String orderId);

    /**
     * 通过订单查找队列
     * @param orderId
     * @return
     */
    OrderQueueEntity findByOrderId(String orderId);

    /**
     * 查询五分钟内的订单
     * @return
     */
    List<OrderQueueEntity> selectQueueInFiveMinute();

    /**
     * 查询五分钟以外的订单
     * @return
     */
    List<OrderQueueEntity> selectQueueAfterFiveMinute();

    /**
     * 通过订单号更新或写入
     * @param orderId
     */
    void saveOrUpdateByOrderId(String orderId);
}
