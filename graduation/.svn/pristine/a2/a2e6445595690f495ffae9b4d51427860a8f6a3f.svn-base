HHJsLib.register({
	init: function() {
		this.bindPrintbtnClick();
		this.bindGobackbtnClick();
		this.bindSenddocbtnClick();
		this.bindCurrentDateTime();
		this.bindSendbtnClick();
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
	}
});