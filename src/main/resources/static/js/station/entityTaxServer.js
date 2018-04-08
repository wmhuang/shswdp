//滚动条距离 适配计算选中框的偏移量

$('#page_1').unbind('click').bind('click', function() {
	$('#entityDiv2').hide();
	$('#entityDiv1').show();
})
$('#page_2').unbind('click').bind('click', function() {
	$('#entityDiv1').hide();
	$('#entityDiv2').show();
})

// 获取实体厅窗口列表
function getEntityWindows(busiType, id) {
	$('#entity_window_box').html("");
	clear_detail();
	$('#' + id).siblings().css('background', 'url(/img/bg_entity_span_1.png)');
	$('#' + id).css('background', 'url(/img/bg_entity_span_2.png)')
	$.ajax({
		url : ctx + "/stationEntity/getEntityWindows",
		dataType : "json",
		data : {
			busiType : busiType
		},
		success : function(data) {
			var arr = new Array();
			for ( var key in data) {
				// 获取大厅名称
				$('#entity_window_box').append("<div class='entity_window_title'><span class='span_white_30'>" + key + "</span></div>")
				var list = data[key];
				for ( var i in list) {
					// 获取大厅窗口列表
					// 生成div的id包含 CK_窗口号码_大厅代码_窗口状态 信息
					// 通過icon_window_窗口状态 匹配对应的图片
					$('#entity_window_box').append(
							"<div id='ck_" + list[i].CK_DM + "_" + list[i].DT_DM + "_" + list[i].CK_ZT
									+ "' onclick='view_detail(this.id)' class='entity_window_div' style='background:url(/img/icon_window_" + list[i].CK_ZT
									+ ".png) no-repeat;' ><div class='entity_window_bottom_div'><span class='span_white_18'>" + list[i].CK_DM + "</span></div></div>")

				}
				$('#entity_window_box').append("<br/>")
			}
		}
	})
}

function view_detail(id) {
	// 清理详细数据
	var info = id.split("_");

	// 窗口号码
	var ck_hm = info[1];
	// 大厅代码
	var dt_dm = info[2];
	// 窗口当前状态
	var ck_zt = info[3];

	if (ck_zt == "close") {
		// 如果窗口是关闭的或者其他指定的状态，则return
		return;
	}

	// 确定选择框横坐标 纵坐标的位置。
	var X = $('#' + id).offset().left - 59;
	var Y = $('#' + id).offset().top - 64 + scrollLength;
	// 添加选中窗口 详细信息
	$("#select_box").remove();
	$('#entity_window_box').append(
			"<div id ='select_box' style='position:absolute;z-index:1000;width:102px;height:118px;top:" + Y + "px;left:" + X
					+ "px;background:url(/img/icon_window_select.png);'></div>");
	get_detail(dt_dm, ck_hm);
}

// 展示详细信息
function get_detail(dt_dm, ck_hm) {
	$.ajax({
		url : ctx + "/stationEntity/getDetailInfo",
		dataType : "json",
		data : {
			dtdm : dt_dm,
			ckhm : ck_hm
		},
		success : function(data) {
			$("#entity_swry_mc").html(data.RYMC);
			$("#entity_dtmc_ck_dm").html(data.DTMC + "<br>" + data.CKHM + "号窗口");
			$("#login_time").html(data.DLSJ);
			$("#work_time").html(data.GZSC);
			$("#rest_time").html(data.XXSC);
			$("#busi_count").html(data.YWZL);
			$("#fhl").html(data.FHL);
			$("#yjhs").html(data.YJHZS);
			// $("#qhs").html(data.qhs);
			$("#dbywjs").html(data.YWPJSC);
		}
	})
}

function clear_detail() {
	// 清理detail窗口
	$("#entity_swry_mc").html("");
	$("#entity_dtmc_ck_dm").html("");
	$("#login_time").html("");
	$("#work_time").html("");
	$("#rest_time").html("");
	$("#busi_count").html("");
	$("#fhl").html("");
	$("#yjhs").html("");
	$("#qhs").html("");
	$("#dbywjs").html("");
}

