 HHJsLib.register({
    init: function() {
        this.bindSelectTopicbtn();
        this.bindEditAddBtn();
        this.bindEditSubBtn();
    },
	bindSelectTopicbtn: function() {
		//选择课题提交
		$("#topic-sub-btn").click(function() {
			if(!confirm("确定提交吗")) {
				return false;
			}
			$("#selection-form").submit();
		});
	},
	
	bindEditAddBtn: function() {
		$(".edit-numadd-btn").live("click", function() {
			
			var number  = parseInt($("#topNum").val());
			var nameone = $("#sonName1").val();
			var nametwo = $("#sonName2").val();
			var namethr = $("#sonName3").val();
			var namefou = $("#sonName4").val();
			var html = "";
			
			if(number == 1){
				html += "<div class='control-group' id='sondiv1'><div class='controls'>";
				html += "子课题名称一：<input name='sTName1' class='input-medium'";
				if(nameone != null){
					html += " value='" + nameone + "'";
				}
				html += " type='text' style='width:497px;' />";
				html += "<button type='button' class='btn edit-numsub-btn' style='margin-left:7px;'>删除</button>";
				html += "</div></div><div class='control-group' id='sondiv2'><div class='controls'>";
				html += "子课题名称二：<input name='sTName2' class='input-medium'"
				if(nametwo != null){
					html += +" value='" + nametwo + "'";
				}
				html += " type='text' style='width:497px;' />";
				html += "</div></div>";
				$("#numdiv").after(html);
				number = number + 1;
				$("#topNum").val(number);
				return;
			}
			if(number == 2){
				html = "";
				html += "<div class='control-group' id='sondiv3'><div class='controls'>";
				html += "子课题名称三：<input name='sTName3' class='input-medium'"
				if(namethr != null){
					html += +" value='" + namethr + "'";
				}
				html += " type='text' style='width:497px;' />";
				html += "</div></div>";
				$("#sondiv2").after(html);
				number = number + 1;
				$("#topNum").val(number);
				return;
			}
			if(number == 3){
				html = "";
				html += "<div class='control-group' id='sondiv4'><div class='controls'>";
				html += "子课题名称四：<input name='sTName4' class='input-medium'"
				if(namefou != null){
					html += +" value='" + namefou + "'";
				}
				html += " type='text' style='width:497px;' />";
				html += "</div></div>";
				$("#sondiv3").after(html);
				number = number + 1;
				$("#topNum").val(number);
				return;
			}
		});
		/*$(".edit-numadd-btn").click(function() {
			
		});*/
	},
	
	bindEditSubBtn: function() {
		$(".edit-numsub-btn").live("click", function() {
			var number = parseInt($("#topNum").val());
			if(number == 2){
				$("#sondiv1").remove();
				$("#sondiv2").remove();
				$("#topNum").val("1");
				return;
			}else if(number == 3){
				$("#sondiv3").remove();
				number = number - 1;
				$("#topNum").val(number);
				return;
			}else if(number == 4){
				$("#sondiv4").remove();
				number = number - 1;
				$("#topNum").val(number);
				return;
			}
		});
		/*$(".edit-numsub-btn").click(function() {
			
		});*/
	}
 });