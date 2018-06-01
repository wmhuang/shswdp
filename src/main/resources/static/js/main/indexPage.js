//当前办税服务厅人数
function getDqbsfwtrs() {
	$.ajax({
		url : '/entityTaxServer/getDqbsfwtrs',
		success : function(result) {
			$('#dqbsfwtrs').html(forInt(result.COUNT));
		}
	})
}

//业务受理情况
function getYwslqk(isIndex){
	$.ajax({
		url : '/onlineTaxServer/getYwslqk',
		success : function(result) {
			pushRankTable('indexRightTable', 0, 2, result, isIndex);
		}
	})
}