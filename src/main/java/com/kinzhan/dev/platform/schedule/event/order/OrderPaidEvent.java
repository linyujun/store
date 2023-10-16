package com.kinzhan.dev.platform.schedule.event.order;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderPaidEvent extends ApplicationEvent {

    private String orderId;
    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param orderId 自定义
     */
    public OrderPaidEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }
}

