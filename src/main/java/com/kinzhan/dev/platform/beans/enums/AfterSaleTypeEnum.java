package com.kinzhan.dev.platform.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AfterSaleTypeEnum {

    REFUND(1,"仅退款"),
    REFUND_GOODS(2, "退货退款"),
    FIX(3, "维修"),
    EXCHANGE(4, "换货");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static AfterSaleTypeEnum toEnum(int value) {
        return Stream.of(AfterSaleTypeEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
