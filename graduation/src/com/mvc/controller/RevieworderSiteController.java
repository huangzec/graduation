package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.Revieworder;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Tbtopic;
import com.mvc.exception.VerifyException;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradetwoService;
import com.mvc.service.RevieworderService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbtopicService;

/**
 * 毕业评阅安排控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/revieworder")
public class RevieworderSiteController {

	@Autowired
	private RevieworderService revieworderService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	private Pagination pagination;
	private List<Revieworder> list = new ArrayList<Revieworder>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Revieworder> getList() {
		return list;
	}

	public void setList(List<Revieworder> list) {
		this.list = list;
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

	/**
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 毕业论文评阅查询视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午05:23:54
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/searchview.do")
	public ModelAndView searchView(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/search-view");
		_assignGradeInfo(request);
		
		return mav;
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
	 * 数据列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap ) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/list");
		Department department = (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		_assignGradeInfo(request);
		String grade 	= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			request.setAttribute("message", "请选择毕业届别");
			
			return mav;
		}
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String where = "from Revieworder where tbgradeId = '" + grade + 
			"' AND departmentId = '" + department.getDeptId() + 
			"' AND teacherId = '" + userid + "' ";
		list = revieworderService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Revieworder>) revieworderService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		_assignStudentListMap(list, request);
		
		return mav;
	}
	
	/**
	 * 加载学生列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<Revieworder> data, HttpServletRequest request) throws VerifyException
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("stuId", "studentId", data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}
	
	/**
	 * 指导老师是否已经评该学生成绩 
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午10:11:11
	 * @return void
	 */
	@RequestMapping(value="/guideteacherjudge.do")
	public void guideTeacherJudge(HttpServletRequest request, HttpServletResponse response)
	{
		String studentId 	= request.getParameter("stuId");
		if(Verify.isEmpty(studentId)) {
			HResponse.errorJSON("学生编号不能为空", response);
			return;
		}
		String where = "from Gradeone where stuId = '" + studentId + 
			"' AND status = '1' order by createTime desc";
		try {
			Gradeone gradeone = gradeoneService.getRecordByWhere(where);
			if(Verify.isEmpty(gradeone)) {
				HResponse.errorJSON("该学生的指导老师尚未评定成绩", response);
				return;
			}
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}	
	
	/**
	 * 评阅教师评分
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午11:34:32
	 * @return void
	 */
	@RequestMapping(value="/scorereview.do")
	public void scoreReview(HttpServletRequest request, HttpServletResponse response)
	{
		String sixScore 	= request.getParameter("six");
		String userId 	= request.getParameter("userId");
		try{
			String where 	= "from Gradetwo where stuId = '" + userId + "' ";
			Gradetwo gradetwo = gradetwoService.getRecordByWhere(where);
			if(!Verify.isEmpty(gradetwo)) {
				HResponse.errorJSON("你已经评过该学生成绩了，不需要再评", response);
				
				return;
			}
			if(!verifyScore(request)) {
				HResponse.errorJSON(request.getAttribute("message").toString(), response);
				
				return;
			}
			Float one 		= Float.parseFloat(request.getParameter("one"));
			Float two 		= Float.parseFloat(request.getParameter("two"));
			Float three 	= Float.parseFloat(request.getParameter("three"));
			Float four 		= Float.parseFloat(request.getParameter("four"));
			Float five 		= Float.parseFloat(request.getParameter("five"));
			Float all 		= one + two + three + four + five;
			Float six 		= (float) 0;
			if(!Verify.isEmpty(sixScore)) {
				six = Float.parseFloat(sixScore);
				all = all + six;
			}
			String content 	= request.getParameter("content");
			gradetwo = new Gradetwo();
			gradetwo.setStuId(userId);
			gradetwo.setGtOne(one);
			gradetwo.setGtTwo(two);
			gradetwo.setGtThree(three);
			gradetwo.setGtFour(four);
			gradetwo.setGtFive(five);
			gradetwo.setGtSix(six);
			gradetwo.setGtAll(all);
			gradetwo.setContent(content);
			gradetwo.setStatus("1");
			gradetwo.setCreateTime(HResponse.formatDateTime(new Date()));
			gradetwoService.addOne(gradetwo);
			
			HResponse.okJSON(response);
		}catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
		}
	}
	
	/**
	 * 验证分数
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @return
	 */
	private boolean verifyScore(HttpServletRequest request)
	{
		try {
			Float one 		= Float.parseFloat(request.getParameter("one"));
			Float two 		= Float.parseFloat(request.getParameter("two"));
			Float three 	= Float.parseFloat(request.getParameter("three"));
			Float four 		= Float.parseFloat(request.getParameter("four"));
			Float five 		= Float.parseFloat(request.getParameter("five"));
			String sixScore = request.getParameter("six");
			Float all 		= one + two + three + four + five;
			Float six 		= (float) 0;
			if(!Verify.isEmpty(sixScore)) {
				six = Float.parseFloat(sixScore);
				all = all + six;
			}
			float[] score = {one, two, three, four, five, six};
			boolean outOfMax = false;
			for(int i = 0; i < score.length; i ++) {
				if(score[i] > 30) {
					request.setAttribute("message", "第 " + (i + 1) + " 项的分值不能超过30分");
					outOfMax = true;
					break;
				}
			}
			if(outOfMax) {
				return false;
			}
			if(all > 100) {
				request.setAttribute("message", "总分不能超过100分，请检查各项分值");
				
				return false;
			}
			
			return true;
			
		} catch (Exception e) {
			request.setAttribute("message", "分值不对，请检查各项分值");
			
			return false;
		}
	}

	/**
	 * 标记已评阅
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午11:43:49
	 * @return void
	 */
	@RequestMapping(value="/deal.do")
	public void deal(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		if(Verify.isEmpty(id)) {
			HResponse.errorJSON("编号不能为空", response);
			return;
		}
		try{
			Revieworder revieworder = revieworderService.getOneRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(revieworder)) {
				HResponse.errorJSON("记录不存在", response);
				return;
			}
			revieworder.setStatus("2");
			revieworderService.editOneRevieworder(revieworder);
			
			HResponse.okJSON("评审成功", response);
		}catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 学生所选课题类型
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/topictype.do")
	public void topicType(HttpServletRequest request, HttpServletResponse response)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String stuId 	= request.getParameter("stuid");
		if(Verify.isEmpty(stuId)) {
			HResponse.errorJSON("学生ID不能为空", response);
			
			return; 	
		}
		String where = "from Tbtopic where topId = (" +
				"select s.tbtopic.topId from Selectfirst s where s.stuId = '" + stuId.trim() + 
				"' AND s.deptId = '" + department.getDeptId() + "')";
		try {
			Tbtopic tbtopic = tbtopicService.getRecordByWhere(where);
			if(Verify.isEmpty(tbtopic)) {
				HResponse.errorJSON("该学生未选课题", response);
				
				return;
			}
			if(Verify.isEmpty(tbtopic.getTopType())) {
				HResponse.errorJSON("该学生所选课题出现异常，请与管理员联系", response);
				
				return;
			}
			String html = "";
			if(tbtopic.getTopType().trim().equals("1")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②与科学研究、工程或生产实际紧密结合；" +
						"③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input type='text' name='one' " +
						"class='input-mini'/></td></tr><tr><td>文献综述与外文翻译</td><td>①能独立查阅文献和从事其他形式调研" +
						"；②能较好理解课题任务并提出实施方案；③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；" +
						"④文献综述撰写规范，外文翻译符合规定要求，译文准确，质量好；⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。" +
						"</td><td>20</td><td><input type='text' name='two' class='input-mini'/></td></tr><tr>" +
						"<td>研究水平与实际能力</td><td>①能独立开展研究工作；②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解" +
						"决相关理论和实际问题；③实验设计合理，实验数据准确可靠，理论分析与计算正确；④有较强的实际动手能力、经济分析能力和现" +
						"代技术应用能力。</td><td>30</td><td><input type='text' name='three' class='input-mini'/>" +
						"</td></tr><tr><td>论文撰写质量</td><td>①论文结构严谨，层次清晰，结论正确，技术用语准确；②行文流畅，语句通畅；" +
						"③论文格式符合规范要求；④图表完备、整洁，符号统一，编号齐全。</td><td>30</td><td><input type='text' " +
						"name='four' class='input-mini'/></td></tr><tr><td>学术水平与创新</td><td>①具有一定的学术水平或应用价值；" +
						"②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td><td>10</td><td>" +
						"<input type='text' name='five' class='input-mini'/></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div>" +
						"</td></tr><tr><td colspan='4'><div class='lfloat' style='width: 100%;'><label>评阅意见：" +
						"</label><textarea rows='10' name='content' style='width: 100%;'></textarea></div></td>" +
						"</tr></table>";
			}else if(tbtopic.getTopType().trim().equals("2")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②注重反映解决社会、经济、文化中的实际问题；" +
						"③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input type='text' name='one' " +
						"class='input-mini'/></td></tr><tr><td>文献综述与外文翻译</td><td>①能独立查阅文献和从事其他形式调研；" +
						"②能较好理解课题任务并提出实施方案；③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；④文献综述撰写规范，" +
						"外文翻译符合规定要求，译文准确，质量好；⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。</td><td>20" +
						"</td><td><input type='text'name='two'class='input-mini'/></td></tr><tr><td>研究水平与实际能力" +
						"</td><td>①能独立开展研究工作；②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；" +
						"③论点正确、鲜明，阐述清楚，对研究的问题有较强的分析和概括能力，有一定深度；④论据充分，材料翔实可靠，说服力强。</td>" +
						"<td>30</td><td><input type='text'name='three'class='input-mini'/></td></tr><tr><td>" +
						"论文撰写质量</td><td>①论文结构严谨，逻辑性强，论述层次清晰；②语句通畅，语言准确、生动；③论文格式符合规范要求；" +
						"④图表完备、整洁，编号齐全。</td><td>30</td><td><input type='text' name='four' class='input-mini'/>" +
						"</td></tr><tr><td>学术水平与创新</td><td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有" +
						"较深刻的认识，有新的见解，有一定的创新。</td><td>10</td><td><input type='text' name='five' " +
						"class='input-mini'/></td></tr><tr><td colspan='4'><div class='rfloat'>总分：" +
						"<input type='text' name='all' class='input-mini'/></div></td></tr><tr><td colspan='4'>" +
						"<div class='lfloat' style='width: 100%;'><label>评阅意见：</label><textarea rows='10' " +
						"name='content' style='width: 100%;'></textarea></div></td></tr></table>";
			}else if(tbtopic.getTopType().trim().equals("3")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②与科学研究、工程或生产实际紧密结合；" +
						"③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input type='text' name='one' " +
						"class='input-mini'/></td></tr><tr><td>文献综述与外文翻译</td><td>①能独立查阅文献和从事其他形式调研；" +
						"②能较好理解课题任务并提出实施方案；③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；④文献综述撰写规范" +
						"，外文翻译符合规定要求，译文准确，质量好；⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。</td><td>" +
						"20</td><td><input type='text'name='two'class='input-mini'/></td></tr><tr><td>设计水平与实际能力" +
						"</td><td>①能独立开展设计工作；②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；" +
						"③设计方案合理可行，数据准确可靠，论证充分，理论分析与计算正确；④有较强的实际动手能力、经济分析能力和现代技术应用能力。" +
						"</td><td>20</td><td><input type='text'name='three'class='input-mini'/></td></tr><tr><td>" +
						"设计说明书撰写质量</td><td>①结构严谨，层次清晰，结论正确，技术用语准确；②行文流畅，语句通畅；③格式符合规范要求；" +
						"④图表完备、整洁，符号统一，编号齐全。</td><td>20</td><td><input type='text' name='four' " +
						"class='input-mini'/></td></tr><tr><td>图纸质量</td><td>①结构合理，工艺可行；②图样绘制与技术要求符" +
						"合国家标准；③图面质量及工作量符合要求。</td><td>20</td><td><input type='text' name='five' " +
						"class='input-mini'/></td></tr><tr><td>学术水平与创新</td><td>①具有一定的学术水平或应用价值；" +
						"②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td><td>10</td><td>" +
						"<input type='text' name='six' class='input-mini'/></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div></td>" +
						"</tr><tr><td colspan='4'><div class='lfloat' style='width: 100%;'><label>评阅意见：</label>" +
						"<textarea rows='10' name='content' style='width: 100%;'></textarea></div></td></tr></table>";
			}
			HResponse.write("{\"rs\": true, \"html\": \"" + html + "\"}", response);
		} catch (Exception e) {
			e.printStackTrace();
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
}
