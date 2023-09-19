package com.lfw.ioc.context;

import com.lfw.ioc.annotation.ZComponent;
import com.lfw.ioc.annotation.ZComponentScan;
import com.lfw.ioc.config.BeanAnnotationEnum;
import com.lfw.ioc.utils.RecursiveClassLoader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 14:50
 *
 */
@SuppressWarnings({ "FieldCanBeLocal", "unused" })
@ZComponent
class ClassPathBeanDefinitionScannerTest {
	
	@ZComponentScan ("com.example.my")
	private static class Hello {}
	
	private ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner;
	
	@Test
	public void recursivelyScanTest () throws Exception {
		classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner("com.lfw.ioc");
		List<?> list = classPathBeanDefinitionScanner.addAllAnnotatedClasses();
		System.out.println(list);
	}
	
	@Test
	public void recursivelyScan () throws Exception {
		Class<?> clazz = ClassPathBeanDefinitionScannerTest.Hello.class;
		ZComponentScan zComponentScan = clazz.getAnnotation(ZComponentScan.class);
		System.out.println("-----"+zComponentScan.value());
		String pack = "com.lfw.ioc.";
		String path = "com/lfw/ioc";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		System.out.println(classLoader);
		Enumeration<URL> resource = classLoader.getResources(path);
		List<File> fileList = new ArrayList<>();
		while(resource.hasMoreElements()) {
			URL url = resource.nextElement();
			System.out.println(url);
			listFiles(new File(url.toURI()), fileList);
		}

//		fileList.forEach(System.out::println);
		System.out.println(fileList.get(10));
		int com = fileList.get(10).toString().indexOf("com");
		String substring = fileList.get(10).toString().substring(com, fileList.get(10).toString().length() - 6);
		substring = substring.replace("\\", ".");
		System.out.println(substring);
		Class<?> aClass = classLoader.loadClass(substring);
		System.out.println(aClass);
	}
	
	private static void listFiles(File dir, List<File> fileList) {
		if (dir.isDirectory()) {
			for (File f : Objects.requireNonNull(dir.listFiles())) {
				listFiles(f, fileList);
			}
		} else {
			System.out.println(dir.getName());
			if(dir.getName().endsWith(".class")) {
				fileList.add(dir);
			}
		}
	}
	
	@Test
	public void loaderTest() throws IOException {
		String path = "com.lfw.ioc";
		List<Class<?>> loadedClasses = RecursiveClassLoader.loadClasses(path);
		loadedClasses.forEach(System.out::println);
		System.out.println();
		
		// 需要加载的注解集合
		List<Class<?>> values = BeanAnnotationEnum.getValues();
		System.out.println(values);
		System.out.println();
		// get annotated classes
		classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner("com.lfw.ioc");
		List<Class<?>> list = classPathBeanDefinitionScanner.addAllAnnotatedClasses();
		System.out.println(list.size());
	}
	
}