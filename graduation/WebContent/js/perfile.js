HHJsLib.register({
	init: function() {
		this.bindTeaperFilebtnClick();
	},
	/**
	 * 教师修改个人资料
	 */
	bindTeaperFilebtnClick: function() {
		$(".teaperfilebtn").click(function() {
			var _root = $(this);
			try {
				HHJsLib.isEmailByDom("#email");
			} catch (e) {
				return HHJsLib.warn(e);
			}
		});
	}
});