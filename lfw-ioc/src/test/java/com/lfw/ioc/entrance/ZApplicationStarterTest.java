package com.lfw.ioc.entrance;

import com.lfw.ioc.annotation.ZApplication;
import com.lfw.ioc.annotation.ZComponentScan;
import com.lfw.ioc.annotation.ZController;
import com.lfw.ioc.context.AnnotationApplicationContext;
import com.lfw.ioc.factory.BeanFactory;
import org.junit.jupiter.api.Test;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 18:06
 */
@ZController
class ZApplicationStarterTest {
	
	@ZApplication
	@ZComponentScan ("com.example.my")
	private static class Hello {}
	
	@Test
	void TestAnnotation () {
		Class<?> clazz = Hello.class;
		ZComponentScan zComponentScan = clazz.getAnnotation(ZComponentScan.class);
		System.out.println(zComponentScan.value());
	}
	
	@Test
	void run () {
	
	}
	
	@Test
	void finalTest() {
		final String[] strings = new String[] {"a", "b", "c"};
		strings[0] = new String("d");
		
		final BeanFactory[] beanFactories = new BeanFactory[] {new AnnotationApplicationContext(), new AnnotationApplicationContext() };
		beanFactories[0] = new AnnotationApplicationContext();
		
		
	}
}