package com.lfw.ioc.exception;

/*
 * @Author Zzs
 * @Description 依赖注入的接口有多个实现类
 * @DateTime 2023/9/28 18:00
 */
public class MultiplyImplementClassException extends RuntimeException {
	public MultiplyImplementClassException () {
		super();
	}
	
	public MultiplyImplementClassException (String message) {
		super(message);
	}
}
