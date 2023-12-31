package com.lfw.ioc.utils;

import com.lfw.ioc.context.BeanDefinition;

import java.lang.reflect.Field;

/*
 * @Author Zzs
 * @Description 处理所有在bean的属性上的注解，可以理解为这些注解的处理方法在这里注册
 * @DateTime 2023/9/23 17:35
 */
public interface AnnotatedFieldsHandler {
	
	default void handleFields (Class<?> c, BeanDefinition beanDefinition) throws Exception {
		SetterHelper.beanDefinition = beanDefinition;
		Field[] declaredFields = c.getDeclaredFields();
		for (Field f : declaredFields) {
			f.setAccessible(true);
			if (f.getAnnotations().length == 0)
				continue;
			SetterHelper.field = f;
			value();
			autowired();
		}
	}
	
	void value ();
	
	void autowired ();
	
	class SetterHelper {
		public static BeanDefinition beanDefinition;
		public static Field field;
	}
}
