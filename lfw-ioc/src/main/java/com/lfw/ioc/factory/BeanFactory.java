package com.lfw.ioc.factory;

import com.lfw.ioc.context.BeanDefinition;

import java.util.Map;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/14 21:13
 */
public interface BeanFactory {
	
	String FACTORY_PREFIX = "&";
	
	Object getBean (Class<?> bean);
	
	Object getBean (String beanName);
	
	void loading (Map<Class<?>, BeanDefinition> beanDefinitionList) throws Exception;
}
