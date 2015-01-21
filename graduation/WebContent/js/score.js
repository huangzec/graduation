DWZ.regPlugins.push(function($p){
	/**
	 * 开题答辩成绩，查询，年级变化事件
	 */
	$("#score-search").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#score-search");
		var grade 	= _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").remove();
					_base.find(".inprofession").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"professid\" class=\"inprofession\">" +
							options + 
							"</select>";
					_root.after(profession);
					/**
					 * 专业变化事件
					 */
					_base.find(".inprofession").bind("change", function() {
						var ingrade = _base.find(".ingrade").val();
						var profess = _base.find(".inprofession").val();
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
								function(da) {
									_base.find(".inclass").remove();
									if(typeof da == "undefined" || typeof da.data == "undefined" || 1 > da.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= da.data;
									for(var i = 0; i < list.length; i ++) {
										var d = list[i];
										option += "<option value='" + d.id + "'>" + d.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"inclass student-class \">" +
											option +
											"</select>";
									_base.find(".inprofession").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 开题答辩成绩，查询，按钮提交事件
	 */
	$(".score-search-btn").click(function() {
		var grade 		= $(".ingrade").val();
		var profess 	= $(".inprofession").val();
		var tbclass 	= $(".inclass").val();
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
	 * 开题答辩成绩，查询列表中，年级变化事件
	 */
	$("#score-search-list").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#score-search-list");
		var grade 	= _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").remove();
					_base.find(".inprofession").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"professid\" class=\"inprofession\">" +
							options + 
							"</select>";
					_root.after(profession);
					/**
					 * 专业变化事件
					 */
					_base.find(".inprofession").bind("change", function() {
						var ingrade = _base.find(".ingrade").val();
						var profess = _base.find(".inprofession").val();
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
								function(da) {
									_base.find(".inclass").remove();
									if(typeof da == "undefined" || typeof da.data == "undefined" || 1 > da.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= da.data;
									for(var i = 0; i < list.length; i ++) {
										var d = list[i];
										option += "<option value='" + d.id + "'>" + d.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"inclass student-class \">" +
											option +
											"</select>";
									_base.find(".inprofession").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 毕业论文总成绩，查询，年级变化事件
	 */
	$("#graduation-score-search").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#graduation-score-search");
		var grade 	= _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").remove();
					_base.find(".inprofession").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"professid\" class=\"inprofession\">" +
							options + 
							"</select>";
					_root.after(profession);
					/**
					 * 专业变化事件
					 */
					_base.find(".inprofession").bind("change", function() {
						var ingrade = _base.find(".ingrade").val();
						var profess = _base.find(".inprofession").val();
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
								function(da) {
									_base.find(".inclass").remove();
									if(typeof da == "undefined" || typeof da.data == "undefined" || 1 > da.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= da.data;
									for(var i = 0; i < list.length; i ++) {
										var d = list[i];
										option += "<option value='" + d.id + "'>" + d.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"inclass student-class \">" +
											option +
											"</select>";
									_base.find(".inprofession").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 毕业论文总成绩，查询，按钮提交事件
	 */
	$(".graduate-search-btn").click(function() {
		var grade 		= $(".ingrade").val();
		var profess 	= $(".inprofession").val();
		var tbclass 	= $(".inclass").val();
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
	 * 毕业论文总成绩，查询，年级变化事件
	 */
	$("#graduation-score-search-list").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#graduation-score-search-list");
		var grade 	= _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").remove();
					_base.find(".inprofession").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"professid\" class=\"inprofession\">" +
							options + 
							"</select>";
					_root.after(profession);
					/**
					 * 专业变化事件
					 */
					_base.find(".inprofession").bind("change", function() {
						var ingrade = _base.find(".ingrade").val();
						var profess = _base.find(".inprofession").val();
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
								function(da) {
									_base.find(".inclass").remove();
									if(typeof da == "undefined" || typeof da.data == "undefined" || 1 > da.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= da.data;
									for(var i = 0; i < list.length; i ++) {
										var d = list[i];
										option += "<option value='" + d.id + "'>" + d.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"inclass student-class \">" +
											option +
											"</select>";
									_base.find(".inprofession").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 系部所有学生材料，查询，年级变化事件
	 */
	$("#alldoc-search").find(".ingrade").bind("change", function() {
		var _root 	= $(this);
		var _base 	= $("#alldoc-search");
		var grade 	= _root.val();
		if("" == grade) {
			alert("请选择年级");
			
			return false;
		}
		$.post(
				basePath + "admin/profession/ajaxloaddata.do",
				{grade: grade},
				function(dat) {
					_base.find(".inclass").remove();
					_base.find(".inprofession").remove();
					if(typeof dat == "undefined" || typeof dat.data == "undefined" || 1 > dat.data.length) {
						return;
					}
					var options 	= "<option value=\"\">-- 请选择 --</option>";
					var list 		= dat.data;
					for(var i = 0; i < list.length; i ++) {
						var da = list[i];
						options += "<option value='" + da.id + "'>" + da.name + "</options>";
					}
					var profession = "<select name=\"professid\" class=\"inprofession\">" +
							options + 
							"</select>";
					_root.after(profession);
					/**
					 * 专业变化事件
					 */
					_base.find(".inprofession").bind("change", function() {
						var ingrade = _base.find(".ingrade").val();
						var profess = _base.find(".inprofession").val();
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
								function(da) {
									_base.find(".inclass").remove();
									if(typeof da == "undefined" || typeof da.data == "undefined" || 1 > da.data.length) {
										return;
									}
									var option 	= "<option value=\"\">-- 请选择 --</option>";
									var list 		= da.data;
									for(var i = 0; i < list.length; i ++) {
										var d = list[i];
										option += "<option value='" + d.id + "'>" + d.name + "</options>";
									}
									var tbc = "<select name=\"claid\" class=\"inclass student-class \">" +
											option +
											"</select>";
									_base.find(".inprofession").after(tbc);
								},
								"json"
						);
					});
				},
				"json"
		);
	});
	/**
	 * 系部所有学生材料，查询，按钮提交事件
	 */
	$(".alldoc-search-btn").click(function() {
		var grade 		= $(".ingrade").val();
		var profess 	= $(".inprofession").val();
		var tbclass 	= $(".inclass").val();
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
	 * 开题答辩概况，查询，按钮提交事件
	 */
	$(".optresult-search-btn").click(function() {
		var tbclass 	= $(".inclass").val();
		if(typeof tbclass == "undefined" || "" == tbclass) {
			alert("查询精确到班级，请选择到班级");
				
			return false;
		}
	});
});