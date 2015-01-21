HHJsLib.register({
	init: function() {
		this.bindOfficeClick();
		this.bindGradeChange();
	},
	bindOfficeClick: function() {
		$(".office").click(function() {
			$(".latest-news").attr("class", "mav");
		});
	},
	bindGradeChange: function() {
		/**
		 * 班级管理，年级变化事件
		 */
		$(".grade").click(function() {
			alert("ok");
		});
	}
});