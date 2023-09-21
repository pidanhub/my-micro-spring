package com.lfw.ioc.context;

import java.util.List;

/*
 * @Author Zzs
 * @Description 专注于读取bean中信息的函数式接口
 * @DateTime 2023/9/15 13:27
 *
 */
@FunctionalInterface
public interface BeanDefinitionReader {
	
	List<BeanDefinition> reading (List<Class<?>> toBeReading);

}
