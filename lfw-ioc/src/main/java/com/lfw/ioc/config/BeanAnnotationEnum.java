package com.lfw.ioc.config;

import com.lfw.ioc.annotation.ZComponent;
import com.lfw.ioc.annotation.ZController;
import com.lfw.ioc.annotation.ZService;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Zzs
 * @Description 此枚举类注册IoC加载类的注解，非常简单，添加一行就行
 *              TODO
 *              以后可能会有其他作用的注解，不能到处去写，以后会抽象出通用方法
 * @DateTime 2023/9/19 15:53
 */
@SuppressWarnings({"unused"})
public enum BeanAnnotationEnum {
	
	Z_CONTROLLER(ZController.class),
	Z_COMPONENT(ZComponent.class),
	Z_SERVICE(ZService.class),
		;
	
	public final Class<?> value;
	
	BeanAnnotationEnum (Class<?> value) {
		this.value = value;
	}
	
	public static List<Class<?>> getValues () {
		List<Class<?>> list = new ArrayList<>();
		for (BeanAnnotationEnum b : BeanAnnotationEnum.values())
			list.add(b.value);
		return list;
	}
	
}
