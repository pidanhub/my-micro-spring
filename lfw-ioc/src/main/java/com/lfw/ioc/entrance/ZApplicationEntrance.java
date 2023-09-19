package com.lfw.ioc.entrance;

import com.lfw.ioc.annotation.ZApplication;
import com.lfw.ioc.annotation.ZComponentScan;
import com.lfw.ioc.context.ClassPathBeanDefinitionScanner;
import com.lfw.ioc.exception.NotZApplicationAppException;
import com.lfw.ioc.exception.RootScanPathRegisterFailedException;
import com.lfw.ioc.factory.AnnotationApplicationContext;
import com.lfw.ioc.factory.BeanFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/15 16:43
 */
@SuppressWarnings({ "unused", "MismatchedQueryAndUpdateOfCollection" })
public class ZApplicationEntrance {
	
	// Q: 是否要将入口都放在这里？
	// 注册BeanFactory实现类后生效
	public static final BeanFactory[] beanFactories = new BeanFactory[] {
			new AnnotationApplicationContext()
	};
	
	private static final Set<Class<?>> includeAnnotations = new HashSet<>(Arrays.asList(
				ZApplication.class, ZComponentScan.class
		));
	
	private static final Map<Class<?>, List<Annotation>> moduleAnnotationList = new HashMap<>();
	public static void run (Class<?> starter, String... args) {
		run(new Class[]{starter}, args);
	}

	public static void run (Class<?>[] starters, String... args) {
		new ZApplicationEntrance().handleEntranceAnnotations(starters, args);
	}
	
	private void handleEntranceAnnotations (Class<?>[] starters, String... args) {
		// 获取启动类上的注解，并保存，以备他用
		for (Class<?> clazz : starters)
			moduleAnnotationList.put(clazz, Arrays.asList(clazz.getAnnotations()));
		Class<?> clazz = starters[0];
		try {
			if (clazz.getAnnotation(ZApplication.class) == null)
				throw new NotZApplicationAppException("Missed ZApplication annotation on the first entrance class.");
			
			// TODO 这个位置还没设计完善，mybatis中还有MapperScan注解
			// 从这里看，ClassPathBeanDefinitionScanner需要返回并且将BeanFactory处理得当，也要兼容其他框架带来的bean工厂，拓展性很重要
			// TODO 如何拓展？？
			// 其他注解委托到Mybatis框架的ProxyFactory？？
			ZComponentScan zComponentScan = clazz.getAnnotation(ZComponentScan.class);
			ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner;
			// TODO 没有添加注解的话，应该有默认的扫描路径，就是项目的根目录，暂不处理，留作加强
			if (zComponentScan == null || zComponentScan.value().equals(""))
				classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner();
			else {
				classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(zComponentScan.value());
			}
			classPathBeanDefinitionScanner.addAllAnnotatedClasses();
			
		} catch (NotZApplicationAppException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
