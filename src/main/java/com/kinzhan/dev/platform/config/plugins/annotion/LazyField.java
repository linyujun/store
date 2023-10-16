package com.kinzhan.dev.platform.config.plugins.annotion;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LazyField {

    /**
     * 要翻译的字段名称
     */
    String from();

    /**
     * 翻译到哪个字段.默认为from()+Name,可以自定义
     */
    String to() default "";

    /**
     * 对应的字典KEY
     * @return
     */
    String key() default "";

    /**
     * 对应得mapper方法
     * @return
     */
    String mapper() default "";

    /**
     * 是否多选
     * @return
     */
    String multiple() default "false";
}
