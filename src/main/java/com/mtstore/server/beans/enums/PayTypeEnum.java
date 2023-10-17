package com.mtstore.server.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PayTypeEnum {
    WEIXIN("WEIXIN", "微信支付"),
    ALIPAY("ALIPAY", "支付宝"),
    BALANCE("BALANCE", "余额支付");

    @EnumValue
    private String value;

    @JsonValue
    private String desc;

    public static PayTypeEnum toEnum(String value) {
        return Stream.of(PayTypeEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
