package com.mvc.common;

/**
 * 切面代理工具类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2015-1-4 下午12:13:28 
 * @version 	1.0
 */
public class AopProxy {

	/**
	 * 当前类名
	 */
	private Object _obj;
	
	public AopProxy(Object obj)
	{
		_obj 	= obj;
	}

	/**
	 * 调用需要执行的方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param method
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public Object invoke(String method, Object... params) throws Exception 
	{
		Object result 	= Reflect.invoke(_obj, method, params);
		
		return result;
	}
}
