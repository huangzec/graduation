package com.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.Verify;
import com.mvc.controller.admin.TeacherController;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeall;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradethree;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Taskdoc;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Teacher;
import com.mvc.entity.Topicfinish;
import com.mvc.entity.Topicreport;
import com.mvc.service.GradeallService;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.ProfessionService;
import com.mvc.service.StudentService;
import com.mvc.service.TaskdocService;
import com.mvc.service.TeacherService;
import com.mvc.service.TopicfinishService;
import com.mvc.service.TopicreportService;

/**
 * 学生操作---答辩信息模块控制器
 * 
 * @author Happy_Jqc@163.com
 *
 */

@Controller
@RequestMapping(value = "/user/rejoin/")
public class RejoinInfoController {
	
	@Autowired
	private OpentopicinfoService opiService;
	
	@Autowired
	private GraduateinfoService gdiService;
	
	@Autowired
	private OpentopicscoreService opiscoreService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
	@Autowired
	private GradethreeService gradethreeService;
	
	@Autowired
	private GradeallService gradeallService;
	
	@Autowired
	private TaskdocService taskdocService;
	
	@Autowired
	private TopicreportService topicreportService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private TopicfinishService topicfinishService;
	
	@Autowired
	private GraduateinfoService greduateinfoService;
	
	@Autowired
	private OpentopicinfoService openinfoService;
	
	/**
	 * 
	 * 进入答辩信息视图 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 下午08:13:21
	 * @return ModelAndView
	 */
	@RequestMapping(value="infoview.do")
	public ModelAndView infoView(){
		return new ModelAndView("user/rejoinInfo/rejoin-info");
	}
	
	/**
	 * 
	 * 进入答辩时间、地点视图 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 下午08:51:09
	 * @return ModelAndView
	 */
	@RequestMapping(value="timeplace.do")
	public ModelAndView timeplaceView(){
		return new ModelAndView("user/rejoinInfo/time-place");
	}
	
	/**
	 * 
	 * 获取答辩时间、地点信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 上午10:25:01
	 * @return ModelAndView
	 */
	@RequestMapping(value="gettpinfo.do")
	public ModelAndView getTPInfo(HttpServletRequest request, ModelMap modelMap){
		ModelAndView mav = new ModelAndView();
		String rejoin_type = request.getParameter("type");
		String userId = (String) request.getSession().getAttribute("user_id");
		String noneinfo = "没有您的答辩时间、地点相关信息，请确认是否提交答辩申请或联系系部管理员！";
		
		System.out.println(rejoin_type+"-----type ---");
		
		if(!Verify.isEmpty(rejoin_type) && rejoin_type.equals("1")){
			Opentopicinfo opiInfo = opiService.getRecordByWhere("from Opentopicinfo where stuId ='" + userId + "'");
			if(!Verify.isEmpty(opiInfo)){
				modelMap.put("opiInfo", opiInfo);
			}else{
				request.setAttribute("message", noneinfo);
			}
			
			mav.setViewName("user/rejoinInfo/timeplace-open");
			
		}else if(!Verify.isEmpty(rejoin_type) && rejoin_type.equals("2")){
			Graduateinfo graduateinfo = gdiService.getRecordByWhere("from Graduateinfo where stuId ='" + userId + "'");
			if(!Verify.isEmpty(graduateinfo)){
				modelMap.put("graduateinfo", graduateinfo);
			}else{
				request.setAttribute("message", noneinfo);
			}
			mav.setViewName("user/rejoinInfo/timeplace-graduate");
		}
		return mav;
	}
	
	/**
	 * 
	 * 进入查看答辩成绩视图 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 下午08:53:09
	 * @return ModelAndView
	 */
	@RequestMapping(value="grade.do")
	public ModelAndView rejoinGradeView(){
		return new ModelAndView("user/rejoinInfo/rejoin-grade");
	}
	
	
	/**
	 * 
	 * 获取开题答辩成绩 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 上午11:30:12
	 * @return ModelAndView
	 */
	@RequestMapping(value="opigrade.do")
	public ModelAndView getOpiGrade(HttpServletRequest request, ModelMap modelMap){
		ModelAndView mav = new ModelAndView();
		String userId = (String) request.getSession().getAttribute("user_id");
		
		Opentopicscore opiscore = opiscoreService.getRecordByWhere("from Opentopicscore where studentId ='" +userId + "'");
		if(!Verify.isEmpty(opiscore)){
			modelMap.put("opiscore", opiscore);
			System.out.println(opiscore.getScore()+"-----");
		}else{
			request.setAttribute("message", "暂无相关记录，请联系系部管理员！");
		}
		mav.setViewName("user/rejoinInfo/grade-opi");
		return mav;
	}
	
