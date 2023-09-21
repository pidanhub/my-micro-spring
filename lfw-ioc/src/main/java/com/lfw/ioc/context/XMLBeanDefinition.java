package com.lfw.ioc.context;

import java.util.Map;

/*
 * @Author Zzs
 * @Description 没打算实现这种方式
 * @DateTime 2023/9/21 21:45
 */
public abstract class XMLBeanDefinition implements BeanDefinition {
	@Override
	public void setBeanName (String beanName) {
	
	}
	
	@Override
	public void setClass (Class<?> aClass) {
	
	}
	
	@Override
	public void addIntoDefinitionMap (String fieldName, Object value) {
	
	}
	
	@Override
	public Map<String, Object> getMap () {
		return null;
	}
}
