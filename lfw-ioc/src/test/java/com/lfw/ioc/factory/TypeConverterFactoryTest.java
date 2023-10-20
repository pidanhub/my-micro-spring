package com.lfw.ioc.factory;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 20:06
 */
class TypeConverterFactoryTest {
	
	@Test
	void convertStringToTypeTest () {
		Class<?> aClass = TestClass.class;
		Field[] declaredFields = aClass.getDeclaredFields();
		System.out.println(TypeConverterFromValueFactory.convertStringToType("l", declaredFields[0].getType()).getClass());
		System.out.println(TypeConverterFromValueFactory.convertStringToType("1.1", declaredFields[1].getType()).getClass());
		System.out.println(TypeConverterFromValueFactory.convertStringToType("false", declaredFields[2].getType()).getClass());
		System.out.println(TypeConverterFromValueFactory.convertStringToType("444876844", declaredFields[3].getType()).getClass());
		System.out.println(TypeConverterFromValueFactory.convertStringToType("11", declaredFields[4].getType()).getClass());
		System.out.println(TypeConverterFromValueFactory.convertStringToType("d", declaredFields[5].getType()).getClass());
	}
}