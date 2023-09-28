package com.lfw.ioc.exception;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 21:44
 */
public class NoSuchDefiniteWayException extends Exception {
	public NoSuchDefiniteWayException () {
		super();
	}
	
	public NoSuchDefiniteWayException (String message) {
		super(message);
	}
}
