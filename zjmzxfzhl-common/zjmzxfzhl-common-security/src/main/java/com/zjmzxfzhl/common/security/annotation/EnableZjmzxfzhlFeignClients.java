package com.zjmzxfzhl.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * @author 庄金明
 * @date 2020年7月18日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableZjmzxfzhlFeignClients {
	String[] value() default {};
	String[] basePackages() default { "com.zjmzxfzhl" };
	Class<?>[] basePackageClasses() default {};
	Class<?>[] defaultConfiguration() default {};
	Class<?>[] clients() default {};
}
