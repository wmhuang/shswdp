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

	// 实体办税服务厅的通办业务受理分局top5
	List getHandledCountByStationRank(Map<String, Object> params);

	// 实体办税服务厅的通办业务量TOP5
	List getHandledCountByBusinessRank(Map<String, Object> params);

	// 实体办税服务厅的预约受理量
	Map getSubScribeStatistics(Map<String, Object> params);

	// 预约总量
	Map getBespeakNum(Map<String, Object> params);

	// 微信取号总量
	Map getOfferTotalCountByWeChat(Map<String, Object> params);

	// 业务受理总量
	Map getHanldedCount(Map<String, Object> params);

	// 税企互动总量
	Map getTaxAndCominteractNum(Map<String, Object> params);

	// 网厅业务受理类型TOP5
	List getHandldedRank(Map<String, Object> params);

	// 业务受理类型TOP按分局
	List getBusinessRankByStation(Map<String, Object> params);

	// 微信取号业务量TOP5
	List getOfferNumByWeChatRank(Map<String, Object> params);

	// 通办预约分局TOP5
	List getTbyyfj(Map<String, Object> params);

	// 本区预约分局TOP5
	List getBqyyfj(Map<String, Object> params);

	// 重点事项办理量_增值税发票代开
	Map getZdsxbllZzsfpdk(Map<String, Object> params);

	// 重点事项办理量
	List getZdsxbll(Map<String, Object> params);
}
