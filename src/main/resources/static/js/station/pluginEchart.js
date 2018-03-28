//所有分局引入的html里面涉及的ajax都放置在这个js文件中 方便index。html中点击年月日之后更新调用方法
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

function getHotQuestion() {
	var datas = new Array();
	var jsons;
	datas.push({
		name : "咨询热点",
		symbolSize : 50,
		draggable : true,
		value : 27,
		label : {
			normal : {
				show : false,
				formatter : "{b}\n{c}",
				textStyle : {
					fontSize : '15',
					fontWeight : 'bold'
				}
			}
		},
		"itemStyle" : {
			normal : {
				color : "#009612"
			}
		}
	});
	var links = new Array();
	var cats = new Array();
	var json1 = new Array();
	var colors = [ '#d48265', '#9c27b0', '#009688', '#ca8622', '#009612',
			'#c71e93', '#571ec7', '#1ebfc7', '#c4c71e', '#c7641e' ];
	var map_colo = [];
	var a = 0;
	var bool = true;
	$.ajax({
		type : "get",
		url : ctx + "/station12366/getHotQuestion",
		async : false,
		data : {},
		dataType : "json",
		success : function(json) {
			jsons = json;
			// 处理表格
			getRanksTable(json);
			for (var i = 0; i < json.length; i++) {
				bool = true;
				for (var s = 0; s < json1.length; s++) {
					if (json[i].YJMC == json1[s]) {
						bool = false;
					}
				}
				if (bool) {
					json1.push(json[i].YJMC);
					datas.push({
						name : json[i].YJMC,
						symbolSize : getInt(800),
						category : json[i].YJMC,
						draggable : true,
						value : json[i].SL,
						label : {
							normal : {
								show : true,
								formatter : "{b}\n{c}",
								textStyle : {
									fontSize : 15,
									color : 'white',
									fontWeight : 'bold'
								}
							}
						},
						itemStyle : {
							normal : {
								color : colors[a]
							}
						}
					});
					links.push({
						source : "咨询热点",
						target : json[i].YJMC
					});
					cats.push({
						name : json[i].YJMC
					});
					map_colo.push({
						name : json[i].YJMC,
						value : colors[a]
					});
					++a;
				} else {
					for (var s = 0; s < datas.length; s++) {
						if (json[i].YJMC == datas[s].name) {
							datas[s].value = parseInt(datas[s].value)
									+ parseInt(json[i].SL);
						}
					}
				}
			}

			for (var i = 0; i < json.length; i++) {
				datas.push({
					name : json[i].EJMC,
					symbolSize : getInt(2),
					category : json[i].YJMC,
					draggable : true,
					value : json[i].SL,
					label : {
						normal : {
							show : true,
							formatter : "{b}\n{c}",
							position : 'top',
							color : '#fff',
							textStyle : {
								fontSize : 20,
								fontWeight : 'bold',
								color : '#fff',
							}
						}
					},
					itemStyle : {
						normal : {
							color : getcolor(json[i].YJMC)
						}
					}
				});
				links.push({
					source : json[i].YJMC,
					target : json[i].EJMC
				});
				cats.push({
					name : json[i].EJMC
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
	function getRanksTable(json) {

		console.log(json);

		var m = getTop5Color(json);

		var tableHtml = "<table style='border-collapse: collapse;'>" + "<tr>"
				+ "<td colspan='3' style='text-align:right'>"
				+ "<span class='span_yellow_30' >TOP5排行榜</span>" + "</td>"
				+ "</tr>";

		var total = 0;
		for (var j = 0; j < json.length; j++) {
			total += json[j].SL;
		}
		for (var i = 0; i < json.length; i++) {
			var yjmc = json[i].YJMC;
			if (i > 4) {
				// 控制排行榜的数量，4代表前5
				break;
			}
			var questionName = json[i].EJMC;
			var questionCount = json[i].SL;
			var questionPersent1 = json[i].SL / total;
			var questionPersent2 = questionPersent1 * 100;

			questionPersent2 = questionPersent2.toFixed(2) + "%";

			var tableTrHtml = "<tr >\n"
					+ "<td width='200px'><span class='span_white_26'>"
					+ questionName + "</span></td>\n"
					+ "<td width='70px'><span class='span_white_26'>"
					+ questionCount + "</span></td>\n"
					+ " <td width='100px'><span class='span_white_26'>"
					+ questionPersent2 + "</span></td>\n" + "</tr>";
			tableHtml += tableTrHtml;
		}
		tableHtml += "</table>";
		var rankDiv = document.getElementById('questType_table');
		rankDiv.innerHTML = tableHtml;
	}

	function getTop5Color(json) {
		var m = new Map();
		var colors = [ "rgba(212,130,101,1)", "rgba(156,39,176,1)",
				"rgba(0,150,136,1)" ];
		var currentColor = 0;
		for (var i = 0; i < json.length; i++) {
			var YJMC = json[i].YJMC;
			var color = m.get(YJMC);
			if (color == null) {
				m.set(YJMC, colors[currentColor]);
				currentColor++;
			}
		}
		return m;
	}

	function getcolor(n) {
		for (var i = 0; i < map_colo.length; i++) {
			if (n == map_colo[i].name) {
				return map_colo[i].value;
			}
		}
	}
	function getInt(i) {
		if (i > 100) {
			return fRandomBy(80, 100);
		} else if (i < 5) {
			return fRandomBy(40, 60);
		} else {
			return i;
		}
	}
	function fRandomBy(under, over) {
		switch (arguments.length) {
		case 1:
			return parseInt(Math.random() * under + 1);
		case 2:
			return parseInt(Math.random() * (over - under + 1) + under);
		default:
			return 0;
		}
	}

	option = {
		backgroundColor : '',
		tooltip : {},
		animationDuration : 1000,
		animationEasingUpdate : 'quinticInOut',
		series : [ {
			name : '咨询热点',
			type : 'graph',
			layout : 'force',

			force : {
				repulsion : 1000
			},
			data : datas,
			links : links,
			categories : cats,
			focusNodeAdjacency : true,
			roam : false,
			lineStyle : {
				normal : {
					color : 'source',
					curveness : 0,
					type : "solid"
				}
			}
		} ]
	};
	var chart = echarts.init(document.getElementById('hotQuestion'));
	chart.setOption(option);
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
			var chart = echarts.init(document
					.getElementById('station_selfHelpCountStatistics_line'));
			// 为echarts对象加载数据
			chart.setOption(option);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}

// 主页网厅环形图
function getBusinessTypePersent(isIndex) {
	var option = {
		color : [ '#FF9D35', '#AE62E0', '#E3E067', '#5BDBFF', '#7F9FFF',
				'#FFC500', '#3CB371', '#00FA9A', '#FF69B4' ],
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
	$
			.ajax({
				type : "get",
				url : ctx + "/stationOnline/getBusinessTypePersent",
				async : false,
				dataType : "json",
				success : function(data) {
					option.series[0].data = data;
					var chart;
					if (typeof (isIndex) == 'undefined') {
						chart = echarts.init(document
								.getElementById('online_pie'));

					} else {
						var obj = document.getElementById("pageIframe").contentWindow.document
								.getElementById("online_pie");
						chart = echarts.init(obj);
					}
					chart.setOption(option);
				}
			});

}

// 主页12366问题类型占比
function getQuestionTypePersent(isIndex) {
	var option = {
		color : [ '#FF9D35', '#AE62E0', '#E3E067', '#5BDBFF', '#7F9FFF',
				'#FFC500', '#3CB371', '#00FA9A', '#FF69B4' ],
		tooltip : {
			trigger : 'item',
			formatter : "{d}%<br/>{b}  "
		},
		calculable : false,
		series : [ {
			name : '问题类型',
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
		}, {
			name : '问题类型',
			type : 'pie',
			radius : [ 0, 70 ],
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
	$
			.ajax({
				type : "get",
				url : ctx + "/station12366/getQuestionTypePersent",
				async : false,
				dataType : "json",
				success : function(data) {
					option.series[0].data = data.outsideData;
					// 内圈 暂时不显示
					// option.series[1].data = data.insideData;
					var chart;
					if (typeof (isIndex) == 'undefined') {
						chart = echarts.init(document
								.getElementById('index_12366_pie'));
					} else {
						var obj = document.getElementById("pageIframe").contentWindow.document
								.getElementById("index_12366_pie");
						chart = echarts.init(obj);
					}
					chart.setOption(option);
				}
			});

}