	/**
	 * 
	 * 获取毕业答辩成绩，包括成绩表一、二、三的各项成绩 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 上午11:31:55
	 * @return ModelAndView
	 */
	@RequestMapping(value="gdigrade.do")
	public ModelAndView getgdiGrade(HttpServletRequest request, ModelMap modelMap){
		ModelAndView mav = new ModelAndView();
		String userId = (String) request.getSession().getAttribute("user_id");
		
		Gradeone gradeone = gradeoneService.getRecordByWhere("from Gradeone where stuId = '" + userId + "' AND status = '1'");
		Gradetwo gradetwo = gradetwoService.getRecordByWhere("from Gradetwo where stuId = '" + userId + "' AND status = '1'");
		Gradethree gradethree = gradethreeService.getRecordByWhere("from Gradethree where stuId = '" + userId + "' AND status = '1'");
		Gradeall gradeall = gradeallService.getRecordByWhere("from Gradeall where stuId = '" + userId + "' AND status = '1'");
		
		if(!Verify.isEmpty(gradeone)){
			modelMap.put("gradeone", gradeone);
		}
		if(!Verify.isEmpty(gradetwo)){
			modelMap.put("gradetwo", gradetwo);
		}
		if(!Verify.isEmpty(gradethree)){
			modelMap.put("gradethree", gradethree);
		}
		if(!Verify.isEmpty(gradeall)){
			modelMap.put("gradeall", gradeall);
		}
		mav.setViewName("user/rejoinInfo/grade-graduate");
		return mav;
	}
	
	/**
	 * 
	 * 进入答辩材料视图 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 下午08:54:04
	 * @return ModelAndView
	 */
	@RequestMapping(value="document.do")
	public ModelAndView rejoinDocView(){
		return new ModelAndView("user/rejoinInfo/rejoin-doc");
	}
	
	
	/**
	 * 
	 * 加载学生信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 下午03:42:20
	 * @return void
	 */
	private void _assignStudentInfo(HttpServletRequest request, String id){
		String where = "select * from student s ,  tbclass c, profession p where s.stu_ID = '" + 
		id + "' and s.cla_ID = c.cla_ID and c.pro_ID = p.pro_ID";
		Map<String, String> map = studentService.getStudentInfo(where);
		request.setAttribute("student_map", map);
	}
	
	/**
	 * 
	 * 加载专业信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-10 上午10:11:06
	 * @return void
	 */
	private void _assignProfessionInfo(HttpServletRequest request, String id){
		String prowhere = "select * from profession where `pro_ID` = (" +
		"select pro_ID from tbclass where cla_ID = (" +
		"select cla_ID from student where stu_ID = " + id + "))";
		Map<String, String> professionMap = professionService.getLinkedRecordByWhere(prowhere);
		request.setAttribute("profession_map", professionMap);
	}
	
	/**
	 * 
	 * 加载课题选择信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-10 下午04:48:59
	 * @return Selectfirst
	 */
	private Selectfirst _assignSelectfirst(String userId) {
		String selWhere = "from Selectfirst where stuId = '" + userId + "' AND selStatus = '1'";
		Selectfirst selectfirst = selectfirstDao.getOne(selWhere);
		return selectfirst;
	}
	
	/**
	 * 加载指导教师信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 上午10:56:43
	 * @return void
	 */
	private void _assignTeacherInfo(ModelMap modelMap, Selectfirst selectfirst) 
	{
		if(Verify.isEmpty(selectfirst)) {
			return;
		}
		Teacher teacher = teacherService.getOneTeacherById(selectfirst.getTeaId());
		modelMap.put("teacher", teacher);
	}
	
	/**
	 * 
	 * 获取论文信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:12:12
	 * @return ModelAndView
	 */
	@RequestMapping(value="paper.do")
	public ModelAndView getPaper(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		String where = "from Topicfinish where stuId = '" + userId + "' order by createTime asc";
		List<Topicfinish> list = topicfinishService.getAllRowsByWhere(where);
		if(list != null){
			modelMap.put("list", list);
		}else{
			request.setAttribute("message", "暂无相关记录。");
		}
		return new ModelAndView("user/rejoinInfo/doc-paper");
	}
	
