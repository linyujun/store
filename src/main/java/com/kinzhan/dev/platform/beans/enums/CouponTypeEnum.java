package com.kinzhan.dev.platform.beans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponTypeEnum {

    ALL("ALL","无门槛"),
    FULL("FULL","满减"),
    DISCOUNT("DISCOUNT", "折扣");

    private String value;

    private String desc;

    public static CouponTypeEnum toEnum(int value) {
        return Stream.of(CouponTypeEnum.values())
                .filter(p -> p.value.equals(value))
                .findAny()
                .orElse(null);
    }
}
