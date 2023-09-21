package com.lfw.ioc.annotation;

import com.lfw.ioc.context.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 13:33
 */
@SuppressWarnings ("unused")
public class AnnotatedBeanDefinition implements BeanDefinition {
	
	protected final Map<String, Object> signedByValueMap = new HashMap<>();
	protected final Map<String, Object> signedByAnnotationMap = new HashMap<>();
	protected String beanName;
	protected Class<?> aClass;
	
	public AnnotatedBeanDefinition () {
	}
	
	public AnnotatedBeanDefinition (String beanName, Class<?> aClass) {
		this.beanName = beanName;
		this.aClass = aClass;
	}
	
	/**
	 * 调用时，上层获取到被@ZValue注解标识的字段，并取出值，相应地存入definition中
	 *
	 * @param fieldName key
	 * @param value     value
	 */
	@Override
	public void addingValueMap (String fieldName, Object value) {
		signedByValueMap.put(fieldName, value);
	}
	
	// how?
	@Override
	public void addingAnnotationMap (String fieldName) {
		signedByAnnotationMap.put(fieldName, null);
	}
	
	// 在IoC初始化bean的时候的关键信息，这是一个bean的所有内容
	@Override
	public Map<String, Object> getSignedByValueMap () {
		return signedByValueMap;
	}
	
	@Override
	public Map<String, Object> getSignedByAnnotationMap () {
		return signedByAnnotationMap;
	}
	
}
