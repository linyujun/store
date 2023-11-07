package com.mtstore.server.schedule;

import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.mtstore.server.beans.entity.OrderQueueEntity;
import com.mtstore.server.schedule.event.order.OrderPaidEvent;
import com.mtstore.server.service.OrderQueueService;
import com.mtstore.server.service.OrderService;
import com.mtstore.server.service.WxOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 微信支付订单处理任务
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderSyncTask {
    final OrderQueueService orderQueueService;

    final OrderService orderService;

    final WxOrderService wxOrderService;



    /**
     * 主动同步订单支付状态
     * 2秒一次处理订单队列
     */
    public void syncQueueOrder() {
        List<OrderQueueEntity>  results = orderQueueService.selectQueueInFiveMinute();
        if (null != results && results.size() > 0) {
            results.parallelStream().forEach(entity -> {
                WxPayOrderQueryResult orderResult = wxOrderService.queryOrder(entity.getOrderId());
                if (null != orderResult) {
                    //查询一下
                    wxOrderService.syncOrder(orderResult);
                }
            });
        }
    }

}
