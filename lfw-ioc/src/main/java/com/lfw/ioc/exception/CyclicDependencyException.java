package com.lfw.ioc.exception;

/*
 * @Author Zzs
 * @Description 循环依赖
 * @DateTime 2023/9/27 14:46
 */
public class CyclicDependencyException extends RuntimeException {
	
	public CyclicDependencyException () {
		super();
	}
	
	public CyclicDependencyException (String message) {
		super(message);
	}
}
