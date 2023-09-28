package com.lfw.ioc.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 16:40
 */
@SuppressWarnings ({ "unused" })
class AnnotatedBeanDefinitionTest {
	
	@Test
	void reflectFieldsTest () throws Exception {
		Class<?> clazz = Class.forName("com.lfw.ioc.annotation.AnnotatedBeanDefinitionTest$TestClass");
		TestClass o = (TestClass) clazz.getDeclaredConstructor().newInstance();
		Field[] declaredFields = o.getClass().getDeclaredFields();
		
		for (Field f : declaredFields) {
			System.out.println(f.getName());
			System.out.println(f.getType());
			System.out.println();
		}
		System.out.println(o);
	}
	
	protected static class Inner {
		private String f1;
		private String f2;
	}
	
	protected static class TestClass {
		private String f1;
		private String f2;
		private Inner inner;
	}
}