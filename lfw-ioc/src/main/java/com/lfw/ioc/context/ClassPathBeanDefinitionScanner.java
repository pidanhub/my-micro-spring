package com.lfw.ioc.context;

import com.lfw.ioc.config.BeanAnnotationEnum;
import com.lfw.ioc.utils.RecursiveClassLoader;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import static com.lfw.ioc.utils.RecursiveClassLoader.matchFile;

/*
 * @Author Zzs
 * @Description 用于扫描类路径下的所有bean
 * @DateTime 2023/9/15 13:21
 */
@SuppressWarnings("unused")
public class ClassPathBeanDefinitionScanner {
	
	private final String classPath;
	public ClassPathBeanDefinitionScanner(String classPath) {
		this.classPath = classPath;
	}
	
	public ClassPathBeanDefinitionScanner () {
		// TODO 获取当前项目的根目录，可能要传参
		this.classPath = "/";
	}
	
	private List<Class<?>> beanClasses;
	private void recursivelyScan () throws IOException {
		String path = matchFile(this.classPath);
		Enumeration<URL> resourceUrls = Thread.currentThread().getContextClassLoader().getResources(path);
		List<Class<?>> beanClasses = null;
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			String protocol = url.getProtocol();
			/*
				详见spring的org.springframework.util.ResourceUtils，对各个类型的资源包都有相应的加载规则
				只扫描文件夹下的文件资源
			 */
			if(protocol.equals("file")) {
				// 递归变量路径下面所有的 class文件，加载所有的.class文件
				beanClasses = RecursiveClassLoader.loadClasses(classPath);
			}else if(protocol.equals("jar")) {
				// 等下再说
				break; // TODO
			}
		}
		this.beanClasses = beanClasses;
	}
	
	public List<Class<?>> addAllAnnotatedClasses () throws IOException {
		this.recursivelyScan();
		List<Class<?>> values = BeanAnnotationEnum.getValues();
		List<Class<?>> needAddClasses = new ArrayList<>();
		for (Class<?> aClass : beanClasses) {
			Annotation[] annotations = aClass.getAnnotations();
			// 这里有很多注解，比如@Service，@Controller，@Component等等
			// 而且很容易就会多几个类似功能的注解，必须要高可拓展才行
			if (annotations.length != 0) {
				for (Annotation a : annotations)
					if (values.contains(a.annotationType()))
						needAddClasses.add(aClass);
			}
		}
		return needAddClasses;
	}
	
}
