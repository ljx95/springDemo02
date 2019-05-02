package com.ljx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: LinjianXiong
 * Date: 2019/4/26
 * Time: 19:55
 *
 * 自定义注解
 *
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// @interface 定义注解
public @interface AddAnnotation {

    // 手写Spring事务注解
    int userId() default 0;

    String userName() default "默认名称";

    String[] arrays();
}
