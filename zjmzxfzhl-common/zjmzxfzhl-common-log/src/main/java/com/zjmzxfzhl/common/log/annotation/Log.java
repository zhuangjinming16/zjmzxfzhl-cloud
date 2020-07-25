package com.zjmzxfzhl.common.log.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * 
 * @author 庄金明
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 日志内容
     * 
     * @return
     */
    String value() default "";
}
