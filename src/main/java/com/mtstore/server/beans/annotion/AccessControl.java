package com.mtstore.server.beans.annotion;


import java.lang.annotation.*;

/**
 * 权限检查注解，用法 @AccessControl(roles = {"ADMIN"})
 * 只支持方法，如果需要支持类，支持的角色，ADMIN, RIDER, MERCHANT
 * @author songsir
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AccessControl {
    String[] roles() default {};
}
