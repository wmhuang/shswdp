package com.css.nsfw.dp.service.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.nfpt.EntityTaxServerDao;
import com.css.nsfw.dp.dao.wt.OnlineTaxServerDao;

@Component
public class EntityTaxServerService {
	@Autowired
	private EntityTaxServerDao entityTaxServerDao;

	@Autowired
	private OnlineTaxServerDao onlineTaxServerDao;

	// 取号数量TOP5
	public List getOfferCountRank() {
		return entityTaxServerDao.getOfferCountRank();
	}

	// 当前等待人数TOP5
	public List getWaitingCountRank() {

		return entityTaxServerDao.getWaitingCountRank();
	}

	// 已办理人数TOP5
	public List getHandledCountRank() {

		return entityTaxServerDao.getHandledCountRank();
	}

	// 业务受理总量排行榜TOP5
	public List getHandledBusinessRank(String timeSpan, String local) {
		List result = new ArrayList();
		if (StringUtils.isNotBlank(timeSpan) && StringUtils.isNotBlank(local)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("local", local);
			switch (timeSpan) {
			case "D":
				result = entityTaxServerDao.getHandledBusinessRankDay(params);
				break;
			case "M":
				result = entityTaxServerDao.getHandledBusinessRankMonth(params);
				break;
			case "Y":
				result = entityTaxServerDao.getHandledBusinessRankYear(params);
				break;

			default:
				break;
			}
		}
		return result;
	}

	// 业务受理类型排行榜TOP5
	public List getHandledBusinessTypeRank(String timeSpan) {
		List result = new ArrayList();
		if (StringUtils.isNotBlank(timeSpan)) {
			switch (timeSpan) {
			case "D":
				result = entityTaxServerDao.getHandledBusinessTypeRankDay();
				break;
			case "M":
				result = entityTaxServerDao.getHandledBusinessTypeRankMonth();
				break;
			case "Y":
				result = entityTaxServerDao.getHandledBusinessTypeRankYear();
				break;

			default:
				break;
			}
		}
		return result;
	}

	// 各事项按分局TOP5
	public List getSubStationHandledBusinessTypeRank(String timeSpan, String ywlxDm) {
		List result = new ArrayList();
		if (StringUtils.isNotBlank(timeSpan) && StringUtils.isNotBlank(ywlxDm)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ywlxDm", ywlxDm);
			switch (timeSpan) {
			case "D":
				result = entityTaxServerDao.getSubStationHandledBusinessTypeRankDay(params);
				break;
			case "M":
				result = entityTaxServerDao.getSubStationHandledBusinessTypeRankMonth(params);
				break;
			case "Y":
				result = entityTaxServerDao.getSubStationHandledBusinessTypeRankYear(params);
				break;
			default:
				break;
			}
		}
		return result;
	}

	// 全市通办分局TOP5
	public List getHandledCountByStationRank(String timeSpan) {
		List result = new ArrayList();
		if (StringUtils.isNotBlank(timeSpan)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("timeSpan", timeSpan);
			result = onlineTaxServerDao.getHandledCountByStationRank(params);
		}
		return result;
	}

	// 全市通办业务量TOP5
	public List getHandledCountByBusinessRank(String timeSpan) {
		List result = new ArrayList();
		if (StringUtils.isNotBlank(timeSpan)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("timeSpan", timeSpan);
			result = onlineTaxServerDao.getHandledCountByBusinessRank(params);
		}
		return result;
	}

	// 全市取号人数，已办理人数，当前等待人数
	public Map getOfferHandledWaittingCount() {

		return entityTaxServerDao.getOfferHandledWaittingCount();
	}

	// 办税大厅窗口情况
	public Map getTaxServerWindowStatistics() {

		return entityTaxServerDao.getTaxServerWindowStatistics();
	}

	// 当日预约办理情况
	public Map getSubScribeStatistics(String timeSpan) {
		Map result = new HashMap();
		if (StringUtils.isNotBlank(timeSpan)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("timeSpan", timeSpan);
			result = onlineTaxServerDao.getSubScribeStatistics(params);
		}
		return result;
	}

	// 话务总量，人工服务量，自动接听服务量
	public Map getDqbsfwtrs() {
		return entityTaxServerDao.getDqbsfwtrs();
	}

}
