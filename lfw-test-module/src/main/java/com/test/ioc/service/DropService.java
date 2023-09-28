package com.test.ioc.service;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZService;
import com.test.ioc.mapper.Mapper;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/28 0:21
 */
@ZService
public class DropService {
	@ZAutowired
	private Mapper mapper;
	
	@Override
	public String toString () {
		return "DropService{" +
				"mapper=" + mapper +
				'}';
	}
}