// 服务事项排行榜
function getFwsxphb(isIndex) {
	$.ajax({
		url : ctx + '/stationEntity/getFwsxphb',
		dataType : 'json',
		success : function(data) {
			pushRankTable10('fwsxphb', 0, 2, data, isIndex);
		}
	})
}

// 服务明星排行榜
function getFwmxphb(isIndex) {
	$.ajax({
		url : ctx + '/stationEntity/getFwmxphb',
		dataType : 'json',
		success : function(data) {
			pushRankTable10('fwmxphb', 2, 1, data, isIndex);
		}
	})
}

function pieEcharts(isIndex) {
	var option = {
		color : [ '#FF8B78', '#F5CE6E', '#A1C9FF', '#B0CBA1' ],
		title : {
			show : false,
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b} : {c} ({d}%)"

		},
		legend : {
			show : false,
			type : 'scroll',
			orient : 'vertical',
			right : 10,
			top : 20,
			bottom : 20,
			data : [ "综合服务", "发票管理", "纳税申报" ],
			textStyle : {
				color : "white",
				fontSize : 20
			}
		},
		calculable : true,
		series : [ {
			name : '姓名',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '50%' ],
			// roseType : 'area',
			data : [],
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'outer',
						formatter : function(a, b, c) {
							return a.data.name;
						},
						textStyle : {
							color : "white",
							fontFamily : "w微软雅黑",
							fontSize : 30
						}

					},
					labelLine : {
						show : true
					}
				}
			}
		} ]
	};

	$.ajax({
		url : ctx + '/stationEntity/getPieEcharts',
		dataType : 'json',
		success : function(data) {
			option.series[0].data = data;

			var chart;
			if (typeof (isIndex) == 'undefined') {

				// 刷新页面加载
				chart = echarts.init(document.getElementById('station_entity_business_type_pie'));
			} else {
				// 点击年月日加载
				var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("station_entity_business_type_pie");
				chart = echarts.init(obj);
			}
			chart.setOption(option);
		}
	});

}

function getYyzlTbzl(isIndex) {
	$.ajax({
		url : ctx + '/stationOnline/getBespeakNum',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#yyzl").html(forInt(data.YYZL));
				$("#tbzl").html(forInt(data.KQTBYYZL));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#yyzl").html(forInt(data.YYZL));
				$("#pageIframe").contents().find("#tbzl").html(forInt(data.KQTBYYZL));
			}
		}
	});
}

function IndexEntityRank(isIndex) {
	$.ajax({
		url : ctx + '/stationEntity/getIndexEntityRank',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				for (var i = 1, l = data.length; i <= l; i++) {
					$("#index_entity_rank tr:eq(" + i + ") td:eq(0) span").html(i + "&nbsp;|&nbsp;" + data[i - 1].MC);
					$("#index_entity_rank tr:eq(" + i + ") td:eq(1) span").html(data[i - 1].YBLSL);
					$("#index_entity_rank tr:eq(" + i + ") td:eq(2) span").html(data[i - 1].DQDDRS);
					$("#index_entity_rank tr:eq(" + i + ") td:eq(3) span").html(data[i - 1].YJDDSJ + "分钟");
				}
			} else {
				// 清空表格
				for (var i = 1; i <= 5; i++) {
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(0) span").html('');
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(1) span").html('');
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(2) span").html('');
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(3) span").html('');
				}

				// 通过年月日加载
				for (var i = 1, l = data.length; i <= l; i++) {
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(0) span").html(i + "&nbsp;|&nbsp;" + data[i - 1].MC);
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(1) span").html(data[i - 1].YBLSL);
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(2) span").html(data[i - 1].DQDDRS);
					$("#pageIframe").contents().find("#index_entity_rank tr:eq(" + i + ") td:eq(3) span").html(data[i - 1].YJDDSJ + "分钟");

				}
			}

		}
	})
}

// 主页办税服务厅业务总量
function getTotalCount(isIndex) {
	$.ajax({
		url : ctx + '/stationEntity/getTotalCount',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#entity_total_count").html(forInt(data.VALUE));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#entity_total_count").html(forInt(data.VALUE));
			}
		}
	});
}