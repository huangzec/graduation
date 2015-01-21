package com.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.controller.admin.TeacherController;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeall;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradethree;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.LinkeddataApplyTopicinfo;
import com.mvc.entity.LinkeddataApplyTopicscore;
import com.mvc.entity.Message;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Taskdoc;
import com.mvc.entity.Tballdoc;
import com.mvc.entity.Tbdocnum;
import com.mvc.entity.Teacher;
import com.mvc.entity.Topicfinish;
import com.mvc.entity.Topicreport;
import com.mvc.exception.VerifyException;
import com.mvc.service.GradeallService;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.LinkeddataApplyTopicinfoService;
import com.mvc.service.LinkeddataApplyTopicscoreService;
import com.mvc.service.MessageService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.ProfessionService;
import com.mvc.service.StudentService;
import com.mvc.service.TaskdocService;
import com.mvc.service.TballdocService;
import com.mvc.service.TbdocnumService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TeacherService;
import com.mvc.service.TopicfinishService;
import com.mvc.service.TopicreportService;

/**
 * 学生操作---答辩信息模块控制器
 * 
 * @author Happy_Jqc@163.com
 *
 */

@SuppressWarnings("serial")
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
	private LinkeddataApplyTopicscoreService linkATSService;
	
	@Autowired
	private LinkeddataApplyTopicinfoService linkATIService;
	
	@Autowired
	private TballdocService alldocService;
	
	@Autowired
	private TbdocnumService docnumService;
	
	@Autowired
	private MessageService messageServie;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	
	private int pageNum = 1;//页数
	
	private int numPerPage = 10;//每页显示多少条
	
	private List<Tbdocnum> numlist;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<Tbdocnum> getNumlist() {
		return numlist;
	}

	public void setNumlist(List<Tbdocnum> numlist) {
		this.numlist = numlist;
	}

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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="gettpinfo.do")
	public ModelAndView getTPInfo(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		String rejoin_type = request.getParameter("type");
		String userId = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		String noneinfo = "没有您的答辩时间、地点相关信息，请确认是否提交答辩申请或联系系部管理员！";
		
		if(!Verify.isEmpty(rejoin_type) && rejoin_type.equals("1")){
			List<Opentopicinfo> opinfolist = opiService.getAllRowsByWhere("from Opentopicinfo where stuId ='" + userId + "' order by opiDate desc");
			if(opinfolist != null && opinfolist.size() > 0){
				_assignTeacherName(dept.getDeptId(), request);
				modelMap.put("opinfolist", opinfolist);
			}else{
				request.setAttribute("message", noneinfo);
			}
			
			mav.setViewName("user/rejoinInfo/timeplace-open");
			
		}else if(!Verify.isEmpty(rejoin_type) && rejoin_type.equals("2")){
			List<Graduateinfo> gdilist = gdiService.getAllRowsByWhere("from Graduateinfo where stuId ='" + userId + "' order by gdiDate desc");
			if(gdilist != null && gdilist.size() > 0){
				_assignTeacherName(dept.getDeptId(), request);
				modelMap.put("gdilist", gdilist);
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="opigrade.do")
	public ModelAndView getOpiGrade(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		String userId    = (String) request.getSession().getAttribute("user_id");
		Department dept  = (Department) request.getSession().getAttribute("dept");
		String where  = "from Opentopicscore where createTime = (select MAX(createTime) from Opentopicscore where studentId = '" + userId + "')";
		Opentopicscore opentopicscore = opiscoreService.getRecordByWhere(where);
		LinkeddataApplyTopicscore linkATS = linkATSService.getRecordByWhere("from LinkeddataApplyTopicscore where relId = '" + opentopicscore.getId() + "'");
		LinkeddataApplyTopicinfo  linkATI = linkATIService.getRecordByWhere("from LinkeddataApplyTopicinfo where itemId = '" + linkATS.getItemId() + "'");
		Opentopicinfo opentopicinfo = opiService.getRecordByWhere("from Opentopicinfo where opiId = '" + linkATI.getRelId() + "'");
		modelMap.put("opentopicinfo", opentopicinfo);
		modelMap.put("opentopicscore", opentopicscore);
		_assignTeacherName(dept.getDeptId(), request);
		/*String where 	= "from Opentopicscore where studentId = '" + userId + "' order by createTime desc ";
		List<Opentopicscore> opiscorelist = opiscoreService.getAllRowsByWhere(where);
		String whereIn = null;
		whereIn = SqlUtil.whereIn("itemId", "id", opiscorelist);
		System.out.println(whereIn+"......1");
		
		List<LinkeddataApplyTopicscore> linkATSlist = linkATSService.getAllRowsByWhere("from LinkeddataApplyTopicscore where " + whereIn);
		whereIn = SqlUtil.whereIn("itemId", "itemId", linkATSlist);
		System.out.println(whereIn+"......2");
		
		List<LinkeddataApplyTopicinfo> linkATIlist = linkATIService.getAllRowsByWhere("from LinkeddataApplyTopicinfo where " + whereIn);
		whereIn = SqlUtil.whereIn("opiId", "relId", linkATIlist);
		System.out.println(whereIn+"......3");
		
		List<Opentopicinfo> opinfolist = opiService.getAllRowsByWhere("from Opentopicinfo where " + whereIn);
		
		if((opiscorelist != null && opiscorelist.size() > 0) && (opinfolist != null && opinfolist.size() > 0)){
			modelMap.put("opinfolist", opinfolist);
			modelMap.put("list", opiscorelist);
		}*/
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="gdigrade.do")
	public ModelAndView getgdiGrade(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
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
	 * @throws VerifyException 
	 */
	private Selectfirst _assignSelectfirst(String userId) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="paper.do")
	public ModelAndView getPaper(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute("user_id");
		String where = "from Topicfinish where stuId = '" + userId + "' order by createTime desc";
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
	 * 毕业相关材料详细页 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-3 下午03:16:41
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="papeldetail.do")
	public ModelAndView paperDetail(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String id = request.getParameter("id");
		Topicfinish topicfinish = topicfinishService.getRecordByWhere("from Topicfinish where id = " + id);
		if(!Verify.isEmpty(topicfinish)){
			modelMap.put("topicfinish", topicfinish);
		}else{
			modelMap.put("topicfinish", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-paper-detail");
	}
	
	/**
	 * 
	 * 获取任务书 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-9 上午11:14:17
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="taskdoc.do")
	public ModelAndView getTaskDoc(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute("user_id");
		_assignStudentInfo(request, userId);
		TopicController.assignSelSource(request);
		String where = "from Taskdoc where stuId ='" + userId + "'";
		Taskdoc taskdoc = taskdocService.getRecordByWhere(where);
		TeacherController.assignTeacherposMap(request);
		Selectfirst selectfirst = _assignSelectfirst(userId);
		
		if(!Verify.isEmpty(taskdoc)){
			Teacher teacher = teacherService.getOneTeacherById(taskdoc.getTeaId());
			if(!Verify.isEmpty(teacher)){
				modelMap.put("teacher", teacher);
			}
			if(!Verify.isEmpty(selectfirst)){
				modelMap.put("selectfirst", selectfirst);
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="openreport.do")
	public ModelAndView getOpenReport(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		Selectfirst selectfirst = _assignSelectfirst(userId);
		String where = "from Topicreport where stuId ='" + userId + "'";
		Topicreport topicreport = topicreportService.getRecordByWhere(where);
		
		if(!Verify.isEmpty(selectfirst) && !Verify.isEmpty(topicreport)){
			Opentopicinfo opentopicinfo = opiService.getRecordByWhere("from Opentopicinfo where stuId = '" + userId + "'");
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="gradeone.do")
	public ModelAndView getGradeOne(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="gradetwo.do")
	public ModelAndView getGradeTwo(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="gradethree.do")
	public ModelAndView getGradeThree(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		String where = "from Gradethree where stuId ='" + userId + "'  AND status = '1'";
		Gradethree gradethree = gradethreeService.getRecordByWhere(where);
		Selectfirst selectfirst = _assignSelectfirst(userId);
		if(!Verify.isEmpty(gradethree) && !Verify.isEmpty(selectfirst)){
			Graduateinfo graduateinfo = gdiService.getRecordByWhere("from Graduateinfo where stuId = '" + userId + "'");
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
	 * @throws VerifyException 
	 */
	private void _assignTeacherName(String deptId, HttpServletRequest request) throws VerifyException {
		String where = "from Teacher where deptId = '" + deptId + "'";
		List<Teacher> list = teacherService.getAllRows(where);
		if(list == null || list.size() < 0){
			return;
		}
		request.setAttribute("tea_map", ArrayUtil.turnListToMap("teaId", "teaName", list));
		request.setAttribute("pos_map", ArrayUtil.turnListToMap("teaId", "teaPos", list));
	}
	
	/**
	 * 注册学生上传的资料审核状态Map
	 */
	private static LinkedHashMap<String, String> _docStatusMap =  new LinkedHashMap<String, String>(){{
		put("0", "未审核");
		put("1", "通过");
		put("2", "返回修改");
	}};
	
	/**
	 * 
	 * 加载学生上传的资料审核状态Map
	 *  
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午03:14:53
	 * @return void
	 */
	public static void _assignDocStatusMap(HttpServletRequest request){
		request.setAttribute("docstatus_map", MapUtil.makeLinkedMapMap(_docStatusMap));
	}
	
	/**
	 * 
	 * 上传所有材料 视图
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-26 下午03:55:59
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="alldocview.do")
	public ModelAndView allDocView(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute("user_id");
		Gradeall gradeall = gradeallService.getRecordByWhere("from Gradeall where stuId = '" + userId + "' AND gaGrade >= 60");
		List<Tballdoc> list = alldocService.getAllRowsByWhere("from Tballdoc where stuId = '" + userId + "'");
		Tbdocnum docnum = docnumService.getRecordByWhere("from Tbdocnum where stuId = '" + userId + "'");
		if(!Verify.isEmpty(gradeall)){
			request.setAttribute("OK", "当前可进行操作！");
			if(!Verify.isEmpty(list)){
				_assignDocStatusMap(request);
				modelMap.put("list", list);
				modelMap.put("docnum", docnum);
			}
		}else{
			request.setAttribute("OK", null);
		}
		return new ModelAndView("user/rejoinInfo/doc-alldoc");
	}
	
	/**
	 * 
	 * 上传所有材料 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-26 下午04:11:11
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="alldoc.do")
	public ModelAndView allDoc(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		ModelAndView mav  = new ModelAndView();
		String userId     = (String) request.getSession().getAttribute("user_id");
		Department dept   = (Department) request.getSession().getAttribute("dept");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("filepath");
		String title = multipartFile.getOriginalFilename();
		
		Tbdocnum docnum = docnumService.getRecordByWhere("from Tbdocnum where stuId = '" + userId + "'");
		if(!Verify.isEmpty(docnum) && docnum.getNumstate().equals("2")){
			request.setAttribute("message", "文件正在审核，不能进行上传！");
			mav.setViewName("forward:/user/rejoin/alldocview.do");
			return mav;
		}
		
		//Tballdoc alldoc = alldocService.getRecordByWhere("from Tballdoc where stuId = '" + userId + "'");
		//if(Verify.isEmpty(alldoc)){
			if(multipartFile.getSize() > 0 || (title.length() < 255 && title.length() > 0)){
				try{
					Tballdoc alldoc = new Tballdoc();
					alldoc.setStuId(userId);
					alldoc.setDeptId(dept.getDeptId());
					alldoc.setCreateTime(HResponse.formatDateTime(new Date()));
					alldoc.setDocTitle(title.substring(0, title.lastIndexOf(".")));
					alldoc.setDocContent(title);
					alldoc.setDocState("0");
					this.setFilePath(request, alldoc);
					alldocService.addOne(alldoc);
					request.setAttribute("message", "上传成功！");
					mav.setViewName("redirect:/user/rejoin/alldocview.do");
					if(!Verify.isEmpty(docnum)){
						int num = docnum.getDocnum();
						docnum.setDocnum(num++);
						try{
							docnumService.editOneTbdocnum(docnum);
						}catch(Exception e){
							System.out.println(e.toString());
						}
					}else{
						docnum = new Tbdocnum();
						docnum.setStuId(userId);
						docnum.setNumstate("0");
						docnum.setDocnum(1);
						try{
							docnumService.addOne(docnum);
						}catch(Exception e){
							System.out.println(e.toString());
						}
					}
				}catch(Exception e){
					request.setAttribute("message", "上传失败");
					System.out.println(e.toString());
				}
			}else{
				request.setAttribute("msg", "selectfile");
				mav.setViewName("forward:/user/rejoin/alldocview.do");
				//System.out.println(request.getAttribute("msg")+".............");
			}
		/*}else{
			request.setAttribute("message", "文件已上传！");
			mav.setViewName("forward:/user/rejoin/alldoclist.do");
		}*/
		return mav;
	}
	
	/**
	 * 
	 * edit document info 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午05:59:09
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="editdoc.do")
	public ModelAndView editDoc(HttpServletRequest request) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/rejoin/alldocview.do");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("newfile");
		String title  = multipartFile.getOriginalFilename();
		String docId  = request.getParameter("docId");
		String numId  = request.getParameter("numId");
		String userId = (String) request.getSession().getAttribute("user_id");
		
		Tballdoc doc  = alldocService.getOneRecordById(Integer.parseInt(docId));
		Tbdocnum num  = docnumService.getOneRecordById(Integer.parseInt(numId));
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId = '" + userId + "'");
		if(multipartFile.getSize() > 0 || (title.length() < 255 && title.length() > 0) && !Verify.isEmpty(doc)){
			try {
				doc.setDocTitle(title.substring(0, title.lastIndexOf(".")));
				doc.setDocContent(title);
				this.setFilePath(request, doc);
				alldocService.editOneTballdoc(doc);
				request.setAttribute("message", "文件修改成功！");
				
				num.setNumstate("0");
				docnumService.editOneTbdocnum(num);
				
				_sendMessageToTea(userId, selectfirst.getTeaId(), "文件修改", "的文件-- " + title + "，已作出相应修改，注意查看进行再次审阅！");
			} catch (Exception e) {
				request.setAttribute("message", "文件修改失败！");
			}
		}
		return mav;
	}
	
	/**
	 * 
	 * student send message to teacher
	 *  
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午08:23:38
	 * @return void
	 */
	private void _sendMessageToTea(String fromId, String toId, String name, String content) {
		Student student = studentService.getOneById(fromId);
		Message message = new Message();
		message.setName(name);
		message.setContent("所指导学生 " + student.getStuName() + content);
		message.setFromId(fromId);
		message.setToId(toId);
		Short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		try{
			messageServie.saveMessage(message);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	/**
	 * 
	 * 设置文件路径(file_Path) 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-14 下午03:02:31
	 * @return void
	 */
	private void setFilePath(HttpServletRequest request, Tballdoc alldoc) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("filepath");
		if(multipartFile == null){
			multipartFile = multipartRequest.getFile("newfile");
		}
		if(multipartFile.getSize() <= 0){
			return ;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String filePathDir = "/uploadfiles/" + sdf.format(new Date());
		String realPath = request.getSession().getServletContext().getRealPath(filePathDir);
		System.out.println("RejoinInfoController.setFilePath()" + realPath);
		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		String suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));
		String filename = UUID.randomUUID().toString() + suffix;
		System.out.println("----"+ multipartFile.getOriginalFilename());
		String realfilename = realPath + File.separator + filename;
		File lastfile = new File(realfilename);
		try {
			multipartFile.transferTo(lastfile);
			alldoc.setFilePath(filePathDir + "/" + filename);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * 教师审核材料列表 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-15 下午07:16:40
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="checkview.do")
	public ModelAndView checkDoc(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/check-doc");
		_assignGradeInfo(request);
		
		String userId    = (String) request.getSession().getAttribute("user_id");
		Department dept  = (Department) request.getSession().getAttribute("dept");
		String grade 	 = request.getParameter("grade");
		String where = "from Selectfirst where teaId = '" + userId + "' AND selStatus = '1'";
		if(!Verify.isEmpty(grade)){
			String hql   = "from Student s where s.claId IN (select c.claId from Tbclass c  where c.proId IN (" +
			"select p.proId from Profession p where p.graId = " + Integer.parseInt(grade) +"))";
			List<Student> stuIdlist = studentService.getAllRows(hql);
			String stuWhereIn = SqlUtil.whereIn("stuId", "stuId", stuIdlist);
			where += " AND " + stuWhereIn;
		}
		where += " order by stuId desc";
		List<Selectfirst> stulist = selectfirstDao.getAll(where);
		if(Verify.isEmpty(stulist)){
			request.setAttribute("message", "无指导学生！");
			return mav;
		}
		
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		if(pagination == null){
			pagination = new Pagination(numPerPage);
		}
		pagination.setSize(numPerPage);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		
		String whereIn = SqlUtil.whereIn("stuId", "stuId", stulist);
		List<Tballdoc> doclist = alldocService.getAllRowsByWhere("from Tballdoc where " + whereIn);
		numlist = docnumService.getAllRecordByPages("from Tbdocnum where " + whereIn, pagination);
		
		if(Verify.isEmpty(doclist) || Verify.isEmpty(numlist)) {
			request.setAttribute("message", "当前无学生上传材料！");
			return mav;
		}
		
		if(this.numlist.size() == 0 && pagination.getCurrentPage() != 1){
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			numlist = docnumService.getAllRecordByPages("from Tbdocnum where " + whereIn, pagination);
		}
		
		modelMap.put("doclist", doclist);
		modelMap.put("numlist", numlist);
		modelMap.put("pagination", pagination);
		modelMap.put("grade", grade);
		_assignStuName(request, dept.getDeptId(), whereIn);
		return mav;
		
	}

	
	/**
	 * 
	 * send check info 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午09:16:20
	 * @return void
	 * @throws VerifyException 
	 */
	@RequestMapping(value="checkinfo.do")
	public void checkinfo(HttpServletResponse response, HttpServletRequest request) throws VerifyException{
		String userId    = (String) request.getSession().getAttribute("user_id");
		String numstate  = request.getParameter("state");
		String stuId     = request.getParameter("stuId");
		String info      = request.getParameter("info");
		if(!Verify.isEmpty(numstate) && !Verify.isEmpty(stuId)){
			Tbdocnum docnum        = docnumService.getRecordByWhere("from Tbdocnum where stuId = '" + stuId + "'");
			List<Tballdoc> doclist = alldocService.getAllRowsByWhere("from Tballdoc where stuId = '" + stuId + "'");
			if(!Verify.isEmpty(docnum)){
				docnum.setNumstate(numstate);
				try{
					docnumService.editOneTbdocnum(docnum);
					HResponse.okJSON(response);
					
					for(Tballdoc alldoc : doclist){
						if(!Verify.isEmpty(info)){
							alldoc.setDocState("0");
						}else{
							alldoc.setDocState("1");
						}
						alldocService.editOneTballdoc(alldoc);
					}
					
					if(!Verify.isEmpty(info)){
						_sendMessageToStu(userId, stuId, "材料审核修改信息" , info);
					}else{
						_sendMessageToStu(userId, stuId, "材料审核通过", "你所提交的材料审核已通过！");
					}
				}catch(Exception e){
					System.out.println(e.toString());
					throw new VerifyException("状态提交失败！请稍后再试…");
				}
			}
		}else{
			throw new VerifyException("服务器繁忙，请稍后再试…");
		}
	}
	
	/**
	 * 
	 * teacher send message to student
	 *  
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午09:01:28
	 * @return void
	 * @throws VerifyException 
	 */
	private void _sendMessageToStu(String fromId, String toId, String name, String content) throws VerifyException {
		Message message = new Message();
		message.setName(name);
		message.setContent(content);
		message.setFromId(fromId);
		message.setToId(toId);
		Short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		try{
			messageServie.saveMessage(message);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	/**
	 * 加载年级信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午06:00:58
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGradeInfo(HttpServletRequest request) throws VerifyException 
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + 
			"' order by graNumber desc";
		
		request.setAttribute(
				"gradeList", 
				tbgradeService.getAllRowsByWhere(where)
				);		
	}
	
	/**
	 * 
	 * 加载学生姓名
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-20 下午05:46:54
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStuName(HttpServletRequest request, String deptId, String whereIn) throws VerifyException {
		List<Student> list = studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(list)){
			return;
		}
		request.setAttribute("stuname_map", ArrayUtil.turnListToMap("stuId", "stuName", list));
	}
	
	/**
	 * 
	 * 最终材料显示 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-28 上午09:46:07
	 * @return ModelAndView
	 */
	/*@RequestMapping(value="alldoclist.do")
	public ModelAndView alldoclist(HttpServletRequest request, ModelMap modelMap){
		ModelAndView mav  = new ModelAndView();
		String userId     = (String) request.getSession().getAttribute("user_id");
		Tballdoc tballdoc = alldocService.getRecordByWhere("from Tballdoc where stuId = '" + userId + "'");
		Student student   = studentService.getOneStu("from Student where stuId = '"+ userId + "'");
		if(!Verify.isEmpty(tballdoc)){
			modelMap.put("student", student);
			modelMap.put("alldoc", tballdoc);
			mav.setViewName("user/rejoinInfo/doc-all-list");
		}else{
			request.setAttribute("message", "请上传文件！");
			mav.setViewName("forward:/user/rejoin/alldocview.do");
		}
		return mav;
	}*/
}
