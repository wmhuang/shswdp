function getFooterTime() {
	var datetime = new Date();
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	var today = new Array('\u661f\u671f\u65e5', '\u661f\u671f\u4e00', '\u661f\u671f\u4e8c', '\u661f\u671f\u4e09', '\u661f\u671f\u56db', '\u661f\u671f\u4e94', '\u661f\u671f\u516d');
	var week = today[datetime.getDay()];
	$("#date").html(year + "\u5e74" + month + "\u6708" + date + "\u65e5");
	$("#time").html(hour + ":" + minute);
	$("#week").html(week);
}
function getFooter() {
	var datetime = new Date();
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	var today = new Array('\u661f\u671f\u65e5', '\u661f\u671f\u4e00', '\u661f\u671f\u4e8c', '\u661f\u671f\u4e09', '\u661f\u671f\u56db', '\u661f\u671f\u4e94', '\u661f\u671f\u516d');
	var week = today[datetime.getDay()];
	$("#date").html(year + "\u5e74" + month + "\u6708" + date + "\u65e5");
	$("#time").html(hour + ":" + minute);
	$("#week").html(week);
	$("#endDate").html(year + "-" + month + "-" + (date));
}

// 设置时间间隔
function setTimeSpan(timeSpan) {
	$.ajax({
		type : "get",
		url : ctx + "/system/setCookiesParams",
		async : true,
		dataType : "json",
		data : {
			timeSpan : timeSpan
		},
		success : function(data) {
			// 刷新当前页面的div 通过全局变量CURRENT_PAGE_NAME判断 传入的参数大家请随意
			switch (CURRENT_PAGE_NAME) {

			case "indexPage_station":
				// 实体
				getTotalCount("1");

				// 网厅  
				getHanldedCount("1");
				getBusinessTypePersent("1");

				// 12366
				getCallCountTotal("1");
				getQuestionTypePersent("1");

				// arm
				getIndexTotalCountAndPieEcharts("1");

				break;
			case "entityTaxServer_station":
				// 服务事项排行榜
				getFwsxphb("1");
				// 服务明星排行榜
				getFwmxphb("1");
				// 第一张中间饼图
				pieEcharts("1");
				// 预约总量 跨区通办
				getYyzlTbzl("1");
				break;
			case "onlineTaxServer_station":
				// 数值Map
				getAllMaps("1");
				// 柱状图 跨区本区预约
				handledByOtherArea('1');
				// 跨区通办预约TOP5
				getHandledByAreaRank('1');
				// 微信取号按事项TOP5
				getOfferNumByWeChatRank("1");
				// 业务受理总量排行榜TOP5
				getHandldedRank("1");
				// 重点事项实时办理量
				getHandledCountRealTime("1");
				break;
			case "selfHelpTaxServer_station":
				setArmHandledCountRank("1");
				getArmUsedCountRank("1");
				break;
			case "taxServerStatistics12366_station":
				getCallCount("1");
				getCallSuccess("1");
				getCallDegree("1");
				break;
			default:
			}
			initSomeDMYBtn(timeSpan);

		}
	})
}

// 右上角年月日按钮控制
var arr = new Array("D", "M", "Y");

function initSomeDMYBtn(a) {
	for ( var i in arr) {
		if (a != arr[i]) {
			initDMYBtn(arr[i]);
		} else {
			$("#timeSpan_" + a).attr("src", "/img/icon_" + a + "_2.png");
			$("#timeSpan_" + a).unbind("mouseout");
			$("#timeSpan_" + a).unbind("click");
		}
	}
}

function initDMYBtn(a) {
	$("#timeSpan_" + a).attr("src", "/img/icon_" + a + "_1.png");
	$("#timeSpan_" + a).bind("mouseover", function() {
		this.src = '/img/icon_' + a + '_2.png';
	});
	$("#timeSpan_" + a).bind("mouseout", function() {
		this.src = '/img/icon_' + a + '_1.png';
	});
	$("#timeSpan_" + a).unbind("click").bind("click", function() {
		setTimeSpan(a);
	});
}

function dmyInitFun() {
	initSomeDMYBtn($("#timeSpan_input").val());
}

$(function() {
	// 时间戳
	getFooterTime();
	// 设置年月日标签
	initSomeDMYBtn();
	// 设置当前年月日标签
	dmyInitFun();
})
