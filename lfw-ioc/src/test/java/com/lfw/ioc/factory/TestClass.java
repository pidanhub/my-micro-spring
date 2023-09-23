package com.lfw.ioc.factory;

import com.lfw.ioc.annotation.ZAutowired;
import com.lfw.ioc.annotation.ZValue;

public class TestClass {
	
	@ZValue ("dev-C++")
	public String f1;
	@ZValue ("1.1")
	public Float f2;
	@ZValue ("false")
	public boolean f3;
	@ZValue ("5.5")
	public double f4;
	@ZValue ("13")
	public int f5;
	@ZValue ("a")
	public char f6;
	
	@ZAutowired
	public TestClass testClass;
}