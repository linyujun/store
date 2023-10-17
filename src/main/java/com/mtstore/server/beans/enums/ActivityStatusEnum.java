package com.mtstore.server.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ActivityStatusEnum {

    PENDING(0,"未开始"),
    RUNNING(1,"进行中"),
    FINISHED(2, "已结束");

    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;

    public static ActivityStatusEnum toEnum(int value) {
        return Stream.of(ActivityStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }
}
