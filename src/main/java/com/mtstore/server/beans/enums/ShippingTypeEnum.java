package com.mtstore.server.beans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ShippingTypeEnum {
    DELIVERY("DELIVERY", "物流快递"),
    PICKUP("PICKUP", "自提"),
    CITY_TAKE("CITY_TAKE", "同城配送");

    private String value;

    private String desc;

    public static ShippingTypeEnum toEnum(String value) {
        return Stream.of(ShippingTypeEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
