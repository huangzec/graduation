package com.mvc.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mvc.entity.Student;

/**
 * 数组工具类
 * @author huangzec@foxmail.com
 *
 */
public class ArrayUtil {
	
	/**
	 * 把列表转换成 哈希键值
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午09:03:52
	 * @return Map<String,Map<String,String>>
	 */
	public static Map<String, Map<String, String>> turnListMapToMapMap(String key, List<Map<String, String>> list)
	{
		Map<String, Map<String, String>> data 	= new LinkedHashMap<String, Map<String,String>>();
		for(int i = 0; list != null && i < list.size(); i ++) {
			if(null == list.get(i) || null == list.get(i).get(key)) {
				continue;
			}
			data.put(list.get(i).get(key), list.get(i));
		}
		
		return data;
	}
	
	/**
	 * 把列表转换为map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午08:35:22
	 * @return Map<String,Map<String,String>>
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public static Map<String, Map<String, String>> turnListToMap(String id, String name, List<?> list)
	{
		if(null == list || 1 > list.size()) {
			return null;
		}
		Map<String, Map<String, String>> map 	= new LinkedHashMap<String, Map<String, String>>();
		for(int i = 0; list != null && i < list.size(); i ++) {
			if(null == list.get(i)) {
				continue;
			}
			try {
				Object obj 		= list.get(i);
				Class cls 		= obj.getClass();
				Field idfield 	= cls.getDeclaredField(id);
				Field namefield = cls.getDeclaredField(name);
				idfield.setAccessible(true);
				namefield.setAccessible(true);
				Method idmethod 	= cls.getDeclaredMethod("get" + StringUtil.ufirst(id), null);
				Method namemethod 	= cls.getDeclaredMethod("get" + StringUtil.ufirst(name), null);
				String idresult 	= idmethod.invoke(obj, null).toString();
				final String nameresult 	= namemethod.invoke(obj, null).toString();
				map.put(idresult, new HashMap<String, String>(){{
					put("name", nameresult);
				}});
			} catch (Exception e) {
				
			}
		}
		
		return map;
	}

	/**
	 * 把记录转换为Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-15 下午02:14:29
	 * @return Map<String,Map<String,String>>
	 */
	public static Map<String, Map<String, String>> turnRecordToMap(String id, String name, Object obj)
	{
		if(null == obj) {
			return null;
		}
		Map<String, Map<String, String>> map 	= new LinkedHashMap<String, Map<String,String>>();
		try {
			Class cls 		= obj.getClass();
			Field idfield 	= cls.getDeclaredField(id);
			Field namefield = cls.getDeclaredField(name);
			idfield.setAccessible(true);
			namefield.setAccessible(true);
			Method idmethod 	= cls.getDeclaredMethod("get" + StringUtil.ufirst(id), null);
			Method namemethod 	= cls.getDeclaredMethod("get" + StringUtil.ufirst(name), null);
			String idresult 	= idmethod.invoke(obj, null).toString();
			final String nameresult 	= namemethod.invoke(obj, null).toString();
			map.put(idresult, new HashMap<String, String>(){{
				put("name", nameresult);
			}});
		} catch (Exception e) {
			
		}
		
		return map;
	}
	
	/**
	 * 得到Map里面的值
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午05:54:59
	 * @return String
	 */
	public static String getMapValue(Object key, Map<String, String> map)
	{
		return getMapValue(key, map, "");
	}

	/**
	 * 得到Map里面的值
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午05:57:44
	 * @return String
	 */
	public static String getMapValue(Object key, Map<String, String> map, String def) {
		if(null != map && map.containsKey(key)) {
			return null == map.get(key) ? def : map.get(key).toString();
		}
		
		return def;
	}

}
