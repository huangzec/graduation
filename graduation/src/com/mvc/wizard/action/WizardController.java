package com.mvc.wizard.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mvc.common.DirUtil;
import com.mvc.common.FileUtil;
import com.mvc.common.HResponse;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.common.WizardPojo;
import com.mvc.service.WizardService;

/**
 * 构建工具类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/wizard")
public class WizardController {
	
	private String _projectDir = null;
	
	@Autowired
	private WizardService wizardService;
	
	/**
	 * 构建工具首页
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-31 下午06:05:01
	 * @return ModelAndView
	 */
	@RequestMapping(value="/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wizard/index");
		mav.addObject("tables", wizardService.getAllTables());
		
		return mav;
	}
	
	/**
	 * 生成文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 上午10:11:23
	 * @return ModelAndView
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException
	{
		String entityName 		= request.getParameter("entityname");
		String entityZhName 	= request.getParameter("entityzhname");
		String dao 				= request.getParameter("dao");
		String service 			= request.getParameter("service");
		String[] controllers	= request.getParameterValues("controller");
		_projectDir = DirUtil.fixPath(request.getParameter("project_dir"));
		if(Verify.isEmpty(_projectDir)) {
			request.setAttribute("message", "工程目录不能为空");
			
			return new ModelAndView("forward:/user/wizard/index.do");
		}
		entityName 		= StringUtil.ufirst(StringUtil.replaceUnderlineAndfirstToUpper(entityName, "_", ""));
		String entityNameFirstLow = StringUtil.lfirst(entityName);
		try {
			if(!Verify.isEmpty(dao)) {
				//生成Dao 文件
				_makeDaoFile(entityName);
			}
			if(!Verify.isEmpty(service)) {
				//生成Service文件
				_makeServiceFile(entityName, entityZhName, entityNameFirstLow);
			}
			if(null != controllers && 0 < controllers.length) {
				//生成Controller文件
				for(String controller : controllers) {
					_makeControllerFile(controller, entityName, entityZhName, entityNameFirstLow);
				}
			}
			request.setAttribute("message", "模块生成成功");
		} catch (Exception e) {
			request.setAttribute("message", "模块生成失败");
		}
		
		return new ModelAndView("forward:/user/wizard/index.do");
	}

	/**
	 * 生成Dao文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午09:00:04
	 * @return void
	 * @throws IOException 
	 * @throws NotFoundException 
	 * @throws TransformerException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	private void _makeDaoFile(String entityName) throws NotFoundException, IOException, ParserConfigurationException, SAXException, TransformerException {
		String content 	= FileUtil.getContent(_projectDir + WizardPojo.filesMap.get("dao").get("src")).toString();
		content 	= content.replace("{entityName}", entityName);
		FileUtil.write(
			_projectDir + String.format(
					WizardPojo.filesMap.get("dao").get("desc"), entityName
					), 
			content
		);
		_assignConfig(entityName);
	}

	/**
	 * 生成Service层文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午04:43:23
	 * @return void
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	private void _makeServiceFile(String entityName, String entityZhName, String entityNameFirstLow) throws NotFoundException, IOException {
		String content 	= FileUtil.getContent(_projectDir + WizardPojo.filesMap.get("service").get("src")).toString();
		content 	= content.replace("{entityName}", entityName);
		content 	= content.replace("{entityZhName}", entityZhName);
		content 	= content.replace("{entityNameFirstLow}", entityNameFirstLow);
		content 	= content.replace("{date}", HResponse.formatDateTime(new Date()));
		FileUtil.write(
				_projectDir + String.format(
						WizardPojo.filesMap.get("service").get("desc"), entityName
						),
					content
				);
	}
	
	/**
	 * 生成前台Controller控制文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午05:12:06
	 * @return void
	 * @throws IOException 
	 * @throws NotFoundException 
	 * @throws NotFoundException 
	 */
	private void _makeControllerFile(String app, String entityName, String entityZhName, String entityNameFirstLow) throws IOException, NotFoundException 
	{
		String content 	= FileUtil.getContent(_projectDir + WizardPojo.filesMap.get("controller").get(app + "src")).toString();
		content 	= content.replace("{entityName}", entityName);
		content 	= content.replace("{entityZhName}", entityZhName);
		content 	= content.replace("{entityNameFirstLow}", entityNameFirstLow);
		content 	= content.replace("{date}", HResponse.formatDateTime(new Date()));
		FileUtil.write(
				_projectDir + String.format(
						WizardPojo.filesMap.get("controller").get(app + "desc"), entityName
						), 
				content
				);
	}
	
	/**
	 * 注册配置文件xml
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-3 下午02:55:27
	 * @return void
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	private void _assignConfig(String entityName) throws ParserConfigurationException, IOException, TransformerException, SAXException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder 	= factory.newDocumentBuilder();
		Document document 			= builder.parse(_projectDir + WizardPojo.filesMap.get("config").get("cfg"));
		NodeList beanList 	= document.getElementsByTagName("bean");
		String status 	= null;
		for(int i = 0; i < beanList.getLength(); i ++) {
			Element bean 	= (Element) beanList.item(i);
			if(bean.getAttribute("id").equals(StringUtil.lfirst(entityName) + "Dao")) {
				status 	= entityName;
				break;
			}
		}
		if(!Verify.isEmpty(status)) {
			return;
		}
		//创建节点
		Element newBean 	= document.createElement("bean");
		Element newSubNode 	= document.createElement("property");
		newSubNode.setAttribute("name", "sessionFactory");
		newSubNode.setAttribute("ref", "sessionFactory");
		newBean.appendChild(newSubNode);
		newBean.setAttribute("id", StringUtil.lfirst(entityName) + "Dao");
		newBean.setAttribute("class", "com.mvc.dao." + entityName + "Dao");
		//得到参考节点
		Element refNode 	= (Element) beanList.item(beanList.getLength() - 1);
		//得到根节点
		Element root 		= (Element) document.getElementsByTagName("beans").item(0);
		//插入指定的位置
		root.insertBefore(newBean, refNode);
		//更新xml文件
		TransformerFactory tfFactory 	= TransformerFactory.newInstance();  
		tfFactory.setAttribute("indent-number", new Integer(2));  
		Transformer tf 					= tfFactory.newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");  
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(_projectDir + WizardPojo.filesMap.get("config").get("cfg"))));
	}

	public static void main(String[] args) {
		System.out.println(HResponse.formatDateTime(new Date()));
	}

}
