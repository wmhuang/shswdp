package com.css.nsfw.dp.dao.wt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StationOnlineTaxServerDao {
	// 预约总量
	Map getBespeakNum(Map<String, Object> params);

	// 微信取号总量
	Map getOfferTotalCountByWeChat(Map<String, Object> params);

	// 业务受理总量
	Map getHanldedCount(Map<String, Object> params);

	// 税企互动总量
	Map getTaxAndCominteractNum(Map<String, Object> params);

	// 跨区通办预约TOP5
	List getHandledByAreaRank(Map<String, Object> params);

	// 微信取号业务量TOP5
	List getOfferNumByWeChatRank(Map<String, Object> params);

	// 业务受理总量排行榜TOP5
	List getHandldedRank(Map<String, Object> params);

	// 重点事项实时办理量
	List getHandledCountRealTime(Map<String, Object> params);

	// 本区/跨区预约数量 合并到预约总量中
	Map getOfferNumByArea(String timeSpan);

	// 网厅办理业务总量趋势图（折线图）
	List getHanldedCountOnLine(Map<String, Object> params);

	// 当前在线人数
	Map getOnlineNum(Map<String, Object> params);

	// 业务受理类型数据量
	List getHandldedTotal(Map<String, Object> params);
}
