package com.lfw.ioc.exception;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 19:18
 */
public class FieldMissValueAnnotationException extends Exception {
	public FieldMissValueAnnotationException () {
		super();
	}
	
	public FieldMissValueAnnotationException (String message) {
		super(message);
	}
}
