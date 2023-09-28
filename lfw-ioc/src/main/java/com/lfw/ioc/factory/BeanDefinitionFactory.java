package com.lfw.ioc.factory;

import com.lfw.ioc.context.*;
import com.lfw.ioc.exception.NoSuchDefiniteWayException;

/*
 * @Author Zzs
 * @Description 根据配置文件创建相应的工厂
 * @DateTime 2023/9/21 21:39
 */
public class BeanDefinitionFactory {
	
	public static final String WAY_ANNOTATION = "annotation";
	public static final String WAY_XML = "XML";
	
	private static final String NoSuchDefiniteWayExceptionMessage = "check your configuration file";
	private static String way;
	
	private static void setWay (String way) {
		BeanDefinitionFactory.way = way;
	}
	
	public static BeanDefinition getBeanDefinition () throws NoSuchDefiniteWayException {
		if (way == null)
			way = WAY_ANNOTATION;
		switch (way) {
			case WAY_ANNOTATION:
				return new AnnotatedBeanDefinition();
			case WAY_XML:
				// Not implement yet.
//				return new XMLBeanDefinition();
			default:
				throw new NoSuchDefiniteWayException(NoSuchDefiniteWayExceptionMessage);
		}
	}
	
	
	public static BeanDefinitionReader getBeanDefinitionReader (String way) throws NoSuchDefiniteWayException {
		setWay(way);
		switch (way) {
			case WAY_ANNOTATION:
				return new AnnotatedBeanDefinitionReader();
			case WAY_XML:
				// Not implement yet.
//				return new XMLBeanDefinitionHandler();
			default:
				throw new NoSuchDefiniteWayException(NoSuchDefiniteWayExceptionMessage);
		}
	}
	
	public static BeanFactory getBeanFactory (String way) throws NoSuchDefiniteWayException {
		setWay(way);
		switch (way) {
			case WAY_ANNOTATION:
				return new AnnotationApplicationContext();
			case WAY_XML:
				// Not implement yet.
//				return new XMLBeanDefinitionHandler();
			default:
				throw new NoSuchDefiniteWayException(NoSuchDefiniteWayExceptionMessage);
		}
	}
}
