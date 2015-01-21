HHJsLib.register({
	init: function(){
		this.bindEnterTeaEditClick();
		this.bindEditOkBtnClick();
		this.bindTopNumChange();
	},
	
	/**
	 * 
	 */
	bindEnterTeaEditClick: function(){
		$(".enter-tea-edit").click(function() {
			var _root     = $(this);
			var _base    = $(".enter-tea-edit");
			var _topId    = _base.parent().find("#topid").val();
			$.getJSON(
					siteUrl + "user/topic/teaeditview.do", 
					{topid: _topId},
					function(response){
						
						if(response.rs == false){
							alert(response.message);
							
							return false;
						}
						
						var type        = _base.parent().find("input[name='toptype']").val();
						var source      = _base.parent().find("#topsource").val();
						var number      = _base.parent().find("#number").val();
						var name        = _base.parent().find("#topname").val();
						var keywords    = _base.parent().find("#keywords").val();
						var topcontent  = _base.parent().find("#topcontent").val();
						
						//alert(number);
						
						$("#tptype").find("input:radio").each(function() {
							var optionValue = $(this).val();
							if(optionValue == type){
								$(this).attr('checked', 'checked');
								return false;
							}
							return $(this).attr('checked', null);
						});
						
						$("#tpsource").find("input:radio").each(function() {
							var optionValue = $(this).val();
							if(optionValue == source){
								$(this).attr('checked', 'checked');
								return false;
							}
							return $(this).attr('checked', null);
						});
						var addBtn = "<button class='btn btn-danger rfloat right-100 addbtn'>添加</button>";
						$("#topnum").html(number + addBtn);
						$("#topicname").val(name);
						$("#topkeywords").val(keywords);
						
						$("#editor").val(topcontent);
						
						$("#editor").css({
							"width" : "600px",
							"height" : "150px",
						});
						
						UE.getEditor('editor');
						
						var _num      = parseInt(number);
						var content   = "<div class='control-group' id='numone'><div class='controls'>";
						if(_num >= 2){
							content += "子课题名称一：<input name='sTName1' class='input-medium' id='firstname1' value='" + $("#stname1").val() + "' type='text' style='width:497px;' />";
							content += "</div></div><div class='control-group' id='numtwo'><div class='controls'>";
							content += "子课题名称二：<input name='sTName2' class='input-medium' id='firstname2' value='" + $("#stname2").val() + "' type='text' style='width:497px;' />";
							content += "</div></div>";
							if(_num >=3){
								content += "<div class='control-group' id='numthree'><div class='controls'>";
								content += "子课题名称三：<input name='sTName3' class='input-medium' id='firstname3' value='" + $("#stname3").val() + "' type='text' style='width:497px;' />";
								content += "</div></div>";
								if(_num == 4){
									content += "<div class='control-group' id='numfour'><div class='controls'>";
									content += "子课题名称四：<input name='sTName4' class='input-medium' id='firstname4' value='" + $("#stname4").val() + "' type='text' style='width:497px;' />";
									content += "</div></div>";
								}
							}
						}
						$("#peonum").html(content);
						
						/*_base.parent().find("#head").css('display', 'block');
						_base.parent().find("#body").css('display', 'block');*/
						$(".myModalLabel").html(_base.parent().find("#head").html());
						$(".modal-body").html(_base.parent().find("#body").html());
						
						//$(".model-edit").modal("show");
					}
				);
		});
	},
	
	bindEditOkBtnClick: function(){
		$(".edit-ok-btn").click(function() {
			var _type     = $("input[name='topType']:checked").val();
			var _source   = $("input[name='source']:checked").val();
			var _name     = $("#topicname").val();
			var _num      = $("#topnum").val();
			var _content  = UE.getEditor('editor').getContent();
			var _keywords = $("#topkeywords").val();
			var _topId    = $("#topid").val();
			
			try{
				HHJsLib.isEmpty(_content, "课题内容");
				HHJsLib.isEmpty(_keywords, "课题关键字");
				HHJsLib.isEmpty(_name, "课题名称");
				
				var data = {
						topId: _topId, topName: _name, topContent: _content, topKeywords: _keywords,
						topType: _type, topSource: _source, topNum: _num
					};
				$.post(
						siteUrl + "user/topic/teaedit.do",
						data,
						function(response) {
							HHJsLib.info(response.message);
							if(response.rs === true) {
								$(".model-edit").modal("hide");
								window.location.reload();
							}
						}, 
						'json'
					);
			}catch(e){
				return HHJsLib.warn(e);
			}
		});
	},
	
	bindTopNumChange: function(){
		$("#topnum").change(function() {
			var _curnum = parseInt($("#topnum").val());
			var _oldnum = parseInt($("#number").val());
			
			if(_curnum == _oldnum){
				return;
			}
			var index   = 0;
			var content = "";
			if(_curnum >_oldnum){
				if(_oldnum == 1){
					content += "<div class='control-group' id='numone'><div class='controls'>";
					content += "子课题名称一：<input name='sTName1' class='input-medium' id='firstname1' type='text' style='width:497px;' />";
					content += "</div></div><div class='control-group'id='numtwo'><div class='controls'>";
					content += "子课题名称二：<input name='sTName2' class='input-medium' id='firstname2' value='" + $("#stname2").val() + "' type='text' style='width:497px;' />";
					content += "</div></div>";
					if(_curnum >= 3){
						content += "<div class='control-group' id='numthree'><div class='controls'>";
						content += "子课题名称三：<input name='sTName3' class='input-medium' value='' id='firstname3' value='" + $("#stname3").val() + "' type='text' style='width:497px;' />";
						content += "</div></div>";
						if(_curnum == 4){
							content += "<div class='control-group' id='numfour'><div class='controls'>";
							content += "子课题名称四：<input name='sTName4' class='input-medium' value='' id='firstname4' value='" + $("#stname4").val() + "' type='text' style='width:497px;' />";
							content += "</div></div>";
						}
					}
					$("#peonum").html(content);
				}
				if(_oldnum == 2){
					content += "<div class='control-group' id='numthree'><div class='controls'>";
					content += "子课题名称三：<input name='sTName3' class='input-medium' value='' id='firstname3' value='" + $("#stname3").val() + "' type='text' style='width:497px;' />";
					content += "</div></div>";
					if(_curnum == 4){
						content += "<div class='control-group' id='numfour'><div class='controls'>";
						content += "子课题名称四：<input name='sTName4' class='input-medium' value='' id='firstname4' value='" + $("#stname4").val() + "' type='text' style='width:497px;' />";
						content += "</div></div>";
					}
					$("#peonum").append(content);
				}
				if(_oldnum == 3){
					content += "<div class='control-group' id='numfour'><div class='controls'>";
					content += "子课题名称四：<input name='sTName4' class='input-medium' value='' id='firstname4' value='" + $("#stname4").val() + "' type='text' style='width:497px;' />";
					content += "</div></div>";
					$("#peonum").append(content);
				}
			}
			
			if(_curnum < _oldnum){
				if(_oldnum == 2 || ((_oldnum == 3 || _oldnum == 4) && _curnum == 1)){
					$("#peonum").remove();
				}
				if((_oldnum == 3 || _oldnum == 4) && _curnum == 2){
					$("#numthree").remove();
				}
				if(_oldnum == 4 && _curnum == 3){
					$("#numfour").remove();
				}
			}
		});
	}
});