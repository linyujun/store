package com.mtstore.server.beans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponScopeEnum {

    ALL("ALL","全场通用"),
    PRODUCT("PRODUCT","分类"),
    CATEGORY("CATEGORY", "商品");

    private String value;

    private String desc;

    public static CouponScopeEnum toEnum(int value) {
        return Stream.of(CouponScopeEnum.values())
                .filter(p -> p.value.equals(value))
                .findAny()
                .orElse(null);
    }
}
