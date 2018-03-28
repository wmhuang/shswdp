package com.css.nsfw.dp.dao.local;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface EntityTaxServerDao {
	// 取号数量TOP5
	public List getOfferCountRank();

	// 当前等待人数TOP5
	public List getWaitingCountRank();

	// 已办理人数TOP5
	public List getHandledCountRank();

	// 业务受理总量排行榜TOP5
	public List getHandledBusinessRank();

	// 饼状图 业务受理总量中心城区/郊区
	public Map getHandledBusinessArea();

	// 业务受理类型排行榜TOP5
	public List getHandledBusinessTypeRank();

	// 各事项按分局TOP5
	public List getSubStationHandledBusinessTypeRank();

	// 全市通办分局TOP5
	public List getHandledCountByStationRank();

	// 全市通办业务量TOP5
	public List getHandledCountByBusinessRank();

	// 全市取号人数，已办理人数，当前等待人数
	public Map getOfferHandledWaittingCount();

	// 办税大厅窗口情况
	public Map getTaxServerWindowStatistics();

	// 本区预约/跨区通办预约
	public Map getSubscribeByAreaCount();

	// 当日预约办理情况
	public Map getSubScribeTodayStatistics();

}
