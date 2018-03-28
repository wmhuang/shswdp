//来电总量 人工服务量 自动语音服务量
function getCallCount(isIndex) {
	$.ajax({
		url : ctx + '/station12366/getCallCount',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#ldzl_value").html(data.LDZL);
				$("#rgyyfwl_value").html(data.RGYYFWL);
				$("#zdyyfwl_value").html(data.ZDYYFWL);
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#ldzl_value").html(
						forInt(data.LDZL));
				$("#pageIframe").contents().find("#rgyyfwl_value").html(
						forInt(data.RGYYFWL));
				$("#pageIframe").contents().find("#zdyyfwl_value").html(
						forInt(data.ZDYYFWL));
			}

		}
	})
}
// 接通率
function getCallSuccess(isIndex) {

	$.ajax({
		url : ctx + '/station12366/getCallSuccess',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#jtl_value").html(data.JTL);
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#jtl_value").html(data.JTL);
			}
		}
	})

}
// 满意率
function getCallDegree(isIndex) {

	$.ajax({
		url : ctx + '/station12366/getCallDegree',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#myl_value").html(data.MYL);
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#myl_value").html(data.MYL);
			}
		}
	})
}
//首页来电总量
function getCallCountTotal(isIndex) {
	$.ajax({
		url : ctx + '/station12366/getCallCount',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#call_total_num").html(data.LDZL);
			 
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#call_total_num").html(
						forInt(data.LDZL));
				 
			}

		}
	})
}
//首页当前来电人数
function getCurrentCallNum(isIndex) {
	$.ajax({
		url : ctx + '/station12366/getCurrentCallNum',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#call_current_num").html(data.VALUE);
			 
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#call_current_num").html(
						forInt(data.VALUE));
				 
			}

		}
	})
}