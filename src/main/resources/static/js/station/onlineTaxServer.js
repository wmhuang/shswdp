// 预约总量 微信取号总量 业务受理总量 税企互动总量 
function getAllMaps(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getAllMaps',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#bespeakNum").html(forInt(data.YYZL));
				$("#wechatNum").html(forInt(data.WXQHZL));
				$("#hanldedCount").html(forInt(data.YWSLZL));
				$("#taxAndCominteractNum").html(forInt(data.SQHDZL));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#bespeakNum").html(
						forInt(data.YYZL));
				$("#pageIframe").contents().find("#wechatNum").html(
						forInt(data.WXQHZL));
				$("#pageIframe").contents().find("#hanldedCount").html(
						forInt(data.YWSLZL));
				$("#pageIframe").contents().find("#taxAndCominteractNum").html(
						forInt(data.SQHDZL));
			}
		}
	})
}

// 跨区通办预约TOP5
function getHandledByAreaRank(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getHandledByAreaRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_1_table', 0, 2, data, isIndex);
		}
	})
}
// 微信取号按事项TOP5
function getOfferNumByWeChatRank(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getOfferNumByWeChatRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_2_table', 0, 2, data, isIndex);
		}
	})
}
// 业务受理总量排行榜TOP5
function getHandldedRank(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getHandldedRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_3_table', 0, 2, data, isIndex);
		}
	})
}
// 重点事项实时办理量
function getHandledCountRealTime(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getHandledCountRealTime',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_4_table', 0, 2, data, isIndex);
		}
	})
}

// 首页网厅办理业务总量
function getHanldedCount(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getHanldedCount',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#online_total_count").html(forInt(data.YWSLZL));

			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#online_total_count").html(
						forInt(data.YWSLZL));

			}
		}
	})
}
// 首页网厅当前在线人数
function getOnlineNum(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getOnlineNum',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#online_current_num").html(forInt(data.VALUE));

			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#online_current_num").html(
						forInt(data.VALUE));

			}
		}
	})
}


