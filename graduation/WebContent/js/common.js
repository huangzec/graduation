 HHJsLib.register({
    init: function() {
        this.bindMenuChange();
		this.bindCurgrade();
		HHJsLib.autoSelect("#cur-grade");
        HHJsLib.autoRadio("#topic-type");
        HHJsLib.autoRadio("#topic-source");
        HHJsLib.autoRadio("#taskdoc");
    },
	bindMenuChange: function() {
		HHJsLib.highLightElement("ul#main-nav li", "active", 1);
	},
	bindCurgrade: function() {
		/**
		 * 学生开题答辩概况，当前年级变化事件
		 */
		$("#cur-grade").change(function() {
			var curGrade 	= $(this).val();
			if("" == curGrade) {
				return false;
			}
			var href 	= window.location.href;
			var regex 	= /grade/;
			var parm 	= /\?/;
			if(!parm.test(href)) {
				//不带参数
				window.location.href = href + "?grade=" + curGrade;				
			}else {
				//带参数
				if(!regex.test(href)) {
					//如果没有grade参数
					window.location.href = href + "&grade=" + curGrade;
				}else {
					var reg = /(grade=[0-9]+)/g;
					href 	= href.replace(reg, "grade=" + curGrade);
					window.location.href = href;
				}				
			}
		});
	}	
 });