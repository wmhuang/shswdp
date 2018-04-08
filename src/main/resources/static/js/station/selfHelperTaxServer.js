function click_tag_0() {
	$("#tag_0").css("background", "url(/img/bg_btn_2.png) no-repeat");
	$("#tag_1").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_2").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_3").css("background", "url(/img/bg_btn_1.png) no-repeat");
	updataArmTable("ALL")
}
function click_tag_1() {
	$("#tag_0").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_1").css("background", "url(/img/bg_btn_2.png) no-repeat");
	$("#tag_2").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_3").css("background", "url(/img/bg_btn_1.png) no-repeat");
	updataArmTable("24H")
}
function click_tag_2() {
	$("#tag_0").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_1").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_2").css("background", "url(/img/bg_btn_2.png) no-repeat");
	$("#tag_3").css("background", "url(/img/bg_btn_1.png) no-repeat");
	updataArmTable("QSTB")
}
function click_tag_3() {
	$("#tag_0").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_1").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_2").css("background", "url(/img/bg_btn_1.png) no-repeat");
	$("#tag_3").css("background", "url(/img/bg_btn_2.png) no-repeat");
	updataArmTable("LEAVE")
}
function updataArmTable(type) {

	$.ajax({
		url : ctx + '/stationSelfHelp/getArmByType',
		dataType : "json",
		data : {
			type : type
		},
		success : function(data) {
			$('#ARM_box_table').html('');
			for ( var i in data) {
				var tr = "<tr style='height: 80px;'>\n" + "              <td style='width: 80px'>\n" + "                <img src='/img/icon_ARM.png' />\n"
						+ "              </td>\n" + "              <td class='ARM_box_table_td_bLine' style='width: 270px'>\n" + "                <span class='span_white_28'>"
						+ data[i].NAME + "</span>\n" + "              </td>\n" + "</tr>";

				$('#ARM_box_table').append(tr);
			}
		}
	})
}
// arm机处理事项排行榜
function setArmHandledCountRank(isIndex) {
	$.ajax({
		url : ctx + '/stationSelfHelp/getArmHandledCountRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_selfHelp_1_table', 0, 2, data, isIndex);
		}
	})
}
// arm机放置点用量排行榜
function getArmUsedCountRank(isIndex) {
	$.ajax({
		url : ctx + '/stationSelfHelp/getArmUsedCountRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_selfHelp_2_table', 0, 2, data, isIndex);
		}
	})
}
// 首页arm机业务处理总量 和 环形图
function getIndexTotalCountAndPieEcharts(isIndex) {
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
			}
		} ]
	};
	$.ajax({
		type : "get",
		url : ctx + "/stationSelfHelp/getArmHandledTotalCount",
		async : false,
		dataType : "json",
		success : function(data) {
			// data.list为数据
			option.series[0].data = data.list;
			var chart;
			if (typeof (isIndex) == 'undefined') {
				// data.value为处理业务总量
				$("#selfHelper_total_count").html(forInt(data.value));
				chart = echarts.init(document.getElementById('selfhelper_pie'));
			} else {
				// data.value为处理业务总量
				// 点击年月日加载
				$("#pageIframe").contents().find("#selfHelper_total_count").html(forInt(data.valueL));

				var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("selfhelper_pie");
				chart = echarts.init(obj);
			}
			chart.setOption(option);
		}
	});
}
function selfHelpTaxServerLine() {
	$.ajax({
		type : "get",
		url : ctx + "/stationSelfHelp/getArmHandledCountTotalLine",
		async : false,
		dataType : "json",
		success : function(data) {
			var option = {
				color : [ '#26DBEB', '#CF973E', '#64FF8B', '#46A3FF' ],
				grid : {
				// y : 20,
				// x : 60,
				// y2 : 40
				},
				legend : {
					show : true,
					textStyle : {
						color : 'white',
						fontSize : 24
					},
					data : []
				},
				tooltip : {
					show : true

				},
				xAxis : {
					type : 'category',
					data : [],
					axisLabel : {
						show : true,
						textStyle : {
							color : "rgb(161,207,255)",
							// baseline : 'bottom',
							fontFamily : '微软雅黑',
							fontSize : 12
						}
					},
					axisLine : {
						show : true,
						lineStyle : {
							color : '#2262A7'
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
							color : '#2262A7'
						}
					},
					axisLine : {
						show : true,
						lineStyle : {
							color : 'rgb(38,81,360)'
						}
					}
				},
				series : [ {
					itemStyle : {
						normal : {
							lineStyle : {
								width : 3
							}
						}
					},

					data : [],
					type : 'line'
				}, {
					itemStyle : {
						normal : {
							lineStyle : {
								width : 2
							}
						}
					},

					data : [],
					type : 'line'
				}, {
					itemStyle : {
						normal : {
							lineStyle : {
								width : 2
							}
						}
					},

					data : [],
					type : 'line'
				}, {
					itemStyle : {
						normal : {
							lineStyle : {
								width : 2
							},
							label : {
								show : false,
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
				} ]

			};

			option.xAxis.data = data.时间;
			option.legend.data = data.businesNames;
			option.series[0].name = data.businesNames[0];
			option.series[0].data = data.total;
			option.series[1].name = data.businesNames[1];
			option.series[1].data = data.first;
			option.series[2].name = data.businesNames[2];
			option.series[2].data = data.second;
			option.series[3].name = data.businesNames[3];
			option.series[3].data = data.third;
			var chart = echarts.init(document.getElementById('station_selfHelpCountStatistics_line'));
			// 为echarts对象加载数据
			chart.setOption(option);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});

}