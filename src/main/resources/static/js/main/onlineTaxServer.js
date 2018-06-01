// 预约总量 微信取号总量 业务受理总量 税企互动总量
function getAllMaps(isIndex) {
	$.ajax({
		url : ctx + '/onlineTaxServer/getAllMaps',
		dataType : 'json',
		success : function(data) {
			handledByOtherArea(data.KQTBYYZL, data.BQYYZL, isIndex)
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#bespeakNum").html(forInt(data.YYZL));
				$("#wechatNum").html(forInt(data.WXQHZL));
				$("#hanldedCount").html(forInt(data.YWSLZL));
				$("#taxAndCominteractNum").html(forInt(data.SQHDZL));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#bespeakNum").html(forInt(data.YYZL));
				$("#pageIframe").contents().find("#wechatNum").html(forInt(data.WXQHZL));
				$("#pageIframe").contents().find("#hanldedCount").html(forInt(data.YWSLZL));
				$("#pageIframe").contents().find("#taxAndCominteractNum").html(forInt(data.SQHDZL));
			}
		}
	})
}

// 通办预约分局TOP5|本区预约分局TOP5
var isBq = true;
function getHandledByAreaRank(isIndex) {
	if (isBq) {
		$.ajax({
			url : ctx + '/onlineTaxServer/getBqyyfj',
			dataType : 'json',
			success : function(data) {
				$('#station_online_1_rank').html("本区预约分局TOP5");
				pushRankTable('station_online_1_table', 0, 2, data, isIndex);
			}
		})
	} else {
		$.ajax({
			url : ctx + '/onlineTaxServer/getTbyyfj',
			dataType : 'json',
			success : function(data) {
				$('#station_online_1_rank').html("通办预约分局TOP5");
				pushRankTable('station_online_1_table', 0, 2, data, isIndex);
			}
		})
	}
	isBq = !isBq;
}
// 微信取号按事项TOP5
function getOfferNumByWeChatRank(isIndex) {
	$.ajax({
		url : ctx + '/onlineTaxServer/getOfferNumByWeChatRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_2_table', 0, 2, data, isIndex);
		}
	})
}
// 业务受理总量排行榜TOP5
function getHandldedRank(isIndex) {
	$.ajax({
		url : ctx + '/onlineTaxServer/getHandldedRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_online_3_table', 0, 2, data, isIndex);
		}
	})
}
// 业务受理总量按分局TOP5
var swjgDmArr = new Array('13100430000', '13101010000', '13101040000', '13101050000', '13101060000', '13101070000', '13101090000', '13101100000', '13101120000', '13101130000',
		'13101140000', '13101150000', '13101410000', '13102260000', '13102270000', '13102280000', '13102290000', '13102300000');
var swjgDmIndex = 0;
function getBusinessRankByStation(isIndex) {
	var currentSwjgDm = swjgDmArr[swjgDmIndex];
	$.ajax({
		url : ctx + '/onlineTaxServer/getBusinessRankByStation',
		dataType : 'json',
		data : {
			swjgDm : currentSwjgDm
		},
		success : function(data) {
			var swjgJc = "";
			if (data.length > 0) {
				swjgJc = data[0].SWJGJC;
			}
			$('#handledBusinessStation_title').html(swjgJc + "网厅业务TOP5");
			pushRankTable('station_online_4_table', 0, 2, data, isIndex);

		}
	});
	swjgDmIndex++;
	if (swjgDmIndex == swjgDmArr.length) {
		swjgDmIndex = 0;
	}
}

function handledByOtherArea(KQTBYYZL, BQYYZL, isIndex) {
	var option = {
		calculable : false,
		xAxis : [ {
			show : false,
			type : 'value',
			boundaryGap : [ 0, 0.01 ]
		} ],
		yAxis : [ {
			show : true,
			type : 'category',
			zlevel : 1,
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				margin : -60,
				textStyle : {
					color : 'white',
					fontSize : 24
				}
			},
			data : [ '跨区', '本区' ]
		} ],
		grid : {
			y : 10,
			x : 35,
			x2 : 10,
			y2 : 15,
			width : 250
		},
		series : [ {
			type : 'bar',
			data : [ KQTBYYZL, BQYYZL ],
			barWidth : 41.9,
			barMinHeight : 70,
			itemStyle : {
				normal : {
					color : 'rgba(87,153,255,0.7)',
					label : {
						show : true,
						position : 'right',
						formatter : function(a) {
							return forInt(a.data);
						},
						textStyle : {
							color : '#FFB61C',
							align : 'left',
							baseline : 'bottom',
							fontFamily : 'Impact',
							fontSize : 36
						}
					}
				}
			}
		} ]
	}

	var chart;
	if (typeof (isIndex) == 'undefined') {
		chart = echarts.init(document.getElementById('handledByOtherArea_bar'));
	} else {
		var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("handledByOtherArea_bar");
		chart = echarts.init(obj);
	}
	chart.setOption(option);
}
// 重点事项办理量
function getZdsxbll() {
	$.ajax({
		url : '/onlineTaxServer/getZdsxbll',
		data : {},
		success : function(data) {
			$('#zzsfpdk').html(forInt(data.ZZSFPDK));
			$('#kqysssxbg').html(forInt(data.RCGL2008));
			$('#ybnsrdj').html(forInt(data.RCGL1932));
			$('#zgkpxesp').html(forInt(data.RCGL1939));
			$('#fpszhd').html(forInt(data.RCGL1937));
		}
	})

}