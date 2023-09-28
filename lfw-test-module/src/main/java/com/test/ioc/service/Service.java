package com.test.ioc.service;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZService;
import com.lfw.ioc.annotation.ZValue;
import com.test.ioc.mapper.Mapper;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/28 0:21
 */
@ZService
public class Service {
	
	@ZValue ("1111111111111111")
	private long a;
	@ZAutowired
	private Mapper mapper;
	
	@Override
	public String toString () {
		return "Service{" +
				"a=" + a +
				",\n mapper=" + mapper +
				'}';
	}
	
}
