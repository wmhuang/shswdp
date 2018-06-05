//divbox1----------------------start------------------------------------
// 窗口受理人数TOP5
function getOfferCountRank() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getOfferCountRank",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			$("#rank_1_title").html("窗口受理人数TOP5");
			pushRankTable("rank_1_table", 0, 2, data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

// 当前等待人数TOP5
function getWaitingCountRank() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getWaitingCountRank",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			$("#rank_1_title").html("当前等待人数TOP5");
			pushRankTable("rank_1_table", 0, 2, data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

// 已办理人数TOP5
function getHandledCountRank() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getHandledCountRank",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			$("#rank_1_title").html("当前已办理人数TOP5");
			pushRankTable("rank_1_table", 0, 2, data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

// 服务人数排行榜 循环定时执行
var serverType = 0;
function getServerCountRank() {
	if (serverType == 0) {
		getOfferCountRank();
		serverType++;
	} else if (serverType == 1) {
		getHandledCountRank();
		serverType++;
	} else {
		getWaitingCountRank();
		serverType = 0;
	}
}
// divbox1----------------------end------------------------------------

// divbox2----------------------start------------------------------------
// 业务受理总量（中心城区）TOP5
function getHanledBusinessRankCq() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getHandledBusinessRank",
		async : false,
		data : {
			local : "CQ"
		},
		dataType : "json",
		success : function(data) {
			$("#handledBusiness_title").html("业务受理总量城区TOP5");
			pushRankTable("handledBusiness_table", 0, 2, data);
		}
	});
}
// 业务受理总量（郊区）TOP5
function getHanledBusinessRankJq() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getHandledBusinessRank",
		async : false,
		data : {
			local : "JQ"
		},
		dataType : "json",
		success : function(data) {
			$("#handledBusiness_title").html("业务受理总量郊区TOP5");
			pushRankTable("handledBusiness_table", 0, 2, data);
		}
	});
}
// 控制业务受理总量top5 中心城区和郊区自动切换
var showCq = true;
function handledBusinessChange() {
	if (showCq) {
		getHanledBusinessRankCq();

	} else {
		getHanledBusinessRankJq();
	}
	showCq = !showCq;
}
// divbox2----------------------end------------------------------------

// handledBusinessType_table 业务受理类型TOP5/各事项按分局TOP5
// 根据业务受理类型 按分局展示
var showTypeRank = true;
var typeArr = new Array();
var typeNameArr = new Array();
var typeLength;
var currentTypeIndex = 0;
function getHandledBusinessTypeRank() {
	if (showTypeRank) {
		// 业务受理类型TOP5
		$.ajax({
			type : "get",
			url : ctx + "/entityTaxServer/getHandledBusinessTypeRank",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#handledBusinessType_title").html("业务受理类型TOP5");
				pushRankTable("handledBusinessType_table", 0, 2, data);
				typeLength = data.length;
				if (typeLength == 0) {
					// 业务类型没有数据
					typeArr = new Array();
					typeNameArr = new Array();
					return;
				}
				for (var i = 0; i < typeLength; i++) {
					typeArr[i] = data[i].DTYWXL_DM;
					typeNameArr[i] = data[i].NAME;
				}
				showTypeRank = false;
			}
		});
	} else {
		var currentType = typeArr[currentTypeIndex];
		var currentTypeName = typeNameArr[currentTypeIndex];
		if (currentTypeIndex == typeLength - 1) {
			showTypeRank = true;
			currentTypeIndex = 0;
		} else {
			currentTypeIndex++;
		}
		// 各事项按分局TOP5
		$.ajax({
			type : "get",
			url : ctx + "/entityTaxServer/getSubStationHandledBusinessTypeRank",
			async : false,
			data : {
				currentType : currentType
			},
			dataType : "json",
			success : function(data) {
				$("#handledBusinessType_title").html(currentTypeName + "分局TOP5");
				pushRankTable("handledBusinessType_table", 0, 2, data);
			}
		});
	}

}

