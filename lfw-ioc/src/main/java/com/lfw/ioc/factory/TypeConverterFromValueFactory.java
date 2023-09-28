package com.lfw.ioc.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 19:43
 */
public class TypeConverterFromValueFactory {
	
	private static final Map<Class<?>, Function<String, ?>> converters;
	
	static {
		converters = new HashMap<>();
		TypeConverterFromValueFactory.registerConverter(String::valueOf, String.class);
		TypeConverterFromValueFactory.registerConverter(Integer::valueOf, Integer.class, int.class);
		TypeConverterFromValueFactory.registerConverter(Double::valueOf, Double.class, double.class);
		TypeConverterFromValueFactory.registerConverter(Boolean::valueOf, Boolean.class, boolean.class);
		TypeConverterFromValueFactory.registerConverter(Float::valueOf, Float.class, float.class);
		TypeConverterFromValueFactory.registerConverter(Long::valueOf, Long.class, long.class);
		TypeConverterFromValueFactory.registerConverter(Byte::valueOf, Byte.class, byte.class);
		TypeConverterFromValueFactory.registerConverter(Short::valueOf, Short.class, short.class);
		TypeConverterFromValueFactory.registerConverter(new Function<String, Character>() {
			@Override
			public Character apply (String s) {
				if (s.length() == 1) {
					return s.charAt(0);
				}
				else {
					throw new IllegalArgumentException("Input string should have length 1 for char conversion.");
				}
			}
		}, Character.class, char.class);
	}
	
	// 注册转换策略
	@SafeVarargs
	public static <T> void registerConverter (Function<String, T> converter, Class<T>... targetTypes) {
		for (Class<?> targetType : targetTypes)
			converters.put(targetType, converter);
	}
	
	// 根据目标类型获取转换策略
	@SuppressWarnings ("unchecked")
	public static <T> Function<String, T> getConverter (Class<T> targetType) {
		return (Function<String, T>) converters.get(targetType);
	}
	
	// Strategy Pattern
	public static <T> T convertStringToType (String str, Class<T> targetType) {
		Function<String, T> converter = TypeConverterFromValueFactory.getConverter(targetType);
		if (converter != null) {
			return converter.apply(str);
		}
		else {
			throw new IllegalArgumentException("Unsupported target type");
		}
	}

//	public static Character convertStringToType(String str, Class<Character> targetType) {
//		return str.charAt(0);
//	}
}
