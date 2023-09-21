package com.lfw.ioc.annotation;

import com.lfw.ioc.factory.TestClass;
import org.junit.jupiter.api.Test;

import java.util.List;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 20:44
 */
class AnnotatedBeanDefinitionReaderTest {
	
	@Test
	void initializeFieldTest () {
		System.out.println(new AnnotatedBeanDefinitionReader().reading(List.of(TestClass.class)));
	}
	
	@Test
	void reading () {
	
	}
}