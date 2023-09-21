package com.lfw.ioc.annotation;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/14 22:32
 */

import com.lfw.ioc.context.BeanDefinitionReader;

import java.util.List;

@SuppressWarnings("unused")
public class AnnotatedBeanDefinitionReader implements BeanDefinitionReader {
	
	@Override
	public void reading (List<Class<?>> toBeLoading) {
	
	}
	
}
