HHJsLib.register({
	init: function() {
		this.bindOpenTimeClick();
		this.bindApplySubmitClick();
		this.bindTypeNextClick();
		this.bindApplyNextClick();
		this.bindApplyPreClick();
		this.bindOpenReportPreClick();
		this.bindFinishPreClick();
		this.bindFinishBtnClick();
	},
	bindSuccessbtnClick: function() {
		$(".btn-success").click(function() {
			var _root 	= $(this);
			var id 		= _root.parents("tr").children("input[name='id']").val();
			var pass 	= _root.attr("pass");
			if(confirm("确认" + _root.text())) {
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
		});
	},
	bindOpenTimeClick: function() {
		$(".btn-info").click(function() {
			var _root 	= $(this);
			var type 	= _root.parents("tr").children("input[name='type']").val();
			var userId 	= _root.parents("tr").children("input[name='user_id']").val();
			if(type == 1) {
				window.open(siteUrl + "user/apply/opentopicword.do?userid=" + userId);
			} else if(type == 2){
				alert("2");
			}
		});
	},
	bindApplySubmitClick: function() {
		$("#apply-btn").click(function() {
			$("#apply-form")
				.attr("action", siteUrl + "user/apply/add.do")
				.submit();
		});
	},
	bindTypeNextClick: function() {
		$(".typenext").click(function() {
			var _root = $(this);
			var applyType 	= $("#apply-form").find("input[name='type']:checked").val();
			if(applyType == 1) {
				/**
				 * 开题答辩申请 检测时间和是否已经通过答辩 
				 */
				$.getJSON(
						siteUrl + 'user/apply/checktime.do',
						{mark: "5"},
						function(response) {
							if(response.rs === false) {
								$("#message").text(response.message);
								return;
							}
							$(".applytype").addClass("hidden");
							$(".openly").removeClass("openapply").addClass("show");							
						}
				);
			}else if(applyType == 2) {
				/**
				 * 毕业答辩申请
				 */
				$.getJSON(
						siteUrl + 'user/apply/checktime.do',
						{mark: "6"},
						function(response) {
							if(response.rs === false) {
								$("#message").text(response.message);
								return;
							}
							$(".applytype").addClass("hidden");
							$(".finish").removeClass("hidden").addClass("show");						
						}
				);
			}
		});
	},
	bindApplyNextClick: function() {
		$(".applynext").click(function() {
			$(".openly").removeClass("show").addClass("openapply");
			$(".openrt").removeClass("openreport").addClass("show");
		});
	},
	bindApplyPreClick: function() {
		$(".applypre").click(function() {
			$(".openly").removeClass("show").addClass("openapply");
			$(".applytype").removeClass("hidden");
		});
	},
	bindOpenReportPreClick: function() {
		$(".openreportpre").click(function() {
			$(".openrt").removeClass("show").addClass("openreport");
			$(".openly").removeClass("openapply").addClass("show");
		});
	},
	bindFinishPreClick: function() {
		$(".finishpre").click(function() {
			$(".finish").removeClass("show").addClass("hidden");
			$(".applytype").removeClass("hidden").addClass("show");
		});
	},
	bindFinishBtnClick: function() {
		$("#finish-btn").click(function() {
			$("#apply-form")
				.attr("action", siteUrl + "user/apply/add.do")
				.submit();
		});
	}
});