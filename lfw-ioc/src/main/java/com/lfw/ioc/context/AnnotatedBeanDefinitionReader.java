package com.lfw.ioc.context;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZValue;
import com.lfw.ioc.config.FieldsAnnotationHandler;
import com.lfw.ioc.factory.BeanDefinitionFactory;
import com.lfw.ioc.factory.TypeConverterFromValueFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/14 22:32
 */
@SuppressWarnings ("unused")
public class AnnotatedBeanDefinitionReader implements BeanDefinitionReader, FieldsAnnotationHandler {
	
	/**
	 * IoC识别出了需要加载的类，接下来要初始化并存在IoC的缓存内，是个方法就是读出程序设计者提供给容器的值
	 *
	 * @param toBeReadingCopy 需要去读bean定义信息的Classes
	 * @return 返回定义信息
	 */
	@Override
	public List<BeanDefinition> reading (List<Class<?>> toBeReadingCopy) {
		List<BeanDefinition> beanDefinitionList = new ArrayList<>();
		try {
			for (Class<?> c : toBeReadingCopy) {
				BeanDefinition beanDefinition = BeanDefinitionFactory.getBeanDefinition()
						.setClass(c)
						.setBeanName(getBeanName(c.getSimpleName()));
				fieldsHandler(c, beanDefinition);
				beanDefinitionList.add(beanDefinition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanDefinitionList;
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
				TypeConverterFromValueFactory.convertStringToType(value, f.getType()));
	}
	
	@Override
	public void autowired () {
		Field f = SetterHelper.field;
		BeanDefinition beanDefinition = SetterHelper.beanDefinition;
		ZAutowired zAutowired = f.getAnnotation(ZAutowired.class);
		if (zAutowired == null)
			return;
		beanDefinition.addIntoDefinitionMap(f.getName(), null);
	}
	
	private String getBeanName (String beanName) {
		return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
	}
	
}
