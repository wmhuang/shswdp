/**
 * 
 */
package com.css.nsfw.dp.dao.nfpt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author wmhuang
 *
 */
@Component
@Mapper
public interface EntityTaxServerDao {
	// 主页 当前办税服务厅人数
	Map getDqbsfwtrs();

	// 取号数量TOP5
	public List getOfferCountRank();

	// 当前等待人数TOP5
	public List getWaitingCountRank();

	// 已办理人数TOP5
	public List getHandledCountRank();

	// 业务受理总量排行榜TOP5
	public List getHandledBusinessRankDay(Map<String, Object> params);

	public List getHandledBusinessRankMonth(Map<String, Object> params);

	public List getHandledBusinessRankYear(Map<String, Object> params);

	// 业务受理类型TOP5
	public List getHandledBusinessTypeRankDay();

	public List getHandledBusinessTypeRankMonth();

	public List getHandledBusinessTypeRankYear();

	// 各事项按分局TOP5
	public List getSubStationHandledBusinessTypeRankDay(Map<String, Object> params);

	public List getSubStationHandledBusinessTypeRankMonth(Map<String, Object> params);

	public List getSubStationHandledBusinessTypeRankYear(Map<String, Object> params);

	// 全市取号人数，已办理人数，当前等待人数
	public Map getOfferHandledWaittingCount();

	// 办税大厅窗口情况
	public Map getTaxServerWindowStatistics();

	// 本区预约/跨区通办预约
	public Map getSubscribeByAreaCount();

	// 当日预约办理情况
	public Map getSubScribeTodayStatistics();
}
