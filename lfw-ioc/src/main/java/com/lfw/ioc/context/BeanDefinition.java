package com.lfw.ioc.context;

import java.util.Map;

/*
 * @Author Zzs
 * @Description 定义bean的信息，包括字段信息，注意这里是根据配置文件自动选择使用什么子类
 *              配置文件的信息可以定义为类似如下格式 definition.way = "annotation"(也即下面的两个字段的值，拓展也很方便，再生成一个子类，并将方式注册在下面)
 *              而子类中addIntoDefinitionMap参数相同
 * @DateTime 2023/9/15 13:28
 */
@SuppressWarnings ("unused")
public interface BeanDefinition {
	
	BeanDefinition setBeanName (String beanName);
	
	BeanDefinition setClass (Class<?> aClass);
	
	void addIntoDefinitionMap (String fieldName, Object value);
	
	Map<String, Object> getMap ();
	
}
