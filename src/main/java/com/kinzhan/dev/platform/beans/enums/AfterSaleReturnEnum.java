package com.kinzhan.dev.platform.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AfterSaleReturnEnum {

    PICKUP(1,"上门取件"),
    DELIVERY(2, "自行寄回");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static AfterSaleReturnEnum toEnum(int value) {
        return Stream.of(AfterSaleReturnEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
