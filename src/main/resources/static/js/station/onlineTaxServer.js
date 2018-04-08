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

function handledByOtherArea(isIndex) {
	$
			.ajax({
				type : "get",
				url : ctx + "/stationOnline/getAreaMaps",
				async : false,
				data : {},
				dataType : "json",
				success : function(data) {

					var arr = new Array(data.KQTBYYZL, data.BQYYZL);
					var chart;
					if (typeof (isIndex) == 'undefined') {
						chart = echarts.init(document
								.getElementById('handledByOtherArea_bar'));
					} else {
						var obj = document.getElementById("pageIframe").contentWindow.document
								.getElementById("handledByOtherArea_bar");
						chart = echarts.init(obj);
					}

					// 为echarts对象加载数据
					chart.setOption({
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
							x2 : 0,
							y2 : 15,
							width : 250
						},
						series : [ {
							type : 'bar',
							data : arr,
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
					});

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

				}
			});

}

function stationOnlineTaxServerCountLine(isIndex) {
	$
			.ajax({
				type : "get",
				url : ctx + "/stationOnline/getHanldedCountOnLine",
				async : false,
				data : {},
				dataType : "json",
				success : function(data) {
					var xData = new Array();
					var dData = new Array();
					for ( var i in data) {
						xData[i] = data[i].NAME;
						dData[i] = data[i].VALUE;
					}
					var chart;
					if (typeof (isIndex) == 'undefined') {
						chart = echarts
								.init(document
										.getElementById('station_onlineTaxServerCount_line'));
					} else {
						var obj = document.getElementById("pageIframe").contentWindow.document
								.getElementById("station_onlineTaxServerCount_line");
						chart = echarts.init(obj);
					}
					chart.setOption({
						title : {
							text : '网上税务服务业务总量近12月趋势图',
							padding : [ 5, 5, 5, 50 ],
							textStyle : {
								fontFamily : '微软雅黑',
								color : 'white',
								fontSize : 20
							}
						},
						grid : {
							y : 50,
							x : 60,
							y2 : 30
						// width : 370
						},
						tooltip : {
							show : true,
							textStyle : {
								fontFamily : 'Impact',
								color : 'white',
								fontSize : 20
							}

						},
						xAxis : {
							type : 'category',
							data : xData,
							axisLabel : {
								show : true,
								margin : 30,
								textStyle : {
									color : "white",
									baseline : 'bottom',
									fontFamily : 'Impact',
									fontSize : 20
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

							data : dData,
							type : 'line'
						} ]

					});

				}
			});
}
//主页网厅环形图
function getBusinessTypePersent(isIndex) {
	var option = {
		color : [ '#FF9D35', '#AE62E0', '#E3E067', '#5BDBFF', '#7F9FFF', '#FFC500', '#3CB371', '#00FA9A', '#FF69B4' ],
		tooltip : {
			trigger : 'item',
			formatter : "{d}%<br/>{b}  "
		},
		calculable : false,
		series : [ {
			name : '业务类型',
			type : 'pie',
			radius : [ 80, 110 ],
			// for funnel
			x : '60%',
			width : '35%',
			funnelAlign : 'left',
			// max: 1048,
			data : [],
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{d}%\n{b}",
						textStyle : {
							color : "white",
							fontSize : 14
						}
					}
				}
			},
		} ]
	};
	$.ajax({
		type : "get",
		url : ctx + "/stationOnline/getBusinessTypePersent",
		async : false,
		dataType : "json",
		success : function(data) {
			option.series[0].data = data;
			var chart;
			if (typeof (isIndex) == 'undefined') {
				chart = echarts.init(document.getElementById('online_pie'));

			} else {
				var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("online_pie");
				chart = echarts.init(obj);
			}
			chart.setOption(option);
		}
	});

}