	/**
	 * 
	 * 获取任务书 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:14:17
	 * @return ModelAndView
	 */
	@RequestMapping(value="taskdoc.do")
	public ModelAndView getTaskDoc(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		_assignStudentInfo(request, userId);
		String where = "from Taskdoc where stuId ='" + userId + "'";
		Taskdoc taskdoc = taskdocService.getRecordByWhere(where);
		if(!Verify.isEmpty(taskdoc)){
			Teacher teacher = teacherService.getOneTeacherById(taskdoc.getTeaId());
			if(!Verify.isEmpty(teacher)){
				modelMap.put("teacher", teacher);
			}
			modelMap.put("task-doc", taskdoc);
		}else{
			modelMap.put("topic-report", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-taskdoc");
	}
	
	/**
	 * 
	 * 获取开题报告书 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:15:12
	 * @return ModelAndView
	 */
	@RequestMapping(value="openreport.do")
	public ModelAndView getOpenReport(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		Selectfirst selectfirst = _assignSelectfirst(userId);
		String where = "from Topicreport where stuId ='" + userId + "'";
		Topicreport topicreport = topicreportService.getRecordByWhere(where);
		
		if(!Verify.isEmpty(selectfirst) && !Verify.isEmpty(topicreport)){
			Opentopicinfo opentopicinfo = openinfoService.getRecordByWhere("from Opentopicinfo where stuId = '" + userId + "'");
			if(!Verify.isEmpty(opentopicinfo)){
				modelMap.put("open-topic-info", opentopicinfo);
			}
			_assignStudentInfo(request, userId);
			_assignProfessionInfo(request, userId);
			_assignTeacherName(dept.getDeptId(), request);
			TeacherController.assignTeacherposMap(request);
			
			modelMap.put("selectfirst", selectfirst);
			modelMap.put("topic-report", topicreport);
		}else{
			modelMap.put("topic-report", null);
			modelMap.put("open-topic-info", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-openreport");
	}

	/**
	 * 
	 * 获取成绩表一 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:17:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="gradeone.do")
	public ModelAndView getGradeOne(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		String where = "from Gradeone where stuId ='" + userId + "' AND status = '1'";
		Gradeone gradeone = gradeoneService.getRecordByWhere(where);
		_assignProfessionInfo(request, userId);
		_assignStudentInfo(request, userId);
		Selectfirst selectfirst = _assignSelectfirst(userId);
		if(!Verify.isEmpty(gradeone) && !Verify.isEmpty(selectfirst)){
			modelMap.put("selectfirst", selectfirst);
			modelMap.put("grade-one", gradeone);
		}else{
			modelMap.put("grade-one", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-gradeone");
	}
	
	/**
	 * 
	 * 获取成绩表二
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:17:31
	 * @return ModelAndView
	 */
	@RequestMapping(value="gradetwo.do")
	public ModelAndView getGradeTwo(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		String where = "from Gradetwo where stuId ='" + userId + "' AND status = '1'";
		Gradetwo gradetwo = gradetwoService.getRecordByWhere(where);
		_assignProfessionInfo(request, userId);
		_assignStudentInfo(request, userId);
		Selectfirst selectfirst = _assignSelectfirst(userId);
		if(!Verify.isEmpty(gradetwo) && !Verify.isEmpty(selectfirst)){
			modelMap.put("grade-two", gradetwo);
			modelMap.put("selectfirst", selectfirst);
		}else{
			modelMap.put("grade-two", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-gradetwo");
	}
	
	/**
	 * 
	 * 获取成绩表三 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:17:37
	 * @return ModelAndView
	 */
	@RequestMapping(value="gradethree.do")
	public ModelAndView getGradeThree(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		String where = "from Gradethree where stuId ='" + userId + "'  AND status = '1'";
		Gradethree gradethree = gradethreeService.getRecordByWhere(where);
		Selectfirst selectfirst = _assignSelectfirst(userId);
		if(!Verify.isEmpty(gradethree) && !Verify.isEmpty(selectfirst)){
			Graduateinfo graduateinfo = greduateinfoService.getRecordByWhere("from Graduateinfo where stuId = '" + userId + "'");
			if(!Verify.isEmpty(graduateinfo)){
				modelMap.put("graduate-info", graduateinfo);
			}
			
			_assignProfessionInfo(request, userId);
			_assignStudentInfo(request, userId);
			_assignTeacherInfo(modelMap, selectfirst);
			TeacherController.assignTeacherposMap(request);
			_assignTeacherName(dept.getDeptId(), request);
			
			modelMap.put("selectfirst", selectfirst);
			modelMap.put("grade-three", gradethree);
		}else{
			modelMap.put("grade-three", null);
			modelMap.put("graduate-info", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-gradethree");
	}

	/**
	 * 
	 * 加载教师姓名Map 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-15 上午09:33:13
	 * @return void
	 */
	private void _assignTeacherName(String deptId, HttpServletRequest request) {
		String where = "from Teacher where deptId = '" + deptId + "'";
		List<Teacher> list = teacherService.getAllRows(where);
		System.out.println();
		if(list == null || list.size() < 0){
			return;
		}
		request.setAttribute("tea_map", ArrayUtil.turnListToMap("teaId", "teaName", list));
		request.setAttribute("pos_map", ArrayUtil.turnListToMap("teaId", "teaPos", list));
	}
}
