package com.test.ioc;

import com.lfw.ioc.annotation.ZApplication;
import com.lfw.ioc.annotation.ZComponentScan;
import com.lfw.ioc.context.AnnotationApplicationContext;
import com.lfw.ioc.entrance.ZApplicationEntrance;
import com.lfw.ioc.factory.BeanFactory;
import com.test.ioc.controller.Controller;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/26 19:20
 */
@ZApplication
@ZComponentScan ("com.test.ioc")
public class Main {
	public static void main (String[] args) {
		ZApplicationEntrance.run(Main.class, args);
		BeanFactory beanFactory = new AnnotationApplicationContext();
		System.out.println(beanFactory.getBean(Controller.class));
	}
}
