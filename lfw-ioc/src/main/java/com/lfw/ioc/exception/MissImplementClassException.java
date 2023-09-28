package com.lfw.ioc.exception;

/*
 * @Author Zzs
 * @Description 依赖注入的接口缺少实现类
 * @DateTime 2023/9/28 17:58
 */
public class MissImplementClassException extends RuntimeException {
	
	public MissImplementClassException () {
		super();
	}
	
	public MissImplementClassException (String message) {
		super(message);
	}
}
