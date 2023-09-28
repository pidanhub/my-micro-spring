package com.lfw.ioc.context;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZValue;
import com.lfw.ioc.factory.BeanDefinitionFactory;
import com.lfw.ioc.factory.TypeConverterFromValueFactory;
import com.lfw.ioc.utils.AnnotatedFieldsHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/14 22:32
 */
@SuppressWarnings ("unused")
public class AnnotatedBeanDefinitionReader implements BeanDefinitionReader, AnnotatedFieldsHandler {
	
	/**
	 * IoC识别出了需要加载的类，接下来要初始化并存在IoC的缓存内，是个方法就是读出程序设计者提供给容器的值
	 *
	 * @param toBeReadingCopy 需要去读bean定义信息的Classes
	 * @return 返回定义信息
	 */
	@Override
	public Map<Class<?>, BeanDefinition> reading (List<Class<?>> toBeReadingCopy) {
		Map<Class<?>, BeanDefinition> beanDefinitionMap = new HashMap<>();
		try {
			for (Class<?> c : toBeReadingCopy) {
				BeanDefinition beanDefinition = BeanDefinitionFactory.getBeanDefinition()
						.setClass(c)
						.setBeanName(getBeanName(c.getSimpleName()));
				handleFields(c, beanDefinition);
				beanDefinitionMap.put(c, beanDefinition);
			}
			// clear
			SetterHelper.field = null;
			SetterHelper.beanDefinition = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanDefinitionMap;
	}
	
	@Override
	public void value () {
		Field f = SetterHelper.field;
		BeanDefinition beanDefinition = SetterHelper.beanDefinition;
		ZValue zValue = f.getAnnotation(ZValue.class);
		if (zValue == null || zValue.value().equals(""))
			return;
		String value = zValue.value();
		// 现在根据字段的类型，将字符串转换成对应类型的对象
		beanDefinition.addIntoDefinitionMap(f.getName(),
				// 将value中的字符串转换成为对应的类型，并存入BeanDefinition中
				TypeConverterFromValueFactory.convertStringToType(value, f.getType()));
	}
	
	@Override
	public void autowired () {
		Field f = SetterHelper.field;
		BeanDefinition beanDefinition = SetterHelper.beanDefinition;
		ZAutowired zAutowired = f.getAnnotation(ZAutowired.class);
		if (zAutowired == null)
			return;
		beanDefinition.addIntoDefinitionMap(f.getName(), f.getType());
	}
	
	private String getBeanName (String beanName) {
		return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
	}
	
}
