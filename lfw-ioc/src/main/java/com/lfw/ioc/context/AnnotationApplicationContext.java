package com.lfw.ioc.context;

import com.lfw.ioc.exception.MissImplementClassException;
import com.lfw.ioc.exception.MultiplyImplementClassException;
import com.lfw.ioc.factory.BeanFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author Zzs
 * @Description 通过注解声明的bean对象
 * @DateTime 2023/9/15 13:51
 *
 */
//@SuppressWarnings ("unused")
public class AnnotationApplicationContext implements BeanFactory {
	
	protected static final Map<String, Object> stringObjectCacheMap = new HashMap<>();
	protected static final Map<Class<?>, Object> classObjectCacheMap = new HashMap<>();
	/* TODO 解决循环依赖所用 */
	protected Map<Class<?>, List<Class<?>>> toInjectMap = new HashMap<>();
	
	public AnnotationApplicationContext () {
	}
	
	@Override
	public Object getBean (Class<?> bean) {
		return classObjectCacheMap.get(bean);
	}
	
	@Override
	public Object getBean (String beanName) {
		if (beanName.startsWith("&"))
			return this;
		return stringObjectCacheMap.get(beanName);
	}
	
	/**
	 * 从此函数开始生产bean对象
	 * for循环结束后，生成半成品bean，Autowired可能产生循环依赖，这需要解决
	 * 函数调用结束之后，生成成品bean，过程中会生成很多半成品
	 * {@see AnnotationApplicationContext#setBeanValue()} 有关注入过程
	 *
	 * @param beanDefinitionMap 包含bean的初始化信息
	 */
	@Override
	public void loading (Map<Class<?>, BeanDefinition> beanDefinitionMap) throws Exception {
//		Map<Class<?>, Object> temp = new HashMap<>(); // 中间产物缓存
		for (Map.Entry<Class<?>, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
			// 对于一个即将生成的bean，有可能被其他bean依赖而已经生成，如果存在，则跳过
			if (classObjectCacheMap.get(entry.getKey()) != null)
				continue;
			recursiveLoad(entry, beanDefinitionMap);
		}
	}
	
	protected void recursiveLoad (Map.Entry<Class<?>, BeanDefinition> entry, Map<Class<?>, BeanDefinition> map) throws Exception {
		Class<?> targetClass = entry.getKey();
		BeanDefinition bd = entry.getValue();
		if (entry.getKey().toString().startsWith("interface")) {
			targetClass = getImplementClass(map, targetClass);
			bd = map.get(targetClass);
		}
		Object target = targetClass.getDeclaredConstructor().newInstance();
		String beanName = bd.getBeanName();
		Class<?> beanClass = bd.getBeanClass();
		
		setBeanValue(target, beanClass, bd.getMap(BeanDefinition.VALUE));
		
		Map<String, Object> awMap = bd.getMap(BeanDefinition.AUTOWIRED);
		for (Map.Entry<String, Object> stringObjectEntry : awMap.entrySet()) {
			Class<?> fieldClass = (Class<?>) stringObjectEntry.getValue();
			if (fieldClass.toString().startsWith("interface"))
				fieldClass = getImplementClass(map, fieldClass);
			if (!classObjectCacheMap.containsKey(fieldClass)) {
				BeanDefinition beanDefinition = map.get(fieldClass);
				Map.Entry<Class<?>, BeanDefinition> fEntry = new AbstractMap.SimpleEntry<>(fieldClass, beanDefinition);
				recursiveLoad(fEntry, map);
			}
			Object fTarget = classObjectCacheMap.get(fieldClass);
			Field declaredField = beanClass.getDeclaredField(stringObjectEntry.getKey());
			declaredField.setAccessible(true);
			declaredField.set(target, fTarget);
		}
		
		stringObjectCacheMap.put(beanName, target);
		classObjectCacheMap.put(beanClass, target);
	}
	
	protected final void setBeanValue (Object object, Class<?> aClass, @NotNull Map<String, Object> map) throws Exception {
		Field declaredField;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			declaredField = aClass.getDeclaredField(entry.getKey());
			declaredField.setAccessible(true);
			declaredField.set(object, entry.getValue());
		}
	}
	
	/**
	 * 此函数用于找到接口对应的实现类
	 *
	 * @param map            全部bean集合
	 * @param interfaceClass 属性中的接口
	 * @return 应该被创建的对象
	 */
	protected Class<?> getImplementClass (Map<Class<?>, BeanDefinition> map, Class<?> interfaceClass) {
		// lambda表达式只能获取final
		final Class<?>[] target = { null };
		map.forEach((clazz, bd) -> {
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> anyInterface : interfaces) {
				if (interfaceClass.equals(anyInterface)) {
					if (target[0] == null)
						target[0] = clazz;
					else // lambda表达式异常不能添加到函数签名上，所以这里抛的是运行时异常
						throw new MultiplyImplementClassException();
				}
			}
		});
		
		if (target[0] == null)
			throw new MissImplementClassException();
		return target[0];
	}
}
