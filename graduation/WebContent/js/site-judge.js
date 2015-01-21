HHJsLib.register({
	init: function() {
		this.bindBtnPassClick();
		this.bindBtnRepassClick();
		this.bindModalBtn();
		this.bindToggleClick();
		this.bindModalClick();
		this.bindSubModalClick();
		this.bindSwitchBtn();
		this.bindSubTopicClick();
		this.bindSelectcaseBtnClick();
		this.bindSelectcaseCannotBtnClick();
		this.bindReviewOrderSearch();
		this.bindReviewJudgeBtnClick();
		this.bindReviewJudgeTeacherBtn();
		this.bindReviewLookBtnClick();
		this.bindTopicSelectCuryear();
		this.bindJudgeTopicLazyload();
		HHJsLib.autoSelect("#cur-year");
		HHJsLib.autoSelect("#revieworder-btn");
	},
	bindBtnPassClick: function() {
//		$("#judge-form").find(".pass").click(function() {
//			$.getJSON(
//					siteUrl + 'judge/pass.do',
//					{data: ''},
//					function(response) {
//						
//					}
//			);
//			HHJsLib.info("审核通过");
//		});
	},
	bindBtnRepassClick: function() {
		$("#judge-form").find(".repass").click(function() {
			if(!confirm("确定不通过吗？")) {
				return false;
			}
		});
	},
	bindModalBtn: function() {
		$("#modalBtn").click(function() {
			alert("ok");
			$('#myModal').modal('toggle');
		});
	},
	bindToggleClick: function(){
		$("#judge-form").find(".name").click(function() {
			var _root = $(this);
			var tag = _root.attr("tag");
			$(".sontopic" + tag).toggle();
		});
	},
	bindModalClick: function() {
		/**
		 * 采用技术模态框
		 */
		$("#judge-form").find(".content").click(function() {
			var _root 	= $(this);
			var header 	= _root.siblings(".header").html();
			var body 	= _root.siblings(".body").html();
			var tec 	= _root.attr("tec");
			var _base 	= $("#myModal");
			_base.find(".myModalLabel").html(header);
			_base.find(".modal-body").html(body);
			_base.modal("show");
		});
	},
	bindSwitchBtn: function() {
		$(".switchbtn").change(function() {
		    //var $el = $(data.el), value = data.value;
		    //console.log(e, $el, value);
			alert("jjjjjj");
		});
	},
	bindSubModalClick: function() {
		/**
		 * 对课题认识模态框
		 */
		$("#judge-form").find(".know").click(function() {
			var _root 	= $(this);
			var header 	= _root.siblings(".header").html();
			var body 	= _root.siblings(".body").html();
			var _base 	= $("#myModal");
			_base.find(".myModalLabel").html(header);
			_base.find(".modal-body").html(body);
			var subknow 	= _root.attr("subknow");
			_base.modal("show");
		});
	},
	bindSubTopicClick: function() {
		$("#judge-form").find(".subsontopic").click(function() {
			var _root 	= $(this);
			var subson 	= _root.attr("subson");
			$("." + subson).toggle();
		});
	},
	bindSelectcaseBtnClick: function() {
		/**
		 * 确认为任务人
		 */
		$(".selectcase-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("td").find("input[name='item']").val();
			var topid 	= _root.parents("td").find("input[name='subtopid']").val();
			if(confirm("确认为任务人？")){
				$.getJSON(
						siteUrl + 'user/topic/confirmworker.do?worker=ok',
						{id: id, topid: topid},
						function(response) {
							alert(response.message);
							if(response.rs === true) {
								window.location.reload();
							}
						}
				);
			}
			
		});
	},
	bindSelectcaseCannotBtnClick: function() {
		/**
		 * 确认为非任务人
		 */
		$(".selectcasecannot-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("td").find("input[name='item']").val();
			var topid 	= _root.parents("td").find("input[name='subtopid']").val();
			var topicpe = _root.parents("td").find("input[name='topictype']").val();
			if(confirm("确认为非任务人吗？")){
				$.getJSON(
						siteUrl + 'user/topic/confirmworker.do?worker=no',
						{id: id, topid: topid, topicpe: topicpe},
						function(response) {
							alert(response.message);
							if(response.rs === true) {
								window.location.reload();
							}
						}
				);
			}
		});
	},
	bindReviewOrderSearch: function() {
		/**
		 * 毕业届别判断
		 */
		$('#revieworder-btn').change(function() {
			try{
				HHJsLib.isEmpty($('.grade').val(), '毕业届别');
				window.location.href = siteUrl + "user/revieworder/list.do?id=" + 
				encodeURIComponent(siteUrl) + "&grade=" + $(this).val(); 
			}catch(e){
				
				return HHJsLib.warn(e);
			}
		});
	},
	/**
	 * 评阅教师点击评分
	 */
	bindReviewJudgeBtnClick: function() {
		$(".review-judge-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parent("td").siblings().find("input[name='id']").val();
			var stuId	= _root.parent("td").siblings().find("input[name='studentid']").val();
			try{
				HHJsLib.isEmpty(id, '编号');
				HHJsLib.isEmpty(stuId, '学生学号');
				/**
				 * 学生所选课题类型
				 */
				$.getJSON(
						siteUrl + 'user/revieworder/topictype.do',
						{stuid: stuId},
						function(resp) {
							if(resp.rs === false) {
								alert(resp.message);
								
								return false;
							}
							/**
							 * 指导老师是否已经评分
							 */
							$.getJSON(
									siteUrl + 'user/revieworder/guideteacherjudge.do',
									{stuId: stuId},
									function(response) {
										if(response.rs === false) {
											return HHJsLib.warn(response.message);
										}
										$('#myModal').children(".modal-body").empty();
										$('#myModal').children(".modal-body").append(resp.html);
										$('#myModal').modal('show');
										$('#myModal').children("input[name='modal-student']").attr('value', stuId);
										$('#myModal').children("input[name='modal-id']").attr('value', id);
										/**
										 * 评阅教师 评分总分
										 */
										var $body 	= $('.modal-body');
										var $totalScore = $body.find('input:last');
										$totalScore.focus(function() {
											var $item = 0;
											var isNumber;
											var $that;
											$body.find("input:not(:last)").each(function($index) {
												if(isNaN(parseInt($(this).val()))) {
													$that 	= $(this);
													alert("第 " + ($index + 1) + " 项的分值不是一个有效数字！");
													isNumber = 1;
													return false;
												}
												$item += parseInt($(this).val());
											});
											if(isNumber == 1) {
												$that.focus();
												return false;
											}
											if($item > 100) {
												alert("总分不能超过100分，请检查各项分值");
												$that.focus();
												return false;
											}
											$(this).attr("value", $item);
										});
									}
							);
						}
				);
			}catch (e) {
				
				return HHJsLib.warn(e);
			}
		});
	},
	bindReviewJudgeTeacherBtn: function() {
		/**
		 * 评阅教师评分
		 */
		$('.review-judge-teacher-btn').click(function() {
			var _root 	= $(this);
			var userId 	= $('#myModal').children("input[name='modal-student']").val();
			var id 		= $('#myModal').children("input[name='modal-id']").val();
			var _base 	= $('.modal-body');
			var one 	= _base.find("input[name='one']").val();
			var two 	= _base.find("input[name='two']").val();
			var three 	= _base.find("input[name='three']").val();
			var four 	= _base.find("input[name='four']").val();
			var five 	= _base.find("input[name='five']").val();
			var six 	= _base.find("input[name='six']").val();
			var all 	= _base.find("input[name='all']").val();
			var content = _base.find("textarea[name='content']").val();
			var inputFalse;
			var inputScore;
			var maxScore
			var $that;
			var regexp 	= /[\d]+/;
			var data 	= {
					userId: userId, one: one, two: two, three: three, four: four, five: five,
					six: six, all: all, content: content
					};
			_base.find('input:not(:last)').each(function(index) {
				//不包括最后一项：总分项
				var $inputValue = $(this).val();
				if('' == $inputValue.trim()) {
					alert("第 " + (index + 1) + " 项的分值不能为空！");
					inputFalse = 1;
					$that = $(this);
					return false;
				}
				if(!regexp.test($inputValue.trim())) {
					alert("第 " + (index + 1) + " 项的分值不是一个有效数字！");
					inputScore = 1;
					$that = $(this);
					return false;
				}
				if($inputValue.trim() > 30) {
					alert("第 " + (index + 1) + " 项的分值不能超过最高分30分！");
					maxScore = 1;
					$that = $(this);
					return false;
				}
			});
			if(inputFalse == 1 || inputScore == 1 || maxScore == 1) {
				$that.focus();
				return false;
			}
			if(!isNaN(parseInt(_base.find('input:last').val()))) {
				if(parseInt(_base.find('input:last').val()) > 100) {
					alert("总分不超过100分，请检查各项分值");
					_base.find('input:last').focus();
					return false;
				}
			}
			if('' == _base.find('textarea').val().trim()) {
				alert("评阅意见不能为空");
				_base.find('textarea').focus();
				return false;
			}
			try {
				$.post(
						siteUrl + 'user/revieworder/scorereview.do',
						data,
						function(response) {
							if(response.rs === false) {
								return HHJsLib.warn(response.message);
							}
							$('#myModal').modal('hide');
							_base.find('input').each(function() {
								$(this).attr("value", "");
							});
							_base.find('textarea').val('');
							$.getJSON(
									siteUrl + 'user/revieworder/deal.do',
									{id: id},
									function(res) {
										if(res.rs === false) {
											return HHJsLib.warn(res.message);
										}
										HHJsLib.info(res.message);
										window.location.reload();
									}
							);
						},
						"json"
				);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	},
	bindReviewLookBtnClick: function() {
		/**
		 * 毕业评阅教师查看文档
		 */
		$('.review-look-btn').click(function() {
			var _root 	= $(this);
			var userId 	= _root.parent('td').siblings().find("input[name='studentid']").val();
			try {
				HHJsLib.isEmpty(userId, '学生学号');
				window.open(siteUrl + "user/apply/finishreviewtopicword.do?id=" + encodeURIComponent($(location).attr('href')) + "&userid=" + userId);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	},
	bindTopicSelectCuryear: function() {
		/**
		 * 课题选择，当前年份变化事件
		 */
		$("#cur-year").change(function() {
			var curYear 	= $(this).val();
			if("" == curYear) {
				return false;
			}
			var href 	= window.location.href;
			var regex 	= /year/;
			var parm 	= /\?/;
			if(!parm.test(href)) {
				//不带参数
				window.location.href = href + "?year=" + curYear;				
			}else {
				//带参数
				if(!regex.test(href)) {
					//如果没有year参数
					window.location.href = href + "&year=" + curYear;
				}else {
					var reg = /(year=[0-9]+)/g;
					href 	= href.replace(reg, "year=" + curYear);
					window.location.href = href;
				}				
			}
		});
	},
	bindJudgeTopicLazyload: function() {
		/*
		 * 课题评审列表懒加载
		 */
		var $that = $("#judge-box");
		var ids = [];
		if(!($that.find(".record").attr("data-cur") == 1)) {
			return false;
		}
		$that.find(".is-judged").each(function() {
			ids.push("'" + $(this).attr("data-cur") + "'");
		});
		try {
			$.getJSON(
					siteUrl + "judge/agetlist.do",
					{ids: ids},
					function(response) {
						if(response.rs == false) {
							return false;
						}
						if(typeof response == "undefined" || typeof response.data == "undefined" || 1 > response.data.length) {
							return;
						}
						var list 		= response.data;
						/**
						 * 隐藏已评审过的
						 */
						for(var i = 0; i < list.length; i ++) {
							var da = list[i];
							$that.find(".is-judged").each(function() {
								var curValue = $(this).attr("data-cur");
								if(curValue == da.id) {
									$(this).css("background", "none");
									$(this).empty().append("<span style=\"color: red;\">已评</span>");
									return true;	
								} 
							});
						}
						setTimeout(function() {
							$that.find(".is-judged").css("background", "none");
							$that.find(".is-judged a").show();
						}, 2000);
					}
			);			
		} catch (e) {
			return HHJsLib.warn(e);
		}
	}
});