// 全市通办分局TOP5/全市通办业务量TOP5
var showByStation = true;
function getHandledCountByStationOrBusinessRank() {
	if (showByStation) {
		// 全市通办分局TOP5
		$.ajax({
			type : "get",
			url : ctx + "/entityTaxServer/getHandledCountByStationRank",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#getCountByStaionOrBusiness_title").html("通办分局TOP5");
				pushRankTable("getCountByStaionOrBusiness_table", 0, 2, data);
			}
		});
	} else {
		// 全市通办业务量TOP5
		$.ajax({
			type : "get",
			url : ctx + "/entityTaxServer/getHandledCountByBusinessRank",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#getCountByStaionOrBusiness_title").html("通办业务受理TOP5");
				pushRankTable("getCountByStaionOrBusiness_table", 0, 2, data);
			}
		});
	}
	showByStation = !showByStation;
}

// 取号人数、已办理人数、当前等待人数
function getOfferNumber() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getOfferHandledWaittingCount",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			
			$("#offerNumber").html(forInt(data.QHSL));
			$("#handledNumber").html(forInt(data.YBLSL));
			$("#waitingNumber").html(forInt(data.DQDDRS));
		}
	});
}

//窗口开放柱状图
function setWindowStatisticsBar(open,pause,close){
	var option = {
			tooltip : {
				show : true
			},
			grid : {
				y : 20,
				x : 15,
				y2 : 15,
				width : 370
			},
			xAxis : [ {
				show : true,
				position : 'top',
				type : 'category',
				data : [ "开启", "暂停", "关闭" ],
				axisLabel : {
					show : false,
					textStyle : {
						color : 'white',
						baseline : 'bottom',
						fontFamily : '微软雅黑',
						fontSize : 24
					}
				},
				axisLine : {
					//onZero:false,
					lineStyle : {
						color : 'rgb(79,114,185)',
						width : '3'
					}
				},
				axisTick : {
					show : false
				}
			} ],
			yAxis : [ {
				show : false,
				type : 'value'
			} ],
			series : [ {
				type : "bar",
				data : [open,pause,close],
				barWidth : 56,
				barGap : '60',
				barCategoryGap : '60',
				// 			barMinHeight:30, //柱状图最低显示高度
				itemStyle : {
					normal : {
						color : 'rgba(87,153,255,0.7)',
						label : {
							show : true,
							position : 'top',
							formatter : function(a) {
								return a.name;
							},
							textStyle : {
								color : 'white',
								align : 'left',
								baseline : 'bottom',
								fontFamily : '微软雅黑',
								fontSize : 24
							}
						}
					}
				}
			} ]
		};
	var chart = echarts.init(document.getElementById('taxServerWindowStatistics_bar'));
	// 为echarts对象加载数据 
	chart.setOption(option);
}
//窗口开放情况折线图
function setWindowStatisticsLine(open,pause,close){
	var option = {
			grid : {
				y : 20,
				x : 60,
				y2 : 30,
				width : 370
			},
			tooltip : {
				show : true

			},
			xAxis : {
				type : 'category',
				data : [ '开启', '暂停', '关闭' ],
				axisLabel : {
					show : true,
					margin : 30,
					textStyle : {
						color : function(a) {
							if (a == "开启") {
								return "#FFB61C";
							} else {
								return "white";
							}
						},
						baseline : 'bottom',
						fontFamily : '微软雅黑',
						fontSize : 24
					}
				},
				axisLine : {
					show : true,
					lineStyle : {
						color : 'white'
					}
				}
			},
			yAxis : {
				type : 'value',
				axisLabel : {
					show : true,
					textStyle : {
						color : 'white',
						baseline : 'bottom',
						fontFamily : 'Impact',
						fontSize : 16
					}
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : 'white'
					}
				},
				axisLine : {
					show : true,
					lineStyle : {
						color : 'white'
					}
				}
			},
			series : [ {
				itemStyle : {
					normal : {
						lineStyle : {
							color : 'rgb(255,156,114)',
							width : 5
						},
						label : {
							show : true,
							position : 'top',
							textStyle : {
								color : "#FFB61C",
								fontFamily : 'Impact',
								fontSize : 24
							},
							formatter : function(a) {
								return forInt(a.data);
							}
						}
					}
				},

				data : [open,pause,close],
				type : 'line'
			// 			symbol: 'pin'
			}, {
				itemStyle : {
					normal : {
						lineStyle : {
							color : 'rgb(255,156,114)',
							width : 5
						},
						label : {
							show : true,
							position : 'top',
							textStyle : {
								color : "white",
								fontFamily : 'Impact',
								fontSize : 24
							},
							formatter : function(a) {
								return forInt(a.data);
							}
						}
					}
				},

				data : [],
				type : 'line'
			// 	symbol: 'pin'
			} ]

		};
	var chart = echarts.init(document.getElementById('taxServerWindowStatistics_line_div'));
	// 为echarts对象加载数据 
	chart.setOption(option);
}

