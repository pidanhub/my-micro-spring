package com.lfw.ioc.annotation;

import java.lang.annotation.*;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 13:56
 *
 */
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("unused")
public @interface ZController {
	String value() default "";
}
