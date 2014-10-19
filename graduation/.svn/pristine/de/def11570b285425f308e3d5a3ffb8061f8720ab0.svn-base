package com.mvc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建工具Pojo类
 * @author huangzec@foxmail.com
 *
 */
public class WizardPojo {

	/**
	 * file路径Map
	 */
	@SuppressWarnings("serial")
	public static Map<String, Map<String, String>> filesMap = new HashMap<String, Map<String, String>>(){{
		put("dao", new HashMap<String, String>(){{
			put("src", "src/com/mvc/wizard/data/dao.hd");
			put("desc", "src/com/mvc/dao/%sDao.java");
		}});
		put("service", new HashMap<String, String>(){{
			put("src", "src/com/mvc/wizard/data/service.hd");
			put("desc", "src/com/mvc/service/%sService.java");
		}});
		put("controller", new HashMap<String, String>(){{
			put("sitesrc", "src/com/mvc/wizard/data/controller.hd");
			put("sitedesc", "src/com/mvc/controller/%sSiteController.java");
			put("adminsrc", "src/com/mvc/wizard/data/admincontroller.hd");
			put("admindesc", "src/com/mvc/controller/admin/%sController.java");
		}});
		put("config", new HashMap<String, String>(){{
			put("cfg", "src/config/applicationContext.xml");
		}});
	}};
}