// 办税大厅窗口情况
function getTaxServerWindowStatistics() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getTaxServerWindowStatistics",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			setWindowStatisticsBar(data.OPEN,data.PAUSE,data.CLOSE);
			setWindowStatisticsLine(data.OPEN,data.PAUSE,data.CLOSE)
			$("#openWindowNum").html(forInt(data.OPEN));
			$("#pauseWindowNum").html(forInt(data.PAUSE));
			$("#closeWindowNum").html(forInt(data.CLOSE));
			
		}
	});
}

// 办税大厅窗口情况折线和柱状图切换
var showBar1 = true;
function changeTaxServerWindowStatisticsShowType() {
	if (showBar1) {
		$('#taxServerWindowStatistics_bar_div').show();
		$('#taxServerWindowStatistics_line_div').hide();
	} else {
		$('#taxServerWindowStatistics_line_div').show();
		$('#taxServerWindowStatistics_bar_div').hide();
	}
	showBar1 = !showBar1;
}

// 预约办理总量，本区预约，跨区预约
function getSubScribeStatistics() {
	$.ajax({
		type : "get",
		url : ctx + "/entityTaxServer/getSubScribeStatistics",
		async : false,
		data : {},
		dataType : "json",
		success : function(data) {
			$('#subScribeStatistics').html(forInt(data.YYZL));
			intiBqkqtbyy_bar(data.KQTBYYZL, data.BQYYZL)
		}
	});
}

// 本区跨区预约柱状图
function intiBqkqtbyy_bar(KQTBYYZL, BQYYZL) {
	var option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},

		calculable : true,
		grid : {
			x : 50,
			y : 20,
			x2 : 70,
			y2 : 20
			
		},
		xAxis : [

		{
			show : false,
			type : 'value'
		} ],
		yAxis : [ {
			z : 99,
			type : 'category',
			data : [ '跨区', '本区' ],
			axisLine : {
				show : false
			},
			axisLabel : {
				margin : -45,
				textStyle : {
					color : 'white',
					fontSize : '20'
				}
			}
		} ],
		series : [ {
			name : '直接访问',
			type : 'bar',
			barMinHeight : 70,
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'insideRight'
					}
				}
			},
			barWidth : 40,
			data : [ KQTBYYZL, BQYYZL ],
			itemStyle : {
				normal : {
					color : '#5799FF',
					label : {
						show : true,
						position : 'right',
						textStyle : {
							color : '#FFB61C',
							fontFamily : 'Impact',
							fontSize : 36
						}
					}
				}
			}
		} ]
	};
	var chart = echarts.init(document.getElementById('bqkqtbyy_bar'));
	chart.setOption(option);
}

