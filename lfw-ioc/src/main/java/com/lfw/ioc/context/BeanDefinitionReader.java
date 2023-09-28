package com.lfw.ioc.context;

import java.util.List;
import java.util.Map;

/*
 * @Author Zzs
 * @Description 专注于读取bean中信息的函数式接口，要读的信息有属性和方法(还没实现)
 * @DateTime 2023/9/15 13:27
 *
 */
@FunctionalInterface
public interface BeanDefinitionReader {
	
	Map<Class<?>, BeanDefinition> reading (List<Class<?>> toBeReading);
}
