package com.css.nsfw.dp.controller.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.main.EntityTaxServerService;
import com.css.nsfw.dp.utils.Utils;

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
	public List getHandledBusinessRank(HttpServletRequest request, String local) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getHandledBusinessRank(timeSpan, local);
	}

	// 业务受理类型排行榜TOP5
	@GetMapping(value = "/getHandledBusinessTypeRank")
	public List getHandledBusinessTypeRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getHandledBusinessTypeRank(timeSpan);
	}

	// 各事项按分局TOP5
	@GetMapping(value = "/getSubStationHandledBusinessTypeRank")
	public List getSubStationHandledBusinessTypeRank(HttpServletRequest request, String currentType) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getSubStationHandledBusinessTypeRank(timeSpan, currentType);
	}

	// 全市通办分局TOP5
	@GetMapping(value = "/getHandledCountByStationRank")
	public List getHandledCountByStationRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getHandledCountByStationRank(timeSpan);
	}

	// 全市通办业务量TOP5
	@GetMapping(value = "/getHandledCountByBusinessRank")
	public List getHandledCountByBusinessRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getHandledCountByBusinessRank(timeSpan);
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

	// 当日预约办理情况
	@GetMapping(value = "/getSubScribeStatistics")
	public Map getSubScribeTodayStatistics(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return entityTaxServerService.getSubScribeStatistics(timeSpan);
	}

	// 主页 当前办税服务厅人数
	@GetMapping(value = "/getDqbsfwtrs")
	public Map getDqbsfwtrs(HttpServletRequest request) throws Exception {
		return entityTaxServerService.getDqbsfwtrs();
	}

}
