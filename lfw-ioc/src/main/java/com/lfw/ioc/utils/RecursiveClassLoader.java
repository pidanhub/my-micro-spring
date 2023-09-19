package com.lfw.ioc.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/*
 * @Author Zzs
 * @Description 扫描器
 * @DateTime 2023/9/18 16:35
 */
@SuppressWarnings({"unused"})
public class RecursiveClassLoader {
	
	public static List<Class<?>> loadClasses(String basePackage) throws IOException {
		String basePackagePath = matchFile(basePackage);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> resource = classLoader.getResources(basePackagePath);
		if (resource == null) {
			throw new IllegalArgumentException("Base package not found: " + basePackage);
		}
		List<Class<?>> loadedClasses = new ArrayList<>();
		while(resource.hasMoreElements()) {
			File basePackageDir = new File(resource.nextElement().getFile());
			loadClassesFromDirectory(basePackage, basePackageDir, loadedClasses);
		}
		return loadedClasses;
	}
	
	private static void loadClassesFromDirectory(String packageName, File directory, List<Class<?>> loadedClasses) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					String subPackageName = packageName + "." + file.getName();
					loadClassesFromDirectory(subPackageName, file, loadedClasses);
				} else if (file.getName().endsWith(".class")) {
					String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
					try {
						Class<?> loadedClass = Class.forName(className);
						loadedClasses.add(loadedClass);
					} catch (ClassNotFoundException e) {
						System.err.println("Failed to load class: " + className);
					}
				}
			}
		}
	}
	
	public static String matchFile (String classPath) {
		return classPath.replace('.', '/');
	}
}
