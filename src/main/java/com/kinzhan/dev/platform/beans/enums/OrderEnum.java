package com.kinzhan.dev.platform.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderEnum {
    PENDING_PAYMENT(1,"待付款"),
    PENDING_DELIVERY(2,"待发货"),
    DELIVERED(3, "已发货"),
    CONFIRM_DELIVERY(4, "确认收货"),
    PENDING_WRITE_OFF(5, "待核销"),
    WRITE_OFF_COMPLETE(6, "核销完成"),
    PENDING_RATE(7, "待评价"),
    COMPLETE(8,"订单完成"),
    CANCELLED(-1,"取消订单"),
    AFTER_SALE(-4,"申请售后");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static OrderEnum toEnum(int value) {
        return Stream.of(OrderEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
