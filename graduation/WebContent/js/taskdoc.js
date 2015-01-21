HHJsLib.register({
	init: function() {
		this.bindPrintbtnClick();
		this.bindGobackbtnClick();
		this.bindSenddocbtnClick();
		this.bindCurrentDateTime();
		this.bindSendbtnClick();
		this.bindTaskeditbtn();
		this.bindTaskEdit();
		this.bindTaskdocLazyload();
	},
	bindPrintbtnClick: function() {
		$("#printbtn").click(function() {
			$("#taskdoc-form")
			.attr("action", siteUrl + "user/taskdoc/preview.do")
			.attr("target", "_blank")
			.submit();
		});
	},
	bindGobackbtnClick: function() {
		$("#gobackbtn").click(function() {
			history.go(-1);
		});
	},
	bindSenddocbtnClick: function() {
		$(".senddocbtn").click(function() {
			var _root 	= $(this);
			var task 	= _root.attr("task");
			var topicid = $(".topicid" + task).val();
			var taskerid = $(".taskerid" + task).val();
			window.location.href = siteUrl + "user/taskdoc/senddocview.do?topicid=" + topicid + "&taskerid=" + taskerid;
		});
	},
	bindCurrentDateTime: function() {
		var now= new Date();
		var year=now.getFullYear();
		var month=now.getMonth();
		var date=now.getDate();
		$("#receiptTime").attr("value", year + "-" + (month + 1) + "-" + date);
	},
	bindSendbtnClick: function() {
		$("#sendbtn").click(function() {
			$("#taskdoc-form")
				.attr("action", siteUrl + "user/taskdoc/send.do")
				.submit();
		});
	},
	bindTaskeditbtn: function() {
		/**
		 * 任务书编辑视图
		 */
		$("#edit-btn").click(function() {
			var topicid = $(this).siblings("input[name='topicid']").val();
			var taskerid = $(this).siblings("input[name='taskerid']").val();
			try {
				HHJsLib.isEmpty(taskerid, '学生不能为空');
				$.getJSON(
						siteUrl + "user/taskdoc/checkedit.do",
						{taskerid: taskerid},
						function(response) {
							if(response.rs == false) {
								return HHJsLib.warn(response.message);
							}
							window.location.href = siteUrl + "user/taskdoc/editview.do?topicid=" + 
							topicid + "&taskerid=" + taskerid;
						}
				);
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	},
	bindTaskEdit: function() {
		/**
		 * ajax编辑任务书
		 */
		$("#task-edit").click(function() {
			var $that 	= $("#taskdoc-form").find(".table");
			var title 		= $that.find("input[name='title']").val();
			var taskerid 	= $that.find("input[name='taskerid']").val();
			var source 		= $that.find("input[name='source']").val();
			var Request 	= conRequest.getContent();
			var Material 	= refMaterial.getContent();
			var Plane 		= workPlane.getContent();
			var receipttime = $that.find("input[name='receipttime']").val();
			var finishtime 	= $that.find("input[name='finishtime']").val();
			var data = {
					title: title,
					taskerid: taskerid,
					source: source,
					conRequest: Request,
					refMaterial: Material,
					workPlane: Plane,
					receipttime: receipttime,
					finishtime: finishtime
			};
			$.post(
					siteUrl + "user/taskdoc/asend.do",
					data,
					function(response) {
						if(response.rs == false) {
							return HHJsLib.warn(response.message);
						}
						HHJsLib.info(response.message);
						setTimeout(
								window.location.href = siteUrl + "user/taskdoc/senddoclist.do", 
								3000
								);
					},
					'json'
			);
		});
	},
	bindTaskdocLazyload: function() {
		/*
		 * 下发任务书列表懒加载
		 */
		var $that = $("#task-form");
		var ids = [];
		if(!($that.find(".record").attr("cur") == 1)) {
			return false;
		}
		$that.find(".record").each(function() {
			ids.push("'" + $(this).attr("data-cur") + "'");
		});
		try {
			$.getJSON(
					siteUrl + "user/taskdoc/agetlist.do",
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
							$that.find(".is-send").each(function() {
								var curValue = $(this).attr("data-cur");
								if(curValue == da.id) {
									$(this).empty().html(
											"<button class=\"btn btn-success review-btn\" type=\"button\">查看任务书</button>"
											);
									return true;	
								} 
							});
						}
						/**
						 * 查看任务书
						 */
						$(".review-btn").click(function() {
							var topicid = $(this).parent(".is-send").attr("topic-cur");
							var taskerid = $(this).parent(".is-send").attr("data-cur");
							window.location.href = siteUrl + "user/taskdoc/review.do?topicid=" + topicid + "&taskerid=" + taskerid;
						});
					}
			);			
		} catch (e) {
			return HHJsLib.warn(e);
		}
	}
});