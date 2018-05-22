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
				$("#pageIframe").contents().find("#ldzl_value").html(forInt(data.LDZL));
				$("#pageIframe").contents().find("#rgyyfwl_value").html(forInt(data.RGYYFWL));
				$("#pageIframe").contents().find("#zdyyfwl_value").html(forInt(data.ZDYYFWL));
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
// 首页来电总量
function getCallCountTotal(isIndex) {
	$.ajax({
		url : ctx + '/station12366/getCallCount',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#call_total_num").html(forInt(data.LDZL));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#call_total_num").html(forInt(data.LDZL));

			}

		}
	})
}
// 首页当前来电人数
function getCurrentCallNum(isIndex) {
	$.ajax({
		url : ctx + '/station12366/getCurrentCallNum',
		dataType : "json",
		success : function(data) {
			if (typeof (isIndex) == "undefined") {
				// 首次加载
				$("#call_current_num").html(forInt(data.VALUE));

			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#call_current_num").html(forInt(data.VALUE));

			}

		}
	})
}

function getHotQuestion(isIndex) {
	var datas = new Array();
	var jsons;
	datas.push({
		name : "咨询热点",
		symbolSize : 50,
		draggable : true,
		value : 0,
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
	var colors = [ '#d48265', '#9c27b0', '#009688', '#ca8622', '#009612', '#c71e93', '#571ec7', '#1ebfc7', '#c4c71e', '#c7641e' ];
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
			getRanksTable(json,isIndex);
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
							datas[s].value = parseInt(datas[s].value) + parseInt(json[i].SL);
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
							color : getcolor(json[i].YJMC,map_colo)
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
	
	var option = {
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
	var chart;
	if (typeof (isIndex) == 'undefined') {
		// data.value为处理业务总量
		chart = echarts.init(document.getElementById('hotQuestion'));
	} else {

		var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("hotQuestion");
		chart = echarts.init(obj);
	}
	chart.setOption(option);
}
function getRanksTable(json,isIndex) {
	var m = getTop5Color(json);
	var tableHtml = "<table style='border-collapse: collapse;'>" + "<tr>" + "<td colspan='3' style='text-align:right'>" + "<span class='span_yellow_30' >TOP5排行榜</span>"
			+ "</td>" + "</tr>";

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

		var tableTrHtml = "<tr >\n" + "<td width='200px'><span class='span_white_26'>" + questionName + "</span></td>\n" + "<td width='70px'><span class='span_white_26'>"
				+ questionCount + "</span></td>\n" + " <td width='100px'><span class='span_white_26'>" + questionPersent2 + "</span></td>\n" + "</tr>";
		tableHtml += tableTrHtml;
	}
	tableHtml += "</table>";
	var redDiv;
	if (typeof (isIndex) == 'undefined') {
		redDiv=document.getElementById('questType_table');
	} else {
		redDiv = document.getElementById("pageIframe").contentWindow.document.getElementById("questType_table");
	}
	redDiv.innerHTML=tableHtml;

}

function getTop5Color(json) {
	var m = new Map();
	var colors = [ "rgba(212,130,101,1)", "rgba(156,39,176,1)", "rgba(0,150,136,1)" , "rgba(0,136,150,1)"];
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

function getcolor(n,map_colo) {
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
// 主页12366问题类型占比
function getQuestionTypePersent(isIndex) {
	var option = {
		color : [ '#FF9D35', '#AE62E0', '#E3E067', '#5BDBFF', '#7F9FFF', '#FFC500', '#3CB371', '#00FA9A', '#FF69B4' ],
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
	$.ajax({
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
				chart = echarts.init(document.getElementById('index_12366_pie'));
			} else {
				var obj = document.getElementById("pageIframe").contentWindow.document.getElementById("index_12366_pie");
				chart = echarts.init(obj);
			}
			chart.setOption(option);
		}
	});

}