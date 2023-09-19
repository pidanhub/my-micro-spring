package com.lfw.ioc.annotation;

import java.lang.annotation.*;

@Target ({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention (RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings({"unused"})
public @interface ZAutowired {

	/**
	 * Declares whether the annotated dependency is required.
	 * <p>Defaults to {@code true}.
	 */
	boolean required() default true;

}