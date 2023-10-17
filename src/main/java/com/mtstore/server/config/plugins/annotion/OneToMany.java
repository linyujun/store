package com.mtstore.server.config.plugins.annotion;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OneToMany {

    /**
     * 要填充的字段名称
     */
    String from();

    /**
     * 要填充的目标对象
     */
    String to();

    /**
     * 对应得mapper方法
     * @return
     */
    String mapper() default "";

    /**
     * 对应的Mapper Class
     * @return
     */
    Class clazz() default void.class;

    /**
     * 默认方法
     * @return
     */
    String method() default "";
}
