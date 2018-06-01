package com.css.nsfw.dp.dao.wt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OnlineTaxServerDao {
	// 主页 业务受理情况
	List getywslqk(Map<String, Object> params);

	// 实体办税服务厅的通办业务受理top5
	public List getHandledCountByStationRank(Map<String, Object> params);

	// 实体办税服务厅的通办业务量TOP5
	public List getHandledCountByBusinessRank(Map<String, Object> params);

	// 实体办税服务厅的预约受理量
	public Map getSubScribeStatistics(Map<String, Object> params);
}
