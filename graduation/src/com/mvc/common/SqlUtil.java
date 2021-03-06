package com.mvc.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL工具类
 * @author huangzec@foxmail.com
 *
 */
public class SqlUtil {

	/**
	 * 得到WhereIn条件句
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午09:28:42
	 * @return String
	 */
	public static String whereIn(String field, String extractField, List<?> list)
	{
		return _whereInOrNotInByList("IN", field, extractField, list);
	}
	
	/**
	 * 得到WhereNotIn条件句 
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-18 下午01:52:42
	 * @return String
	 */
	public static String whereNotIn(String field, String extractField, List<?> list)
	{
		return _whereInOrNotInByList("NOT IN", field, extractField, list);
	}

	/**
	 * Where In/Not 的条件子句生成工具
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 上午11:42:28
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private static String _whereInOrNotInByList(String type, String field, String extractField, List<?> list) {
		if(null == list || 1 > list.size()) {
			return "1 = 2";
		}
		List<String> items 		= new ArrayList<String>();
		StringBuffer whereIn 	= new StringBuffer(field + " " + type + " (");
		for(int i = 0; i < list.size(); i ++ ) {
			try {
				Object obj 		= list.get(i);
				Class cls 		= obj.getClass();
				Field fld 		= cls.getDeclaredField(extractField);
				fld.setAccessible(true);
				Method method 	= cls.getDeclaredMethod("get" + StringUtil.ufirst(extractField), null);
				String result 		= method.invoke(obj, null).toString();
				if(null == result || items.contains(result)) {
					continue;
				}
				items.add(result);
				whereIn.append("'" + result + "',");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(1 > items.size()) {
			items 	= null;
			return "1 = 2";
		}
		items 	= null;
		whereIn.deleteCharAt(whereIn.length() - 1);
		
		return whereIn.append(")").toString();
	}

	/**
	 * Where In/Not 的条件子句生成工具
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午09:35:19
	 * @return String
	 */
	private static String _whereInOrNotInByListMap(
			String type, String field, String extractField, List<Map<String, String>> list) 
	{
		if(null == list || 1 > list.size()) {
			return "1 = 2";
		}
		List<String> items 			= new ArrayList<String>();
		StringBuffer whereNotIn 	= new StringBuffer("'" + field + "' " + type + " (");
		for(int i = 0; i < list.size(); i ++) {
			Map<String, String> ele = list.get(i);
			if(null == ele.get(extractField) || items.contains(ele.get(extractField))) {
				continue;
			}
			items.add(ele.get(extractField));
			whereNotIn.append("'" + ele.get(extractField) + ",");
		}
		if(1 > items.size()) {
			items 	= null;
			return "1 = 2";
		}
		items 	= null;
		whereNotIn.charAt(whereNotIn.length() - 1);
		
		return whereNotIn.append(")").toString();
	}

}
