package com.lfw.ioc.annotation;

import java.lang.annotation.*;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 16:57
 */
@Target ({ ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention (RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings ({ "unused" })
public @interface ZValue {
	String value () default "";
}
