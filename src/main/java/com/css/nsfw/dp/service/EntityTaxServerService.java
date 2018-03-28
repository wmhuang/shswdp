package com.css.nsfw.dp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.local.EntityTaxServerDao;

@Component
public class EntityTaxServerService {
	@Autowired
	private EntityTaxServerDao entityTaxServerDao;

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
	public List getHandledBusinessRank() {

		return entityTaxServerDao.getHandledBusinessRank();
	}

	// 饼状图 业务受理总量中心城区/郊区
	public Map getHandledBusinessArea() {

		return entityTaxServerDao.getHandledBusinessArea();
	}

	// 业务受理类型排行榜TOP5
	public List getHandledBusinessTypeRank() {

		return entityTaxServerDao.getHandledBusinessTypeRank();
	}

	// 各事项按分局TOP5
	public List getSubStationHandledBusinessTypeRank() {

		return entityTaxServerDao.getSubStationHandledBusinessTypeRank();
	}

	// 全市通办分局TOP5
	public List getHandledCountByStationRank() {

		return entityTaxServerDao.getHandledCountByStationRank();
	}

	// 全市通办业务量TOP5
	public List getHandledCountByBusinessRank() {

		return entityTaxServerDao.getHandledCountByBusinessRank();
	}

	// 全市取号人数，已办理人数，当前等待人数
	public Map getOfferHandledWaittingCount() {

		return entityTaxServerDao.getOfferHandledWaittingCount();
	}

	// 办税大厅窗口情况
	public Map getTaxServerWindowStatistics() {

		return entityTaxServerDao.getTaxServerWindowStatistics();
	}

	// 本区预约/跨区通办预约
	public Map getSubscribeByAreaCount() {

		return entityTaxServerDao.getSubscribeByAreaCount();
	}

	// 当日预约办理情况
	public Map getSubScribeTodayStatistics() {
		return entityTaxServerDao.getSubScribeTodayStatistics();
	}

}
