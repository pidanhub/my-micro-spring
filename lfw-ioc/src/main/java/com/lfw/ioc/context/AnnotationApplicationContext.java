package com.lfw.ioc.context;

import com.lfw.ioc.factory.BeanFactory;

/*
 * @Author Zzs
 * @Description 通过注解声明的bean对象
 * @DateTime 2023/9/15 13:51
 *
 */
@SuppressWarnings ("unused")
public class AnnotationApplicationContext implements BeanFactory {
	
	
	@Override
	public Object getBean (Class<?> beanName) {
		return null;
	}
}
