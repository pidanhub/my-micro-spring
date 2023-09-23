package com.lfw.ioc.config;

import com.lfw.ioc.annotation.ZService;
import org.junit.jupiter.api.Test;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/19 18:35
 */
@ZService
class BeanAnnotationEnumTest {
	
	@Test
	void enumTest () {
		
		String type = "com.lfw.ioc.annotation.annotation.ZComponent";
		for (BeanAnnotationEnum beanAnnotationEnum : BeanAnnotationEnum.values())
			if (beanAnnotationEnum.value.getName().equals(type))
				System.out.println(beanAnnotationEnum.value);
			else
				System.out.println("x -- " + beanAnnotationEnum.value);
			
	}
	
}