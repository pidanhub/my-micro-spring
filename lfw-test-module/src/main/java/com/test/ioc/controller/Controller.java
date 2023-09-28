package com.test.ioc.controller;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZController;
import com.lfw.ioc.annotation.ZValue;
import com.test.ioc.service.DropService;
import com.test.ioc.service.Service;

/*
 * @Author Zzs
 * @Description test
 * @DateTime 2023/9/28 0:18
 */
@ZController
public class Controller {
	
	@ZValue ("name")
	private String controllerName;
	@ZValue ("1024")
	private int maxConnection;
	
	@ZAutowired
	private Service service;
	
	@ZAutowired
	private DropService dropService;
	
	@Override
	public String toString () {
		return "Controller{" +
				"controllerName='" + controllerName + '\'' +
				",\n maxConnection=" + maxConnection +
				",\n service=" + service +
				",\n dropService=" + dropService +
				'}';
	}
}
