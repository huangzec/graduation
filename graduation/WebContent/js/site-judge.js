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
		this.bindReviewOrderSearch();
		this.bindReviewJudgeBtnClick();
		this.bindReviewJudgeTeacherBtn();
		this.bindReviewLookBtnClick();
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
		$("#judge-form").find(".content").click(function() {
			var _root 	= $(this);
			var tec 	= _root.attr("tec");
			$("#" + tec).modal("show");
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
		$("#judge-form").find(".know").click(function() {
			var _root 	= $(this);
			var subknow 	= _root.attr("subknow");
			$("#" + subknow).modal("show");
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
		$(".selectcase-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("td").find("input[name='item']").val();
			var topid 	= _root.parents("td").find("input[name='subtopid']").val();
			if(confirm("确认为任务人？")){
				$.getJSON(
						siteUrl + 'user/topic/confirmworker.do',
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
	bindReviewOrderSearch: function() {
		$('#revieworder-btn').click(function() {
			try{
				HHJsLib.isEmpty($('.grade').val(), '毕业届别');
			}catch(e){
				
				return HHJsLib.warn(e);
			}
		});
	},
	bindReviewJudgeBtnClick: function() {
		$(".review-judge-btn").click(function() {
			var _root 	= $(this);
			var id 		= _root.parent("td").siblings("input[name='id']").val();
			var stuId	= _root.parent("td").siblings("input[name='studentid']").val();
			try{
				HHJsLib.isEmpty(id, '编号');
				HHJsLib.isEmpty(stuId, '学生学号');
				$.getJSON(
						siteUrl + 'user/revieworder/guideteacherjudge.do',
						{stuId: stuId},
						function(response) {
							if(response.rs === false) {
								return HHJsLib.warn(response.message);
							}
							$('#myModal').modal('show');
							$('#myModal').children("input[name='modal-student']").attr('value', stuId);
							$('#myModal').children("input[name='modal-id']").attr('value', id);
						}
				);
			}catch (e) {
				
				return HHJsLib.warn(e);
			}
		});
	},
	bindReviewJudgeTeacherBtn: function() {
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
				alert("评阅意见不能为空");
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
										//window.location.reload();
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
		$('.review-look-btn').click(function() {
			var _root 	= $(this);
			var userId 	= _root.parent('td').siblings("input[name='studentid']").val();
			try {
				HHJsLib.isEmpty(userId, '学生学号');
				window.open(siteUrl + "user/apply/finishreviewtopicword.do?userid=" + userId);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	}
});