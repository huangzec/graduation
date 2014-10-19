package com.mvc.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map类型工具类
 * @author huangzec@foxmail.com
 *
 */
public class MapUtil {
	
	/**
	 * 生成关联格式的列表Map集合给List视图用
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 上午10:30:51
	 * @return Map<String,Map<String,String>>
	 */
	@SuppressWarnings("serial")
	public static Map<String, Map<String, String>> makeLinkedMapMap(Map<String, String> data)
	{
		Map<String, Map<String, String>> map 	= new HashMap<String, Map<String, String>>();
		Iterator<Entry<String, String>> iter 	= data.entrySet().iterator();
		while(iter.hasNext()) {
			final Map.Entry<String, String> item 	= iter.next();
			map.put(item.getKey(), new HashMap<String, String>(){{
				put("id", item.getKey());
				put("name", item.getValue());
			}});
		}
		
		return map;
	}
	
	/**
	 * 生成关联列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午06:04:57
	 * @return List<Map<String,String>>
	 */
	public static List<Map<String, String>> makeLinkedListMap(Map<String, String> data)
	{
		List<Map<String, String>> list 			= new ArrayList<Map<String, String>>();
		Iterator<Entry<String, String>> iter 	= data.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, String> item = iter.next();
			Map<String, String> map 	= new HashMap<String, String>();
			map.put("id", item.getKey());
			map.put("name", item.getValue());
			list.add(map);
		}
		
		return list;
	}

}
