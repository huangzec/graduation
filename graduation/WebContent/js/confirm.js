HHJsLib.register({
	init: function() {
		this.bindSuccessbtnClick();
		this.bindInfobtnClick();
		this.bindEnterScorebtnClick();
		this.bindEnterScoreModalbtnClick();
		this.bindEnterGraduateScorebtnClick();
		this.bindEnterGraduateScoreModalbtnClick();
	},
	bindPrintbtnClick: function() {
		$("#printbtn").click(function() {
			$("#taskdoc-form")
			.attr("action", siteUrl + "user/taskdoc/preview.do")
			.attr("target", "_blank")
			.submit();
		});
	},
	bindSuccessbtnClick: function() {
		$(".btn-success").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("tr").children("input[name='id']").val();
			var pass 	= _root.attr("pass");
			var type 	= _root.parents("tr").children("input[name='type']").val();
			var userId 	= _root.parents("tr").children("input[name='user_id']").val();
			if(confirm("确认" + _root.text())) {
				if(type == 2) {
					$('#myModal').modal('show');
					$('.modal-btn').unbind().click(function() {
						var _base 	= $('.modal-body');
						var one 	= _base.find("input[name='one']").val();
						var two 	= _base.find("input[name='two']").val();
						var three 	= _base.find("input[name='three']").val();
						var four 	= _base.find("input[name='four']").val();
						var five 	= _base.find("input[name='five']").val();
						var all 	= _base.find("input[name='all']").val();
						var content = _base.find("textarea[name='content']").val();
						var inputFalse;
						var data 	= {userId: userId, one: one, two: two, three: three, four: four, five: five, all: all, content: content};
						_base.find('input').each(function(index) {
							var $inputValue = $(this).val();
							if('' == $inputValue.trim()) {
								alert("第 " + (index + 1) + " 项的分值不能为空！");
								inputFalse = 1;
								return false;
							}
						});
						if(inputFalse == 1) {
							return false;
						}
						if('' == _base.find('textarea').val().trim()) {
							alert("评定意见不能为空");
							return false;
						}
						$.post(
								siteUrl + 'user/apply/scoreteacher.do',
								data,
								function(response) {
									if(response.rs === false) {
										alert(response.message);
										return;
									}
									$('#myModal').modal('hide');
									_base.find('input').each(function() {
										$(this).attr("value", "");
									});
									_base.find('textarea').val('');
									$.getJSON(
											siteUrl + 'user/apply/agree.do',
											{id: id, pass: pass},
											function(response) {
												alert(response.message);
												if(response.rs === true) {
													window.location.reload();
												}							
											}
									);
								},
								"json"
						);
					});
				}
				if(type == 1) {
					$.getJSON(
							siteUrl + 'user/apply/agree.do',
							{id: id, pass: pass},
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
		$(".btn-info").click(function() {
			var _root 	= $(this);
			var type 	= _root.parents("tr").children("input[name='type']").val();
			var userId 	= _root.parents("tr").children("input[name='user_id']").val();
			var id 		= _root.parents("tr").children("input[name='id']").val();
			if(type == 1) {
				window.open(siteUrl + "user/apply/opentopicword.do?userid=" + userId);
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
	bindEnterGraduateScorebtnClick: function() {
		$(".enter-graduate-score-btn").click(function() {
			var _root 	= $(this);
			$("#myModalLabel").children("tag").text(_root.parent().siblings("input[name='student']").val());
			$(".enter-graduate-score").modal("show");
		});
	},
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
			var _all 		= _base.children().find("input[name='all']").val();
			
			var _judgeView 	= _base.children().find("textarea[name='judge-view']").val();
			var _id 		= $(".enter-graduate-score-btn").parent().siblings("input[name='id']").val();
			var _sid 		= $(".enter-graduate-score-btn").parent().siblings("input[name='studentid']").val();
			var _stu 		= $(".enter-graduate-score-btn").parent().siblings("input[name='student']").val();
			var _sttime		= $(".enter-graduate-score-btn").parent().siblings("input[name='starttime']").val();
			var _place 		= $(".enter-graduate-score-btn").parent().siblings("input[name='place']").val();
			var _person 		= $(".enter-graduate-score-btn").parent().siblings("input[name='person']").val();
			
			try {
				HHJsLib.isEmpty(_mecont, "答辩中提出的主要问题及回答的简要情况记录");
				var inputFalse;
				_base.children().find("table").find('input').each(function(index) {
					var $inputValue = $(this).val();
					if('' == $inputValue.trim()) {
						alert("第 " + (index + 1) + " 项的分值不能为空！");
						inputFalse = 1;
						return false;
					}
				});
				if(inputFalse == 1) {
					return false;
				}
				HHJsLib.isEmpty(_judgeView, "答辩小组意见");
				var data = {
						sid: _sid, content: _mecont, hoster: _hoster, recorder: _recorder, 
						one: _one, two: _two, three: _three, four: _four, five: _five, all: _all,
						stu: _stu, judgeview: _judgeView, sttime: _sttime, place: _place, 
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
	}
});