//业务受理总量
function getBusiTotalCount(isIndex){
	$.ajax({
		url : ctx + '/selfTaxServer/getBusiTotalCount',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#busiTotalCount").html(forInt(data.COUNT));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#busiTotalCount").html(forInt(data.COUNT));
			}
		}
	})
}

//重点事项实时办理量
function getKeyBusiCount(isIndex){
	$.ajax({
		url : ctx + '/selfTaxServer/getKeyBusiCount',
		dataType : 'json',
		success : function(data) {
			if (typeof (isIndex) == 'undefined') {
				// 首次加载
				$("#fprz").html(forInt(data.FPRZ));
				$("#qddy").html(forInt(data.QDDY));
				$("#fpdk").html(forInt(data.FPDK));
			} else {
				// 点击年月日加载
				$("#pageIframe").contents().find("#fprz").html(forInt(data.FPRZ));
				$("#pageIframe").contents().find("#qddy").html(forInt(data.FPRZ));
				$("#pageIframe").contents().find("#fpdk").html(forInt(data.FPRZ));
			}
		}
	})
}
//业务受理量TOP5
function getBusiRank(isIndex){
	$.ajax({
		url : ctx + '/selfTaxServer/getBusiRank',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_self_1_table', 0, 2, data, isIndex);
		}
	})
}

//业务受理量TOP5
/*function getStationRankByBusi(isIndex){
	$.ajax({
		url : ctx + '/selfTaxServer/getStationRankByBusi',
		dataType : 'json',
		success : function(data) {
			pushRankTable('station_self_2_table', 0, 2, data, isIndex);
		}
	})
}
*/
//业务受理总量按分局TOP5
var swjgDmArr = new Array('13100430000', '13101010000', '13101040000', '13101050000', '13101060000', '13101070000', '13101090000', '13101100000', '13101120000', '13101130000',
		'13101140000', '13101150000', '13101410000', '13102260000', '13102270000', '13102280000', '13102290000', '13102300000');
var swjgMcArr= new Array('税务三分局', '黄浦区税务局', '徐汇区税务局', '长宁区税务局', '静安区税务局', '普陀区税务局', '虹口区税务局', '杨浦区税务局', '闵行区税务局', '宝山区税务局',
		'嘉定区税务局', '浦东新区税务局', '浦东新区税务局保税区税务分局', '奉贤区税务局', '松江区税务局', '金山区税务局', '青浦区税务局', '崇明区税务局');
var swjgDmIndex = 0;
function getStationRankByBusi(isIndex) {
	var currentSwjgDm = swjgDmArr[swjgDmIndex];
	$.ajax({
		url : ctx + '/selfTaxServer/getStationRankByBusi',
		dataType : 'json',
		data : {
			swjgDm : currentSwjgDm
		},
		success : function(data) {
			$('#station_self_2_rank').html(swjgMcArr[swjgDmIndex] + "自助业务TOP5");
			pushRankTable('station_self_2_table', 0, 2, data, isIndex);

		}
	});
	swjgDmIndex++;
	if (swjgDmIndex == swjgDmArr.length) {
		swjgDmIndex = 0;
	}
}

