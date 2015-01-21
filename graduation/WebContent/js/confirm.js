HHJsLib.register({
	init: function() {
		this.bindSuccessbtnClick();
		this.bindInfobtnClick();
		this.bindEnterScorebtnClick();
		this.bindEnterScoreModalbtnClick();
		this.bindEnterGraduateScorebtnClick();
		this.bindEnterGraduateScoreModalbtnClick();
		this.bindGraduateUnpassbtnClick();
	},
	bindPrintbtnClick: function() {
		$("#printbtn").click(function() {
			$("#taskdoc-form")
			.attr("action", siteUrl + "user/taskdoc/preview.do")
			.attr("target", "_blank")
			.submit();
		});
	},
	/**
	 * 答辩确认
	 */
	bindSuccessbtnClick: function() {
		$(".btn-success").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("tr").children("input[name='id']").val();
			var pass 	= _root.attr("pass");
			var type 	= _root.parents("tr").children("input[name='type']").val();
			var userId 	= _root.parents("tr").children("input[name='user_id']").val();
			if(confirm("确认" + _root.text())) {
				if(type == 2) {
					/**
					 * 毕业答辩确认
					 */
					if(pass == 2) {
						var _base 	= $('.modal-body');
						/**
						 * 评分指标，论文，设计，其他
						 */
						$.getJSON(
								siteUrl + "user/apply/topictype.do",
								{stuid: userId},
								function(rense) {
									if(rense.rs === false) {
										alert(rense.message);
										
										return false;
									}
									$('#myModal').children(".modal-body").empty();
									$('#myModal').children(".modal-body").append(rense.html);

									$('#myModal').modal('show');
									/**
									 * 指导老师查看该学生是否已经给了成绩
									 */
									$.getJSON(
											siteUrl + "user/apply/havescoreteacher.do",
											{userid: userId},
											function(resp) {
												if(resp.rs === true) {
													_base.find("input[name='one']").attr("value", parseInt(resp.data[0].one));
													_base.find("input[name='two']").attr("value", parseInt(resp.data[0].two));
													_base.find("input[name='three']").attr("value", parseInt(resp.data[0].three));
													_base.find("input[name='four']").attr("value", parseInt(resp.data[0].four));
													_base.find("input[name='five']").attr("value", parseInt(resp.data[0].five));
													_base.find("input[name='six']").attr("value", parseInt(resp.data[0].six));
													_base.find("input[name='all']").attr("value", parseInt(resp.data[0].all));
													_base.find("textarea[name='content']").attr("value", resp.data[0].content);
												}
												/**
												 * 指导老师评分 计算总分
												 */
												var $body 	= $("#myModal").find('.modal-body').find("table");
												var $totalcore = $body.find('input:last');
												$totalcore.focus(function() {
													var $item = 0;
													var isNumber;
													var $thattotal;
													var $last;
													var overMax;
													$body.find("input:not(:last)").each(function($index) {
														if(isNaN(parseInt($(this).val()))) {
															$thattotal 	= $(this);
															alert("第 " + ($index + 1) + " 项的分值不是一个有效数字！");
															isNumber = 1;
															return false;
														}
														if(parseInt($(this).val()) > 30) {
															alert("第 " + ($index + 1) + " 项的分值不能超过30分");
															$thattotal = $(this);
															overMax = 1;
															
															return false;
														}
														$item += parseInt($(this).val());
														$last = $(this);
													});
													if(isNumber == 1 || overMax == 1) {
														$thattotal.focus();
														return false;
													}
													if($item > 100) {
														alert("总分不能超过100分，请检查各项分值");
														$last.focus();
														return false;
													}
													$(this).attr("value", $item);
												});
											}
									);
									/**
									 * 指导老师评分 
									 */
									$('.modal-btn').unbind().click(function() {
										var one 	= _base.find("input[name='one']").val();
										var two 	= _base.find("input[name='two']").val();
										var three 	= _base.find("input[name='three']").val();
										var four 	= _base.find("input[name='four']").val();
										var five 	= _base.find("input[name='five']").val();
										var six 	= _base.find("input[name='six']").val();
										var all 	= _base.find("input[name='all']").val();
										var content = _base.find("textarea[name='content']").val();
										var inputFalse;
										var $that;
										var inputScore;
										var maxScore;
										var regexp 	= /^(\d+)(\.\d+)?$/;
										var data 	= {userId: userId, one: one, two: two, three: three, four: four, five: five, six: six, all: all, content: content};
										_base.find('input:not(:last)').each(function(index) {
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
										if('' == _base.find('textarea').val()) {
											alert("评定意见不能为空");
											_base.find('textarea').focus();
											return false;
										}
										$.post(
												siteUrl + 'user/apply/scoreteacher.do',
												data,
												function(rnse) {
													if(rnse.rs === false) {
														alert(rnse.message);
														return;
													}
													$('#myModal').modal('hide');
													_base.find('input').each(function() {
														$(this).attr("value", "");
													});
													_base.find('textarea').val('');
													$.getJSON(
															siteUrl + 'user/apply/agree.do',
															{id: id, pass: pass, userid: userId},
															function(rpns) {
																alert(rpns.message);
																if(rpns.rs === true) {
																	window.location.reload();
																}							
															}
													);
												},
												"json"
										);
									});
								}
						);
					}else if(pass == 1) {
						$.getJSON(
								siteUrl + 'user/apply/agree.do',
								{id: id, pass: pass, userid: userId},
								function(rspos) {
									alert(rspos.message);
									if(rspos.rs === true) {
										window.location.reload();
									}							
								}
						);
					}
				}else if(type == 1) {
					/**
					 * 开题答辩确认
					 */
					$.getJSON(
							siteUrl + 'user/apply/agree.do',
							{id: id, pass: pass, userid: userId},
							function(response) {
								alert(response.message);
								if(response.rs === true) {
									window.location.reload();
								}							
							}
					);
				}
			}
		});
	},
	bindInfobtnClick: function() {
		/**
		 * 查看文档
		 */
		$(".btn-info").click(function() {
			var _root 	= $(this);
			var type 	= _root.parents("tr").find("input[name='type']").val();
			var userId 	= _root.parents("tr").find("input[name='user_id']").val();
			var id 		= _root.parents("tr").find("input[name='id']").val();
			if(type == 1) {
				window.open(siteUrl + "user/apply/opentopicword.do?id=" + id +"&userid=" + userId);
			} else if(type == 2){
				window.open(siteUrl + "user/apply/finishtopicword.do?id=" + id + "&userid=" + userId);
			}
		});
	},
	bindEnterScorebtnClick: function() {
		$(".enter-score-btn").click(function() {
			var _root 	= $(this);
			$("#myModalLabel").children("tag").text(_root.parent().siblings("input[name='student']").val());
			$(".enterscore").modal("show");
		});
	},
	bindEnterScoreModalbtnClick: function() {
		$(".enter-score-modal-btn").click(function() {
			var _base 		= $(".modal-body");
			var _score 		= _base.children().find("input[name='score']").val();
			var _mecont 	= _base.children().find("textarea[name='meeting-content']").val();
			var _hoster 	= _base.children().find("input[name='hoster']").val();
			var _recorder 	= _base.children().find("input[name='recorder']").val();
			var _judgeView 	= _base.children().find("textarea[name='judge-view']").val();
			var _sid 		= $(".enter-score-btn").parent().siblings("input[name='studentid']").val();
			var _opdate 	= $(".enter-score-btn").parent().siblings("input[name='opdate']").val();
			var _opplace 	= $(".enter-score-btn").parent().siblings("input[name='opplace']").val();
			var _opjudge 	= $(".enter-score-btn").parent().siblings("input[name='opjudge']").val();
			var _opid	 	= $(".enter-score-btn").parent().siblings("input[name='opid']").val();
			var data = {
					sid: _sid, score: _score, mcont: _mecont, hoster: _hoster, recorder: _recorder, 
					judgeview: _judgeView, opdate: _opdate, opplace: _opplace, opjudge: _opjudge, opid: _opid
					};
			try {
				HHJsLib.isEmpty(_score, "分数");
				HHJsLib.isEmpty(_mecont, "会议记录摘要");
				HHJsLib.isEmpty(_judgeView, "开题答辩小组意见");
				$.post(
						siteUrl + 'user/opentopicinfo/enterscorecontent.do',
						data,
						function(response) {
							HHJsLib.info(response.message);
							if(response.rs === true) {
								_base.children().find("input[name='score']").val("");
								_base.children().find("textarea[name='meeting-content']").val("");
								_base.children().find("input[name='hoster']").val("");
								_base.children().find("input[name='recorder']").val("");
								_base.children().find("textarea[name='judge-view']").val("");
								$(".enterscore").modal("hide");
								window.location.reload();
							}
						},
						'json'
				);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	},
	/**
	 * 录入毕业答辩成绩
	 */
	bindEnterGraduateScorebtnClick: function() {
		$(".enter-graduate-score-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parent().siblings("input[name='id']").val();
			var userId 	= _root.parent().siblings("input[name='studentid']").val();
			$("#myModalLabel").children("tag").text(_root.parent().siblings("input[name='student']").val());
			/**
			 * 学生所选课题类型
			 */
			$.getJSON(
					siteUrl + "user/graduateinfo/topictype.do",
					{stuid: userId},
					function(response) {
						if(response.rs === false) {
							alert(response.message);
							
							return false;
						}
						$("#modal-table").empty();
						$("#modal-table").append(response.html);
						$(".enter-graduate-score").modal("show");
						var _base 	= $('.modal-body');
						$.getJSON(
								siteUrl + 'user/graduateinfo/havegraduatescore.do',
								{id: id, userid: userId},
								function(resp) {
									if(resp.rs === true) {
										_base.find("textarea[name='content']")
											.attr("value", ('null' == resp.data[0].content ? "" : resp.data[0].content));
										_base.find("input[name='hoster']")
											.attr("value", ('null' == resp.data[0].hoster ? "" : resp.data[0].hoster));
										_base.find("input[name='recorder']")
											.attr("value", ('null' == resp.data[0].recorder ? "" : resp.data[0].recorder));
										_base.find("input[name='one']").attr("value", resp.data[0].one);
										_base.find("input[name='two']").attr("value", resp.data[0].two);
										_base.find("input[name='three']").attr("value", resp.data[0].three);
										_base.find("input[name='four']").attr("value", resp.data[0].four);
										_base.find("input[name='five']").attr("value", resp.data[0].five);
										_base.find("input[name='six']").attr("value", resp.data[0].six);
										_base.find("input[name='all']").attr("value", resp.data[0].all);
										_base.find("textarea[name='judge-view']")
											.attr("value", ('null' == resp.data[0].judgeview ? "" : resp.data[0].judgeview));
										/**
										 * 毕业答辩教师 评分总分
										 */
										var $body 	= $('.modal-body').children().find("table");
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
								}
						);
						
					}
			);
		});
	},
	/**
	 * 确认提交毕业答辩成绩
	 */
	bindEnterGraduateScoreModalbtnClick: function() {
		$(".enter-graduate-score-modal-btn").click(function() {
			var _base 		= $(".modal-body");
			var _mecont 	= _base.children().find("textarea[name='content']").val();
			var _hoster 	= _base.children().find("input[name='hoster']").val();
			var _recorder 	= _base.children().find("input[name='recorder']").val();
			var _one 		= _base.children().find("input[name='one']").val();
			var _two 		= _base.children().find("input[name='two']").val();
			var _three 		= _base.children().find("input[name='three']").val();
			var _four 		= _base.children().find("input[name='four']").val();
			var _five 		= _base.children().find("input[name='five']").val();
			var _six 		= _base.children().find("input[name='six']").val();
			var _all 		= _base.children().find("input[name='all']").val();
			
			var _judgeView 	= _base.children().find("textarea[name='judge-view']").val();
			var _id 		= $(".enter-graduate-score-btn").parent().siblings("input[name='id']").val();
			var _sid 		= $(".enter-graduate-score-btn").parent().siblings("input[name='studentid']").val();
			var _stu 		= $(".enter-graduate-score-btn").parent().siblings("input[name='student']").val();
			var _sttime		= $(".enter-graduate-score-btn").parent().siblings("input[name='starttime']").val();
			var _place 		= $(".enter-graduate-score-btn").parent().siblings("input[name='place']").val();
			var _person 		= $(".enter-graduate-score-btn").parent().siblings("input[name='person']").val();
			
			try {
				HHJsLib.isEmptyByDom(
						_base.children().find("textarea[name='content']"),
						"答辩中提出的主要问题及回答的简要情况记录"
						);
				HHJsLib.isEmptyByDom(
						_base.children().find("input[name='hoster']"),
						"会议主持人"
						);
				HHJsLib.isEmptyByDom(
						_base.children().find("input[name='recorder']"),
						"会议记录人"
						);
				var inputFalse;
				var $that;
				var inputScore;
				var maxScore;
				var regexp 	= /[\d]+/;
				_base.children().find("table").find('input:not(:last)').each(function(index) {
					var $inputValue = $(this).val();
					if('' == $inputValue.trim()) {
						alert("第 " + (index + 1) + " 项的分值不能为空！");
						$that = $(this);
						inputFalse = 1;
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
				if(!isNaN(parseInt(_base.children().find("table").find('input:last').val()))) {
					if(parseInt(_base.children().find("table").find('input:last').val()) > 100) {
						alert("总分不超过100分，请检查各项分值");
						_base.children().find("table").find('input:last').focus();
						return false;
					}
				}
				HHJsLib.isEmptyByDom(
						_base.children().find("textarea[name='judge-view']"), 
						"答辩小组意见"
						);
				var data = {
						sid: _sid, content: _mecont, hoster: _hoster, recorder: _recorder, 
						one: _one, two: _two, three: _three, four: _four, five: _five, six: _six, 
						all: _all, stu: _stu, judgeview: _judgeView, sttime: _sttime, place: _place, 
						person: _person, id: _id
						};
				$.post(
						siteUrl + 'user/graduateinfo/enterscorecontent.do',
						data,
						function(response) {
							HHJsLib.info(response.message);
							if(response.rs === true) {
								_base.children().find("input[name='score']").val("");
								_base.children().find("textarea[name='content']").val("");
								_base.children().find("input[name='hoster']").val("");
								_base.children().find("input[name='recorder']").val("");
								_base.children().find("textarea[name='judge-view']").val("");
								$(".enter-graduate-score").modal("hide");
								window.location.reload();
							}
						},
						'json'
				);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	},
	/**
	 * 毕业答辩不通过
	 */
	bindGraduateUnpassbtnClick: function() {
		$(".graduate-unpass-btn").click(function() {
			if(confirm("确定该学生毕业答辩不通过吗")) {
				var id = $(".enter-graduate-score-btn").parent().siblings("input[name='id']").val();
				try {
					$.getJSON(
							siteUrl + 'user/graduateinfo/graduateunpass.do',
							{id: id},
							function(response) {
								HHJsLib.info(response.message);
								if(response.rs === true) {
									window.location.reload();
								}
							}
					);
				} catch (e) {
					return HHJsLib.warn(e);
				}
			}
		});
	}
});