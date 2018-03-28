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
	$
			.ajax({
				url : ctx + '/stationSelfHelp/getArmByType',
				dataType : "json",
				data : {
					type : type
				},
				success : function(data) {
					$('#ARM_box_table').html('');
					for ( var i in data) {
						var tr = "<tr style='height: 80px;'>\n"
								+ "              <td style='width: 80px'>\n"
								+ "                <img src='/img/icon_ARM.png' />\n"
								+ "              </td>\n"
								+ "              <td class='ARM_box_table_td_bLine' style='width: 270px'>\n"
								+ "                <span class='span_white_28'>"
								+ data[i].NAME + "</span>\n"
								+ "              </td>\n" + "</tr>";

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
//首页arm机业务处理总量 和 环形图
function getIndexTotalCountAndPieEcharts(isIndex) {
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
			}
		} ]
	};
	$
			.ajax({
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
						$("#entity_total_count").html(data.value);
						chart = echarts.init(document
								.getElementById('selfhelper_pie'));
					} else {
						// data.value为处理业务总量
						// 点击年月日加载
						$("#pageIframe").contents().find("#entity_total_count").html(
								forInt(data.valueL));
					 
						var obj = document.getElementById("pageIframe").contentWindow.document
								.getElementById("selfhelper_pie");
						chart = echarts.init(obj);
					}
					chart.setOption(option);
				}
			});
}