package com.kinzhan.dev.platform.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@ApiModel
public enum CartScopeEnum {

    GOODS(1,"普通商品"),
    SECKILL(2,"秒杀"),
    COMBINATION(3, "拼团"),
    BARGAIN(4, "砍价"),
    CREDIT(5, "积分"),
    JOIN_COMBINATION(6, "参团"),
    HELP_BARGAIN(7, "帮砍");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static CartScopeEnum toEnum(int value) {
        return Stream.of(CartScopeEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
