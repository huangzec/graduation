DWZ.regPlugins.push(function($p){
	$("#judge-btn").click(function() {
		var ids = [];
		$("#judge-form").find("input[name='id']:checked").each(function() {
			var _root = $(this);
			ids.push("'" + _root.val() + "'");
		});
		$.ajax({
            type: "POST",
            url: basePath + "admin/topic/judgeorder.do",
            data: {ids:ids},
            dataType: "json",
            success: function(data){
            	if(data == true) {
            		alert("已完成评审老师选择");
            	}
             }
        });
	});
	/**
	 * 开题答辩安排，列表，年级变化事件
	 */
	$("#open-topic-list-search").find(".ingrade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$(".inprofession").remove();
					$(".inclass").remove();
					if(typeof dat == "undefined" || dat.data == null || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"profess\" class=\"inprofession\">" +
							options +
							"</select>";
					_root.after(profession);
					/**
					 * 开题答辩安排，列表， 专业变化事件
					 */
					$("#open-topic-list-search").find(".inprofession").bind("change", function() {
						var _base 	= $(this);
						var ingrade = $("#open-topic-list-search").find(".ingrade").val();
						var profess = _base.val();
						if("" == ingrade) {
							alert("请选择年级");
							
							return false;
						}
						if("" == profess) {
							alert("请选择专业");
							
							return false;
						}
						$.post(
								basePath + "admin/tbclass/ajaxloaddata.do",
								{grade: ingrade, profess: profess},
								function(dat) {
									$(".inclass").remove();
									if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
										return;
									}
									var options 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= dat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										options += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var tbc = "<select name=\"inclass\" class=\"inclass\">" +
									options +
									"</select>";
									_base.after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
		
	});
	/**
	 * 安排开题答辩，组别选择变化事件
	 */
	$(".begroup").unbind().change(function() {
		var _root 	= $(this);
		var begroup = _root.val();
		var place 	= $(".place").val();
		if("" == begroup) {
			alert("请选择组别");
			
			return false;
		}
		if("" == place) {
			alert("教室不能为空");
			
			return false;
		}
		$.getJSON(
				basePath + 'admin/opentopicinfo/abegroup.do',
				{place: place, begroup: begroup},
				function(response) {
					if(response.rs == false) {
						_root.find("option[value='']").attr("selected", true);
						alert(response.message);
					}
				}
		);
	});
	/**
	 * 毕业答辩安排，组别变化事件
	 */
	$("#grade-order-room").find(".grade-begroup").unbind().change(function() {
		var _root 	= $(this);
		var begroup = _root.val();
		var place 	= $(".place").val();
		if("" == begroup) {
			alert("请选择组别");
			
			return false;
		}
		if("" == place) {
			alert("教室不能为空");
			
			return false;
		}
		$.getJSON(
				basePath + 'admin/graduateinfo/abegroup.do',
				{place: place, begroup: begroup},
				function(response) {
					if(response.rs == false) {
						_root.find("option[value='']").attr("selected", true);
						alert(response.message);
					}
				}
		);
	});
	/**
	 * 安排答辩，组长选择点击事件
	 */
	$(".header").unbind().click(function() {
		var teaid 	= $(".teaid").val();
		var teaname = $("textarea[name='tea.name']").val();
		var teaids 	= new Array();
		teaids 			= teaid.split(",");
		var teanames 	= new Array();
		teanames 		= teaname.split(",");
		if(typeof teaids == 'undefined' || teaids == "") {
			alert("请先选择评审教师");
			
			return false;
		}
		var isLoaded 	= $(this).attr("data-loaded");
		$(".selec").removeClass("hidden").addClass("show");
		$(".selec").empty();
		for(var i = teaids.length - 1; i >= 0; i --) {
			$(".selec").prepend("<option value=\"" + teaids[i] + "\">" + teanames[i] + "</option>");		
		}
	});
	/**
	 * 毕业答辩安排，列表，年级变化事件
	 */
	$("#grade-list-search").find(".ingrade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$(".inprofession").remove();
					$(".inclass").remove();
					if(typeof dat == "undefined" || dat.data == null || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"profess\" class=\"inprofession\">" +
							options +
							"</select>";
					_root.after(profession);
					/**
					 * 毕业答辩安排，列表， 专业变化事件
					 */
					$("#grade-list-search").find(".inprofession").bind("change", function() {
						var _base 	= $(this);
						var ingrade = $("#grade-list-search").find(".ingrade").val();
						var profess = _base.val();
						if("" == ingrade) {
							alert("请选择年级");
							
							return false;
						}
						if("" == profess) {
							alert("请选择专业");
							
							return false;
						}
						$.post(
								basePath + "admin/tbclass/ajaxloaddata.do",
								{grade: ingrade, profess: profess},
								function(dat) {
									$(".inclass").remove();
									if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
										return;
									}
									var options 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= dat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										options += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var tbc = "<select name=\"inclass\" class=\"inclass\">" +
									options +
									"</select>";
									_base.after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
		
	});
	/**
	 * 毕业评阅教师安排,修改毕业评阅教师,确定修改提交事件
	 */
	$("#review-edit").find(".reviewedit-btn").unbind().click(function() {
		var _root 	= $("#review-edit");
		var teaId 	= _root.find("input[name='tea.id']").val();
		if("" == teaId) {
			alert("新评阅教师不能为空");
			
			return false;
		}
	});
	/**
	 * 毕业评阅教师安排，列表，年级变化事件
	 */
	$("#grade-review-list-search").find(".ingrade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$(".inprofession").remove();
					$(".inclass").remove();
					if(typeof dat == "undefined" || dat.data == null || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"profess\" class=\"inprofession\">" +
							options +
							"</select>";
					_root.after(profession);
					/**
					 * 毕业评阅教师安排，列表， 专业变化事件
					 */
					$("#grade-review-list-search").find(".inprofession").bind("change", function() {
						var _base 	= $(this);
						var ingrade = $("#grade-review-list-search").find(".ingrade").val();
						var profess = _base.val();
						if("" == ingrade) {
							alert("请选择年级");
							
							return false;
						}
						if("" == profess) {
							alert("请选择专业");
							
							return false;
						}
						$.post(
								basePath + "admin/tbclass/ajaxloaddata.do",
								{grade: ingrade, profess: profess},
								function(dat) {
									$(".inclass").remove();
									if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
										return;
									}
									var options 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= dat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										options += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var tbc = "<select name=\"inclass\" class=\"inclass\">" +
									options +
									"</select>";
									_base.after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
		
	});
	/**
	 * 毕业评阅教师安排，条件选择，确定提交事件
	 */
	$(".review-order-btn").click(function() {
		var _root 	= $(this);
		var _base 	= $("#review-where");
		var grade 	= _base.find(".ingrade").val();
		var profess = _base.find(".inprofession").val();
		var tbclass = _base.find(".inclass").val();
		if((!(profess == null) && !("" == profess)) && "" == grade) {
			alert("请先选择年级");
				
			return false;
		}
		if((!(tbclass == null) && !("" == tbclass)) && "" == profess) {
			alert("请先选择专业");
			
			return false;
		}
		if("" == grade) {
			alert("年级不能为空");
			
			return false;
		}
	});
	/**
	 * 课题信息，年级变化事件
	 */
	$("#topic-form").find(".topicgrade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$("#topic-form").find(".topicprofession").empty();
					$("#topic-form").find(".topicclass").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					/*var profession = "<lable class=\"loaded\">专　业：" +
							"<select name=\"profession\" class=\"profession\">" +
							options +
							"</select></lable>";*/
					$("#topic-form").find(".topicprofession").append(options);
				},
				"json"
		);
	});
	/**
	 * 课题信息，专业变化事件
	 */
	$("#topic-form").find(".topicprofession").unbind().change(function() {
		var _root 	= $(this);
		var _base 	= $("#topic-form").find(".topicgrade");
		var grade 	= _base.val();
		var profess = _root.val();
		if(typeof profess == "undefined" || "" == profess) {
			alert("请选择专业");
			return false;
		}
		if(typeof grade == "undefined" || "" == grade) {
			alert("请先选择年级");
			return false;
		}
		$.post(
				basePath + "admin/tbclass/ajaxloaddata.do",
				{grade: grade, profess: profess},
				function(dat) {
					$("#topic-form").find(".topicclass").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					$("#topic-form").find(".topicclass").append(options);
				},
				"json"
		);
	});
	/**
	 * 课题信息，检索点击事件
	 */
	$("#topic-form").find(".topic-search").click(function() {
		var _root 	= $(this);
		var _base 	= $("#topic-form");
		var grade 	= _base.find(".topicgrade").val();
		var profess = _base.find(".topicprofession").val();
		var tbclass = _base.find(".topicclass").val();
		var suber	= _base.find("input[name='submiter']").val();
		var tag 	= _base.find("input[name='tag']").val();
		if((!(profess == null) && !("" == profess)) && "" == grade) {
			alert("请先选择年级");
				
			return false;
		}
		if((!(tbclass == null) && !("" == tbclass)) && "" == profess) {
			alert("请先选择专业");
			
			return false;
		}
	});
	/**
	 * 班级管理，年级变化事件
	 */
	$(".grade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$(".profession").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					/*var profession = "<lable class=\"loaded\">专　业：" +
							"<select name=\"profession\" class=\"profession\">" +
							options +
							"</select></lable>";*/
					$("#class-form").find(".profession").append(options);
				},
				"json"
		);
	});
	/**
	 * 班级管理检索提交事件
	 */
	$(".grade-btn").click(function() {
		var _root = $(this);
		var grade 		= $("#class-form").find(".grade").val();
		var profession 	= $("#class-form").find(".profession").val();
		if(profession != "" && grade == "") {
			alert("请选择年级");
			
			return false;
		}
	});
	/**
	 * 学生管理，年级变化事件
	 */
	$("#student-form").find(".topicgrade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$("#student-form").find(".topicprofession").remove();
					$("#student-form").find(".topicclass").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var topicfess 	= "<select name=\"profession\" class=\"topicprofession\">" +
							options + 
							"</select>";
					$("#student-form").find(".topicgrade").after(topicfess);
					/**
					 * 学生管理，专业选择框变化事件
					 */
					$("#student-form").find(".topicprofession").unbind().change(function() {
						var _root 	= $(this);
						var grade 	= $("#student-form").find(".topicgrade").val();
						var profess = _root.val();
						if("" == grade) {
							alert("请选择年级");
							
							return false;
						}
						if("" == profess) {
							alert("请选择专业");
							
							return false;
						}
						$.post(
								basePath + "admin/tbclass/ajaxloaddata.do",
								{grade: grade, profess: profess},
								function(dat) {
									$("#student-form").find(".topicclass").remove();
									if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= dat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										option += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var topcla = "<select name=\"tbclass\" class=\"topicclass\">" +
										option +	
										"</select>";
									$("#student-form").find(".topicprofession").after(topcla);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 学生管理，检索学生
	 */
	$("#student-form").find(".stu-search").unbind().click(function() {
		var _root 	= $(this);
		var _base 	= $("#student-form");
		var grade 	= _base.find(".topicgrade").val();
		var profess = _base.find(".topicprofession").val();
		var tbclass = _base.find(".topicclass").val();
		if((!(profess == null) && !("" == profess)) && "" == grade) {
			alert("请先选择年级");
				
			return false;
		}
		if((!(tbclass == null) && !("" == tbclass)) && "" == profess) {
			alert("请先选择专业");
			
			return false;
		}
	});
	/**
	 * 学生管理，添加学生 年级变化事件
	 */
	$("#add-student-grade").find(".add-student-grade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_root.nextAll().remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"profession\" id=\"add-student-profess\" class=\"profession required\">" 
							+ options +
							"</select>";
					$("#add-student-grade").find(".add-student-grade").after(profession);
					/**
					 * 学生管理，添加学生 专业变化事件
					 */
					$("#add-student-profess").bind("change", function() {
						var _root 	= $(this);
						var grade 	= $("#add-student-grade").find(".add-student-grade").val();
						var profess = _root.val();
						if("" == profess) {
							alert("请选择专业");
							
							return false;
						}
						if("" == grade) {
							alert("请先选择年级");
							
							return false;
						}
						$.post(
								basePath + "admin/tbclass/ajaxloaddata.do",
								{grade: grade, profess: profess},
								function(dat) {
									$("#add-student-grade").find(".student-class").remove();
									if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
										return;
									}
									var options 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= dat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										options += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"claid student-class required\">" +
									options +
									"</select>";
									$("#add-student-grade").find("#add-student-profess").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
		
	});
	/**
	 * 学生管理，编辑学生，年级变化事件
	 */
	$("#edit-student-form").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#edit-student-form");
		var grade 	= _base.find(".ingrade").val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").empty();
					_base.find(".inprofession").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					_base.find(".inprofession").append(options);
				},
				"json"
		);
	});
	/**
	 * 学生管理，编辑学生，专业变化事件
	 */
	$("#edit-student-form").find(".inprofession").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#edit-student-form");
		var grade 	= _base.find(".ingrade").val();
		var profess = _root.val();
		var tbclass = _base.find(".inclass").val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		if("" == profess) {
			alert("请选择专业");
			
			return false;
		}
		$.post(
				basePath + "admin/tbclass/ajaxloaddata.do",
				{grade: grade, profess: profess},
				function(dat) {
					_base.find(".inclass").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					_base.find(".inclass").append(options);
				},
				"json"
		);
	});
	
	/**
	 * 
	 */
	$("#add-class-grade").find(".profession").unbind().change(function() {
		var _root 	= $(this);
		var grade 	= $("#add-class-grade").find(".add-class-grade").val();
		var profess = _root.val();
		$.post(
				basePath + "admin/tbclass/ajaxloaddata.do",
				{grade: grade, profess: profess},
				function(dat) {
					$("#add-class-grade").find(".topicclass").empty();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var tbc = "<lable class=\"loaded\">" +
					"<select name=\"claid\" class=\"claid required\">" +
					options +
					"</select></lable>";
					$("#add-class-grade").find(".loaded").after(tbc);
				},
				"json"
		);
	});
	/**
	 * 添加班级，年级变化事件
	 */
	$(".add-class-grade").unbind().change(function() {
		var _root = $(this);
		var grade = _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					$(".loaded").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<lable class=\"loaded\">" +
							"<select name=\"parent_id\" class=\"profession required\">" +
							options +
							"</select></lable>";
					$("#add-class-grade").find(".add-class-grade").after(profession);
				},
				"json"
		);
		
	});
	/**
	 * 添加班级保存按钮
	 */
	$(".add-class-btn").click(function() {
		var _root = $(this);
		var grade = $("#add-class-grade").find(".add-class-grade").val();
		var text  = $("#add-class-grade").find(".add-class-grade").children("option:selected").text();
		var profe = $("#add-class-grade").find(".profession").val();
		if(typeof profe == 'undefined') {
			alert("请先添加" + text + "专业");
			
			return false;
		}
	});
	/**
	 * 添加学生提交事件
	 */
	$(".add-student").click(function() {
		var _root = $(this);
		var claid = $("#add-student-grade").find(".claid").val();
		if(typeof claid == "undefined" || claid == null || "" == claid || 1 > claid.length) {
			alert("班级不正确，请确认");
			
			return false;
		}
	});
	/**
	 * 学生转系，系部变化事件
	 */
	$("#change-student-form").find(".change-department").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#change-student-form");
		var dept 	= _root.val();
		if("" == dept) {
			alert("请选择要转到的系部");
			
			return false;
		}
		$.post(
				basePath + "admin/grade/ajaxloaddata.do",
				{dept: dept},
				function(dat) {
					_base.find(".newgrade").remove();
					_base.find(".newprofession").remove();
					_base.find(".newclass").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var gradeHtml = "<select name=\"\" class=\"newgrade\">" +
							options +
							"</select></lable>";
					_root.after(gradeHtml);
					
					/**
					 * 学生转系，年级变化事件
					 */
					_base.find(".newgrade").bind("change", function() {
						var newgrade 	= $(this).val();
						if("" == dept) {
							alert("请选择要转到的系部");
							
							return false;
						}
						if("" == newgrade) {
							alert("请选择要转到的年级");
							
							return false;
						}
						$.post(
								basePath + "admin/profession/ajaxloaddata.do",
								{dept: dept, grade: newgrade},
								function(newgradedat) {
									_base.find(".newprofession").remove();
									_base.find(".newclass").remove();
									if(typeof newgradedat == "undefined" || typeof newgradedat.data == "undefined" || 1 > newgradedat.data.length) {
										return;
									}
									var options 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= newgradedat.data;
									for(var i = 0; i < list.length; i ++) {
										var da = list[i];
										options += "<option value='" + da.id + "'>" + da.name + "</options>";
									}
									var professionHtml = "<select name=\"\" class=\"newprofession\">" +
											options +
											"</select></lable>";
									_base.find(".newgrade").after(professionHtml);
									
									/**
									 * 学生转系，专业变化事件
									 */
									_base.find(".newprofession").bind("change", function() {
										var newprofession 	= $(this).val();
										if("" == dept) {
											alert("请选择要转到的系部");
											
											return false;
										}
										if("" == newgrade) {
											alert("请选择要转到的年级");
											
											return false;
										}
										if("" == newprofession) {
											alert("请选择要转到的专业");
											
											return false;
										}
										$.post(
												basePath + "admin/tbclass/ajaxloaddata.do",
												{dept: dept, grade: newgrade, profess: newprofession},
												function(professdat) {
													_base.find(".newclass").remove();
													if(typeof professdat == "undefined" || typeof professdat.data == "undefined" || 1 > professdat.data.length) {
														return;
													}
													var options 	= "<option value=\"\">-- 请选择 --</option>";
													var list 		= professdat.data;
													for(var i = 0; i < list.length; i ++) {
														var da = list[i];
														options += "<option value='" + da.id + "'>" + da.name + "</options>";
													}
													var classHtml = "<select name=\"new-claid\" class=\"newclass\">" +
															options +
															"</select></lable>";
													_base.find(".newprofession").after(classHtml);
												},
												"json"
										);
									});
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 确定转系，提交按钮
	 */
	$("#change-student-form").find(".change-btn").click(function() {
		if(!confirm("确定转系吗")) {
			return false;
		}
	});
});