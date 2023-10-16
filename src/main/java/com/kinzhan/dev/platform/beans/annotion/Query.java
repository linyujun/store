/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.beans.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Zheng Jie
 * @date 2019-6-4 13:52:30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    String propName() default "";

    Type type() default Type.EQUAL;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    String blurry() default "";

    /**
     * 自定义sql形式，如 "JSON_CONTAINS('sell_status','%s','$')"
     * @return
     */
    String template() default "";

    enum Type {
        EQUAL
        , GREATER_THAN
        , GREATER_THAN_NQ
        , LESS_THAN
        , INNER_LIKE
        , LEFT_LIKE
        , RIGHT_LIKE
        , LESS_THAN_NQ
        , IN
        ,NOT_EQUAL
        ,BETWEEN
        ,NOT_NULL
        ,UNIX_TIMESTAMP
        ,APPLY_SQL
        ,CUSTOM_MAPPER
    }

}

