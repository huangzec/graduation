HHJsLib.register({
	init: function() {
		this.bindWizardbtnClick();
	},
	bindPrintbtnClick: function() {
		$("#printbtn").click(function() {
			$("#taskdoc-form")
			.attr("action", siteUrl + "user/taskdoc/preview.do")
			.attr("target", "_blank")
			.submit();
		});
	},
	bindWizardbtnClick: function() {
		$(".wizardbtn").click(function() {
			var name = $("#entityname").val();
			if(name == "") {
				alert("请选择数据表");
				
				return false;
			}
			
		});
	}
});