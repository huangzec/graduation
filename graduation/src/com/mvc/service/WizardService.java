package com.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.WizardDao;

/**
 * 构建工具服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class WizardService {
	
	@Autowired
	private WizardDao wizardDao;

	public List<Map<String, String>> getAllTables() {
		return wizardDao.getAllTables();
	}

}
