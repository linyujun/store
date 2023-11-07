package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreOrderStatusEntity;
import com.mtstore.server.beans.mapper.StoreOrderStatusMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreOrderStatusService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* @author songsir
* 订单状态
*/
@Service
@RequiredArgsConstructor
public class StoreOrderStatusServiceImpl extends ServiceImpl<StoreOrderStatusMapper, StoreOrderStatusEntity> implements StoreOrderStatusService {


    @Override
    public void create(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
              .setStatus(1)
              .setStatusDesc("创建订单");

        save(entity);
    }

    @Override
    public void paid(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(2)
                .setStatusDesc("支付成功");

        save(entity);
    }

    @Override
    public void send(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(3)
                .setStatusDesc("已发货");

        save(entity);
    }

    @Override
    public void receive(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(4)
                .setStatusDesc("确认收货");

        save(entity);
    }


    @Override
    public void verify(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(6)
                .setStatusDesc("订单核销");

        save(entity);
    }

    @Override
    public void commented(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(7)
                .setStatusDesc("用户点评");

        save(entity);
    }

    @Override
    public void finish(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(8)
                .setStatusDesc("订单完成");

        save(entity);
    }

    @Override
    public void close(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(-1)
                .setStatusDesc("订单关闭");

        save(entity);
    }

    @Override
    public void afterSales(String orderId) {
        StoreOrderStatusEntity entity = new StoreOrderStatusEntity();
        entity.setOrderId(orderId)
                .setStatus(-4)
                .setStatusDesc("订单售后中");

        save(entity);
    }

    @Override
    public List<StoreOrderStatusEntity> findAllByOrderId(String orderId) {
        return lambdaQuery()
                .orderByAsc(StoreOrderStatusEntity::getId)
                .eq(StoreOrderStatusEntity::getOrderId, orderId)
                .list();
    }

}
