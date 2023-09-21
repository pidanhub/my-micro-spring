package com.lfw.ioc.factory;

import com.lfw.ioc.context.AnnotatedBeanDefinition;
import com.lfw.ioc.context.BeanDefinition;
import com.lfw.ioc.exception.NoSuchDefiniteWayException;

/*
 * @Author Zzs
 * @Description 根据配置文件创建相应的工厂
 * @DateTime 2023/9/21 21:39
 */
public class BeanDefinitionFactory {
	
	public static final String WAY_ANNOTATION = "annotation";
	public static final String WAY_XML = "XML";
	
	public static BeanDefinition getBeanDefinition (String way) throws Exception {
		switch (way) {
			case WAY_ANNOTATION:
				return new AnnotatedBeanDefinition();
			case WAY_XML:
				// Not implement yet.
//				return new XMLBeanDefinition();
			default:
				throw new NoSuchDefiniteWayException("check your configuration file");
		}
	}
}
