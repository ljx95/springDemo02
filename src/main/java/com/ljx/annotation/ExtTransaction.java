package com.ljx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: LinjianXiong
 * Date: 2019/4/26
 * Time: 20:07
 */
// 事务注解 设置传播行为
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtTransaction {
}
