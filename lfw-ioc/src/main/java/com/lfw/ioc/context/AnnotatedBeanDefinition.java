package com.lfw.ioc.context;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 13:33
 */
@SuppressWarnings ("unused")
public class AnnotatedBeanDefinition implements BeanDefinition {
	
	protected String beanName;
	protected Class<?> aClass;
	
	protected final Map<String, Object> signedByValueMap = new HashMap<>();
	protected final Map<String, Object> signedByAutowiredMap = new HashMap<>();
	
	@Override
	public BeanDefinition setBeanName (String beanName) {
		this.beanName = beanName;
		return this;
	}
	
	@Override
	public BeanDefinition setClass (Class<?> aClass) {
		this.aClass = aClass;
		return this;
	}
	
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
	public void addingZValueMap (String fieldName, Object value) {
		signedByValueMap.put(fieldName, value);
	}
	
	// how?
	public void addingAutowiredMap (String fieldName, Object value) {
		signedByAutowiredMap.put(fieldName, value);
	}
	
	// 在IoC初始化bean的时候的关键信息，这是一个bean的所有内容
	public Map<String, Object> getSignedByValueMap () {
		return signedByValueMap;
	}
	
	public Map<String, Object> getSignedByAutowiredMap () {
		return signedByAutowiredMap;
	}
	
	@Override
	public void addIntoDefinitionMap (String fieldName, Object value) {
		if (value instanceof Class)
			addingAutowiredMap(fieldName, value);
		else
			addingZValueMap(fieldName, value);
	}
	
	@Override
	public Map<String, Object> getMap (String which) {
		switch (which) {
			case VALUE:
				return getSignedByValueMap();
			case AUTOWIRED:
				return getSignedByAutowiredMap();
			default:
				return null;
		}
	}
	
	@Override
	public String toString () {
		return "AnnotatedBeanDefinition{" +
				"signedByValueMap=" + signedByValueMap +
				", signedByAutowiredMap=" + signedByAutowiredMap +
				", beanName='" + beanName + '\'' +
				", aClass=" + aClass +
				'}';
	}
	
	@Override
	public String getBeanName () {
		return beanName;
	}
	
	@Override
	public Class<?> getBeanClass () {
		return aClass;
	}
}
