package com.mtstore.server.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AfterSaleStatusEnum {

    PENDING(1,"等待商家处理"),
    PASS(2, "商家同意"),
    REJECTED(3, "商家驳回"),
    WAIT_USER_RETURN(4, "待买家退货"),
    MERCHANT_WAIT_RECEIPT(5, "待商家收货"),
    MERCHANT_REFUND_SUCCESS(6, "完成"),
    CLOSED(7, "售后关闭");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static AfterSaleStatusEnum toEnum(int value) {
        return Stream.of(AfterSaleStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
