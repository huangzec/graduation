HHJsLib.register({
	init: function() {
		this.bindGradeChange();
	},
	bindGradeChange: function() {
		/**
		 * 班级管理，年级变化事件
		 */
		$("#student-form").find(".loaded").children().change(function() {
			alert("dddd");
		});
	}
});