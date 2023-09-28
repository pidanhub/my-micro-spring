package com.test.ioc.mapper.impl;

import com.lfw.ioc.annotation.ZMapper;
import com.lfw.ioc.annotation.ZValue;
import com.test.ioc.mapper.Mapper;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/28 0:46
 */
@ZMapper
public class MapperImpl implements Mapper {
	
	@ZValue ("1.3")
	private Float aFloat;
	
	@Override
	public String toString () {
		return "MapperImpl{" +
				"aFloat=" + aFloat +
				'}';
	}
	
	@Override
	public String serve () {
		return "Hello IoC!";
	}
}
