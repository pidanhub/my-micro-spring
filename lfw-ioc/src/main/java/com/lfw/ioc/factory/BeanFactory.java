package com.lfw.ioc.factory;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/14 21:13
 */
public interface BeanFactory {
	
	String FACTORY_PREFIX = "&";
	Object getBean (Class<?> beanName);
}
