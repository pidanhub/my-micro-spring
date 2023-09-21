package com.lfw.ioc.context;

import java.util.Map;

/*
 * @Author Zzs
 * @Description 定义bean的信息，包括字段信息
 * @DateTime 2023/9/15 13:28
 */
@SuppressWarnings ("unused")
public interface BeanDefinition {
	
	void addingValueMap (String fieldName, Object value);
	
	// how?
	void addingAnnotationMap (String fieldName);
	
	// 在IoC初始化bean的时候的关键信息，这是一个bean的所有内容
	Map<String, Object> getSignedByValueMap ();
	
	Map<String, Object> getSignedByAnnotationMap ();
}
