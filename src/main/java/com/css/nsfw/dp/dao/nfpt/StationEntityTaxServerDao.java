package com.css.nsfw.dp.dao.nfpt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StationEntityTaxServerDao {
	// 分局实体窗口
	List getEntityWindows(Map<String, Object> params);

	// 服务明星排行榜
	List getFwmxphb(Map<String, Object> params);

	// 服务事项排行榜
	List getFwsxphb(Map<String, Object> params);

	// 窗口详细信息
	Map getDetailInfo(Map<String, Object> params);

	// 第一张屏饼状图
	List getPieEcharts(Map<String, Object> params);

	// 主页实体厅排行版
	List getIndexEntityRank(Map<String, Object> params);

	Map getTotalCount(Map<String, Object> params);
}
