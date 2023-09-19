package com.lfw.ioc.annotation;

import java.lang.annotation.*;

/*
 * @Author Zzs
 * @Description 扫描包
 * @DateTime 2023/9/15 14:17
 *
 */
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("unused")
public @interface ZComponentScan {
	String value() default "";
	String[] basePackages() default {};
}
