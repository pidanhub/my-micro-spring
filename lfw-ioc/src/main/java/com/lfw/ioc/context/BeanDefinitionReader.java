package com.lfw.ioc.context;

import java.util.List;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 13:27
 *
 */
public interface BeanDefinitionReader {
	
	void reading (List<Class<?>> toBeLoading);

}
