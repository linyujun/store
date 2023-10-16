package com.kinzhan.dev.platform.schedule;

import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.kinzhan.dev.platform.beans.entity.OrderQueueEntity;
import com.kinzhan.dev.platform.beans.entity.StoreOrderEntity;
import com.kinzhan.dev.platform.beans.enums.OrderEnum;
import com.kinzhan.dev.platform.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商城订单处理
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StoreOrderTask {
    final OrderQueueService orderQueueService;

    final OrderService orderService;

    final WxOrderService wxOrderService;

    final StoreOrderService storeOrderService;

    final SysPropertyService propertyService;


    /**
     * 自动关闭系统未支付超时订单
     */
    @Transactional
    public void closeOrder() {
        Integer timeout = propertyService.getIntValue("autoClose");
        if (null != timeout && timeout > 0) {
            List<StoreOrderEntity> todoList = storeOrderService.lambdaQuery()
                    .eq(StoreOrderEntity::getIsPaid, false)
                    .eq(StoreOrderEntity::getStatus, OrderEnum.PENDING_PAYMENT)
                    .lt(StoreOrderEntity::getCreateTime, LocalDateTime.now().minusMinutes(timeout)).list();
            todoList.stream().forEach(entity -> {
                storeOrderService.close(entity.getOrderId());
            });
        }
    }
}
