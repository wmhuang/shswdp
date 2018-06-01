var currentStation = "13100000000"; // 默认选中市局
$(function() {
	$("#selectTable td").bind("click", function() {
		// 分局编码
		var selectId = this.id;
		currentStation = this.id;
		$("#selectTable td").attr("class", "unselect");
		$("#" + currentStation).attr('class', 'select');
		$("#selectDiv").css("display", "none");
		window.location="/index/"+currentStation;
	})

	// 控制所有的改变入口
	function initChange() {
	}
	initChange();
	// window.setInterval(initChange, 10000);

})

function selectStation() {
	$("#" + currentStation).attr('class', 'select');
	$("#selectDiv").css("display", "block");
	
}