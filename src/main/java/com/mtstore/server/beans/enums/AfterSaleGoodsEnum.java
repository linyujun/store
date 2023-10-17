package com.mtstore.server.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AfterSaleGoodsEnum {

    ALREADY(1,"已收货"),
    UNRECEIVED(2, "未收货");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static AfterSaleGoodsEnum toEnum(int value) {
        return Stream.of(AfterSaleGoodsEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
