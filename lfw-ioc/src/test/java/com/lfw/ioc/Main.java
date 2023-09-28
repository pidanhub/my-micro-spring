package com.lfw.ioc;

import java.lang.reflect.Field;

class MyClass {
	private int value;
	
	public MyClass (int value) {
		this.value = value;
	}
	
	public int getValue () {
		return value;
	}
	
	public void setValue (int value) {
		this.value = value;
	}
}

public class Main {
	public static void main (String[] args) throws NoSuchFieldException, IllegalAccessException {
		MyClass myObject = new MyClass(10);
		
		// 输出原始值
		System.out.println("Original Value: " + myObject.getValue()); // 输出: Original Value: 10
		modify(myObject);
		// 输出修改后的值
		System.out.println("Modified Value: " + myObject.getValue()); // 输出: Modified Value: 20
	}
	
	static Object modify (Object o) {
		// 使用反射修改对象的属性
		Object myObject = o;
		Field field = null;
		try {
			field = MyClass.class.getDeclaredField("value");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(myObject, 20);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return myObject;
	}
}
