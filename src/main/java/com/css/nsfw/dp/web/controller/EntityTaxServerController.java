package com.css.nsfw.dp.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.EntityTaxServerService;

@RestController
@RequestMapping(value = "/entityTaxServer")
public class EntityTaxServerController {

	@Autowired
	private EntityTaxServerService entityTaxServerService;

	// 取号数量TOP5
	@GetMapping(value = "/getOfferCountRank")
	public List getOfferCountRank() {
		return entityTaxServerService.getOfferCountRank();
	}

	@GetMapping(value = "/getWaitingCountRank")
	public List getWaitingCountRank() {
		return entityTaxServerService.getWaitingCountRank();
	}

	// 已办理人数TOP5
	@GetMapping(value = "/getHandledCountRank")
	public List getHandledCountRank() {
		return entityTaxServerService.getHandledCountRank();
	}

	// 业务受理总量排行榜TOP5
	@GetMapping(value = "/getHandledBusinessRank")
	public List getHandledBusinessRank() {
		return entityTaxServerService.getHandledBusinessRank();
	}

	// 饼状图 业务受理总量中心城区/郊区
	@GetMapping(value = "/getHandledBusinessArea")
	public Map getHandledBusinessArea() {
		return entityTaxServerService.getHandledBusinessArea();
	}

	// 业务受理类型排行榜TOP5
	@GetMapping(value = "/getHandledBusinessTypeRank")
	public List getHandledBusinessTypeRank() {
		return entityTaxServerService.getHandledBusinessTypeRank();
	}

	// 各事项按分局TOP5
	@GetMapping(value = "/getSubStationHandledBusinessTypeRank")
	public List getSubStationHandledBusinessTypeRank(String currentType) {
		return entityTaxServerService.getSubStationHandledBusinessTypeRank();
	}

	// 全市通办分局TOP5
	@GetMapping(value = "/getHandledCountByStationRank")
	public List getHandledCountByStationRank() {
		return entityTaxServerService.getHandledCountByStationRank();
	}

	// 全市通办业务量TOP5
	@GetMapping(value = "/getHandledCountByBusinessRank")
	public List getHandledCountByBusinessRank() {
		return entityTaxServerService.getHandledCountByBusinessRank();
	}

	// 全市取号人数，已办理人数，当前等待人数
	@GetMapping(value = "/getOfferHandledWaittingCount")
	public Map getOfferHandledWaittingCount() {
		return entityTaxServerService.getOfferHandledWaittingCount();
	}

	// 办税大厅窗口情况
	@GetMapping(value = "/getTaxServerWindowStatistics")
	public Map getTaxServerWindowStatistics() {
		return entityTaxServerService.getTaxServerWindowStatistics();
	}

	// 本区预约/跨区通办预约
	@GetMapping(value = "/getSubscribeByAreaCount")
	public Map getSubscribeByAreaCount() {
		return entityTaxServerService.getSubscribeByAreaCount();
	}

	// 当日预约办理情况
	@GetMapping(value = "/getSubScribeTodayStatistics")
	public Map getSubScribeTodayStatistics() {
		return entityTaxServerService.getSubScribeTodayStatistics();
	}

}
