package com.kinzhan.dev.platform.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PayBizEnum {

    STORE(1,"商城"),
    RECHARGE(2,"充值"),
    VIP(3, "开通VIP"),
    DIRECT(4, "当面付");

    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;

    public static PayBizEnum toEnum(int value) {
        return Stream.of(PayBizEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
