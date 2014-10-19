package com.mvc.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @author huangzec@foxmail.com
 *
 */
public class Reflect {
	
	/**
	 * 得到代理对象
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:26:51
	 * @return Object
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static Object getProxy(String className, Object... params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		return null != params && 0 < params.length ? newInstance(className, params) : newInstance(className);
	}
	
	/**
	 * 得到代理对象
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:30:26
	 * @return Object
	 * @throws Exception 
	 */
	public static Object getProxy(String className, Class<?>[] classes, Object[] params) throws Exception
	{
		return newInstance(className, classes, params);
	}

	/**
	 * 实例化当前的代理类
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午01:59:30
	 * @return Object
	 */
	public static Object newInstance(String className, Class<?>[] classes, Object[] params)
		throws Exception
	{
		try {
			Class<?> cls 	= Class.forName(className);
			Constructor<?> constructor = cls.getConstructor(classes);
			
			return constructor.newInstance(params);
		} catch (ClassNotFoundException e) {
			throw new Exception();
		}
	}
	
	/**
	 * 实例化当前的代理类
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:09:48
	 * @return Object
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static Object newInstance(String className, Object... params) 
		throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		try {
			Class<?> cls 	= Class.forName(className);
			if(null != params && 0 < params.length) {
				Constructor<?> constructor 	= cls.getConstructor(_getClasses(params));
				return constructor.newInstance(params);
			}
			
			return cls.newInstance();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
		
	}

	/**
	 * 执行相应对象的方法
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:36:14
	 * @return Object
	 * @throws Exception 
	 */
	public static Object invoke(Object target, String method, Object... params) throws Exception
	{
		try {
			return null != params && 0 < params.length ? 
					getMethod(target, method, params).invoke(target, params)
					: getMethod(target, method).invoke(target);
		} catch (IllegalArgumentException e) {
			throw new Exception("无效参数!" + method);
        } catch (IllegalAccessException e) {
			throw new Exception("不能访问!" + method);
        }
	}
	
	/**
	 * 得到当前调用的方法
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:39:42
	 * @return Object
	 * @throws Exception 
	 */
	public static Method getMethod(Object target, String name, Object... params) throws Exception 
	{
		Class<?>[] classes 	= _getClasses(params);
		for(Class<?> c = target.getClass(); c != Object.class; c = c.getSuperclass()) {
			try {
				Method method 	= (Method) (0 < params.length
					? c.getDeclaredMethod(name, classes) : c.getDeclaredMethods());
				if(null != method) {
					return method;
				}
			} catch (Exception e) {
				// 
			}
		}
		
		throw new Exception("没有找到当前方法 " + name);
	}

	/**
	 * 得到当前变量的类型
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午02:11:03
	 * @return Class<?>
	 */
	protected static Class<?>[] _getClasses(Object[] args) {
		Class<?>[] argsClass 	= new Class[args.length];
		for(int i = 0; null != args && i < args.length; i++) {
			argsClass[i] 	= args[i].getClass();
		}
		
		return argsClass;
	}
	
	/**
	 * 得到对象里的字段
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 下午03:03:28
	 * @return Field
	 */
	public static Field getField(Object target, String name, boolean isIgnoreAccess) throws Exception
	{
		try {
			Field field 	= target.getClass().getDeclaredField(name);
			if(true == isIgnoreAccess && false == field.isAccessible()) {
				field.setAccessible(true);
			}
			
			return field;
		} catch (Exception e) {
			throw new Exception("没有找到对应字段 " + name);
		}
	}
}
