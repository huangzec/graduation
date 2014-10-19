DWZ.regPlugins.push(function($p){
	$("#judge-btn").click(function() {
		var ids = [];
		$("#judge-form").find("input[name='id']:checked").each(function() {
			var _root = $(this);
			ids.push("'" + _root.val() + "'");
		});
		$.ajax({
            type: "POST",
            url: basePath + "admin/topic/judgeorder.do",
            data: {ids:ids},
            dataType: "json",
            success: function(data){
            	if(data == true) {
            		alert("已完成评审老师选择");
            	}
             }
        });
	});
	$(".header").unbind().click(function() {
		var teaid 	= $(".teaid").val();
		var teaname = $("textarea[name='tea.name']").val();
		var teaids 	= new Array();
		teaids 			= teaid.split(",");
		var teanames 	= new Array();
		teanames 		= teaname.split(",");
		if(typeof teaids == 'undefined' || teaids == "") {
			alert("请先选择评审教师");
			
			return false;
		}
		var isLoaded 	= $(this).attr("data-loaded");
		$(".selec").removeClass("hidden").addClass("show");
		if(2 == isLoaded) {
			return false;
		}
		for(var i = teaids.length - 1; i >= 0; i --) {
			$(".selec").prepend("<option value=\"" + teaids[i] + "\">" + teanames[i] + "\</option>");		
		}
		$(this).attr("data-loaded", "2");
	});
});