package com.css.nsfw.dp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.wt.StationOnlineTaxServerDao;

@Component
public class StationOnlineTaxServerService {
	@Autowired
	private StationOnlineTaxServerDao stationOnlineTaxServerDao;

	// 当日预约数量
	public Map getBespeakNum(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getBespeakNum(params);
	}

	// 微信取号总量
	public Map getOfferTotalCountByWeChat(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getOfferTotalCountByWeChat(params);
	}

	// 业务受理总量
	public Map getHanldedCount(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getHanldedCount(params);
	}

	// 税企互动总量
	public Map getTaxAndCominteractNum(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getTaxAndCominteractNum(params);
	}

	// 跨区通办预约TOP5
	public List getHandledByAreaRank(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getHandledByAreaRank(params);
	}

	// 微信取号业务量TOP5
	public List getOfferNumByWeChatRank(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getOfferNumByWeChatRank(params);
	}

	// 业务受理总量排行榜TOP5
	public List getHandldedRank(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getHandldedRank(params);
	}

	// 重点事项实时办理量
	public List getHandledCountRealTime(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getHandledCountRealTime(params);
	}

	// 本区/跨区预约数量
	public Map getOfferNumByArea(String timeSpan) {
		return stationOnlineTaxServerDao.getOfferNumByArea(timeSpan);
	}

	// 网厅办理业务总量趋势图（折线图）
	public List getHanldedCountOnLine(String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getHanldedCountOnLine(params);
	}

	// 网厅当前在线人数
	public Map getOnlineNum(String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		return stationOnlineTaxServerDao.getOnlineNum(params);
	}

	// 网厅受理类型占比
	public List getBusinessTypePersent(String timeSpan, String unitCode) {
		List<Map<String, Object>> resultData = new ArrayList<>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		// 分组查询的额全部数据 每个业务类型的总数据量
		List o = stationOnlineTaxServerDao.getgetHandldedTotal(params);
		// 类型条数
		int oLength = o.size();
		// 其他的业务量
		int otherCount = 0;
		if (oLength > 1) {
			for (int i = 0; i < oLength; i++) {
				Map one = (Map) o.get(i);
				if (i > 4) {
					// 排名第五之后的归为其他
					otherCount += Integer.parseInt(one.get("VALUE").toString());
					 
				} else {
					Map<String, Object> seriesData = new HashMap<>();
					seriesData.put("name", one.get("XM_MC"));
					seriesData.put("value", one.get("VALUE"));
					 
					resultData.add(seriesData);
				}
			}
			Map<String, Object> seriesOtherData = new HashMap<>();
			seriesOtherData.put("name", "其他");
			seriesOtherData.put("value", otherCount);
			resultData.add(seriesOtherData);
		}
		return resultData;
	}
}
