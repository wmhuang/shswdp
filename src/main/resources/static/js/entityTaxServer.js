$(function() {
	// 取号数量TOP5
	function getOfferCountRank() {
		$
				.ajax({
					type : "get",
					url :  ctx + "/entityTaxServer/getOfferCountRank",
					async : false,
					data : {},
					dataType : "json",
					success : function(data) {
						$("#rank_1_title").html("取号数量排行榜TOP5");
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
				$("#rank_1_title").html("代办理人数排行榜TOP5");
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
				$("#rank_1_title").html("已办理人数排行榜TOP5");
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
	// 业务受理总量排行榜TOP5
	function getHanledBusinessRank() {
		$.ajax({
			type : "get",
			url : ctx +  "/entityTaxServer/getHandledBusinessRank",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				pushRankTable("handledBusiness_rank", 0, 2, data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}
	// 控制业务受理总量排行和业务受理总量按区域划分自动切换
	var showRank = true;
	function handledBusinessChange() {
		if (showRank) {
			$("#handledBusiness_title").html("业务受理总量排行榜TOP5");
			getHanledBusinessRank();
			$("#handledBusiness_rank").show();
			$("#handledBusiness_pie").hide();
		} else {
			$("#handledBusiness_title").html("业务受理总量区域分布");
			$("#handledBusiness_pie").show();
			$("#handledBusiness_rank").hide();
		}
		showRank = !showRank;
	}

	// handledBusinessType_table 业务受理类型TOP5/各事项按分局TOP5
	// 根据业务受理类型 按分局展示
	var showTypeRank = true;
	var typeArr = new Array();
	var typeLength;
	var currentTypeIndex = 0;
	function getHandledBusinessTypeRank() {
		if (showTypeRank) {
			// 业务受理类型TOP5
			$.ajax({
				type : "get",
				url : ctx +  "/entityTaxServer/getHandledBusinessTypeRank",
				async : false,
				data : {},
				dataType : "json",
				success : function(data) {
					$("#handledBusinessType_title").html("业务受理类型TOP5");
					pushRankTable("handledBusinessType_table", 0, 2, data);
					$("#handledBusinessType_title").css("font-size","30px");
					$(".small_size_font").css("font-size", "24px");
					$(".box_table_left_1").css("width","20%");
					$(".box_table_right_1").css("width","70%");
					typeLength = data.length;
					for (var i = 0; i < typeLength; i++) {
						typeArr[i] = data[i].NAME;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

				}
			});
			showTypeRank = false;
		} else {
			var currentType = typeArr[currentTypeIndex];
			if (currentTypeIndex == typeLength - 1) {
				showTypeRank = true;
				currentTypeIndex = 0;
			} else {
				currentTypeIndex++;
			}
			// 各事项按分局TOP5
			$
					.ajax({
						type : "get",
						url : ctx +  "/entityTaxServer/getSubStationHandledBusinessTypeRank",
						async : false,
						data : {
							currentType : currentType
						},
						dataType : "json",
						success : function(data) {
							$("#handledBusinessType_title").html(currentType+"分局TOP5");
							pushRankTable("handledBusinessType_table", 0, 2,
									data);
							$("#handledBusinessType_title").css("font-size","24px");
							$(".small_size_font").css("font-size", "30px");
							$(".box_table_left_1").css("width","45%");
							$(".box_table_right_1").css("width","45%");
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {

						}
					});
		}

	}

	// 全市通办业务量TOP5/全市通办业务量TOP5
	var showByStation = true;
	function getHandledCountByStationOrBusinessRank() {
		if (showByStation) {
			// 全市通办业务量TOP5
			$
					.ajax({
						type : "get",
						url : ctx +  "/entityTaxServer/getHandledCountByStationRank",
						async : false,
						data : {},
						dataType : "json",
						success : function(data) {
							$("#getCountByStaionOrBusiness_title").html(
									"全市通办分局TOP5");
							pushRankTable("getCountByStaionOrBusiness_table",
									0, 2, data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {

						}
					});
		} else {
			// 全市通办业务量TOP5
			$
					.ajax({
						type : "get",
						url : ctx +  "/entityTaxServer/getHandledCountByBusinessRank",
						async : false,
						data : {},
						dataType : "json",
						success : function(data) {
							$("#getCountByStaionOrBusiness_title").html(
									"全市通办业务量TOP5");
							pushRankTable("handledBusinessType_table", 0, 2,
									data);
						},
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {

						}
					});
		}
		showByStation = !showByStation;
	}

	// 取号人数、已办理人数、当前等待人数
	function getOfferNumber() {
		$.ajax({
			type : "get",
			url : ctx +  "/entityTaxServer/getOfferHandledWaittingCount",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#offerNumber").html(forInt(data.OFFER));
				$("#handledNumber").html(forInt(data.HANDLED));
				$("#waitingNumber").html(forInt(data.WAIT));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}

	// 办税大厅窗口情况
	function getTaxServerWindowStatistics() {
		$.ajax({
			type : "get",
			url : ctx +  "/entityTaxServer/getTaxServerWindowStatistics",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#openWindowNum").html(forInt(data.OPEN));
				$("#pauseWindowNum").html(forInt(data.PAUSE));
				$("#closeWindowNum").html(forInt(data.CLOSE));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}

	// 当日预约办理情况
	function getSubScribeTodayStatistics() {
		$.ajax({
			type : "get",
			url : ctx +  "/entityTaxServer/getSubScribeTodayStatistics",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				$('#subScribeTodayStatistics').html(forInt(data.COUNT));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

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

	// 控制所有的改变入口
	function initChange() {
		getServerCountRank();
		handledBusinessChange();
		getHandledBusinessTypeRank();
		getHandledCountByStationOrBusinessRank();
		getOfferNumber();
		getTaxServerWindowStatistics();
		getSubScribeTodayStatistics();
		changeTaxServerWindowStatisticsShowType();
	}
	initChange();
	window.setInterval(initChange, 10000);

})