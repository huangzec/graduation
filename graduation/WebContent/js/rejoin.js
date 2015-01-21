HHJsLib.register({
	init: function(){
		this.bindGetGradeClick();
		this.bindRejoinInfoBtnClick();
		this.bindCheckPassBtn();
	},
	bindGetGradeClick: function(){
		$("#gradeone").click(function(){
			$("#one").removeClass("hidden").addClass("show");
			$("#two").addClass("hidden");
			$("#three").addClass("hidden");
			$("#all").addClass("hidden");
		});
		
		$("#gradetwo").click(function(){
			$("#one").addClass("hidden");
			$("#two").removeClass("hidden").addClass("show");
			$("#three").addClass("hidden");
			$("#all").addClass("hidden");
		});
		
		$("#gradethree").click(function(){
			$("#one").addClass("hidden");
			$("#two").addClass("hidden");
			$("#three").removeClass("hidden").addClass("show");
			$("#all").addClass("hidden");
		});
		
		$("#gradeall").click(function(){
			$("#one").addClass("hidden");
			$("#two").addClass("hidden");
			$("#three").addClass("hidden");
			$("#all").removeClass("hidden").addClass("show");
		});
	},
	
	bindRejoinInfoBtnClick: function(){
		$(".rejoin-info-btn").click(function() {
			var info  = $("#editinfo").val();
			var stuId = $("#stuId").val();
			var state = "2";
			try {
				HHJsLib.isEmpty(info, "修改内容");
				
				var data = {info: info, stuId: stuId, state: state};
				
				$.post(
						siteUrl + "user/rejoin/checkinfo.do",
						data,
						function(response) {
							HHJsLib.info(response.message);
							if(response.rs === true) {
								$(".modal-info").modal("hide");
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
	
	bindCheckPassBtn: function(){
		$(".check-pass").click(function() {
			var stuId = $("#stuId").val();
			var state = "1";
			try {
				var data = {stuId: stuId, state: state};
				
				$.post(
						siteUrl + "user/rejoin/checkinfo.do",
						data,
						function(response) {
							HHJsLib.info(response.message);
							if(response.rs === true) {
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
	
});