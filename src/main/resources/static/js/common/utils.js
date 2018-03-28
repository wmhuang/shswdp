//全局访问地址前缀
var ctx = "http://" + window.location.host;
// 全局变量 保存当前访问页面地址
var CURRENT_PAGE_NAME = "indexPage_station";
 
// 格式化数值（12122）为12,122的字符串
function forInt(num) {
	var result = [], counter = 0;
	num = (num || 0).toString().split('');
	for (var i = num.length - 1; i >= 0; i--) {
		counter++;
		result.unshift(num[i]);
		if (!(counter % 3) && i != 0) {
			result.unshift(',');
		}
	}
	return result.join('');
}
// 排行榜表格填充
function pushRankTable(tableId, intA, intB, data, isIndex) {
	if (typeof (isIndex) == 'undefined') {
		// 首次加载
		for (var i = 0, l = data.length; i < l; i++) {
			if (data[i].VALUE > 0) {
				$("#" + tableId + " tr:eq(" + i + ") td:eq(" + intA + ") span")
						.html(forInt(data[i].VALUE));
				$("#" + tableId + " tr:eq(" + i + ") td:eq(" + intB + ") span")
						.html(data[i].NAME);
			}
		}
	} else {
		// 清空表格
		for (var i = 0; i < 5; i++) {
			$("#pageIframe").contents().find(
					"#" + tableId + " tr:eq(" + i + ") td:eq(" + intA
							+ ") span").html('');
			$("#pageIframe").contents().find(
					"#" + tableId + " tr:eq(" + i + ") td:eq(" + intB
							+ ") span").html('');
		}

		// 通过年月日加载
		for (var i = 0, l = data.length; i < l; i++) {
			if(data[i].VALUE>0){
				$("#pageIframe").contents().find(
						"#" + tableId + " tr:eq(" + i + ") td:eq(" + intA
								+ ") span").html(forInt(data[i].VALUE));
				$("#pageIframe").contents().find(
						"#" + tableId + " tr:eq(" + i + ") td:eq(" + intB
								+ ") span").html(data[i].NAME);
			}
		}
	}
}

 
//ajax设置加载动画
$(function() {
	$.ajaxSetup({
		layerIndex : -1,
		beforeSend : function() {
			this.layerIndex = layer.load(0, {
				shade : [ 0.5, '#393D49' ]
			});
		},
		complete : function() {
			layer.close(this.layerIndex);
		},
		error : function() {
			layer.alert('部分数据加载失败，可能会导致页面显示异常，请刷新后重试', {
				skin : 'layui-layer-molv',
				closeBtn : 0,
				shift : 4
			// 动画类型
			});
		}
	});
});