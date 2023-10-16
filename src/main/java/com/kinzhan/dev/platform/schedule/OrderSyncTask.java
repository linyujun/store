package com.kinzhan.dev.platform.schedule;

import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.kinzhan.dev.platform.beans.entity.OrderQueueEntity;
import com.kinzhan.dev.platform.beans.entity.StoreOrderEntity;
import com.kinzhan.dev.platform.service.OrderQueueService;
import com.kinzhan.dev.platform.service.OrderService;
import com.kinzhan.dev.platform.service.StoreOrderService;
import com.kinzhan.dev.platform.service.WxOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                    wxOrderService.syncOrder(orderResult);
                }
            });
        }
    }

}
