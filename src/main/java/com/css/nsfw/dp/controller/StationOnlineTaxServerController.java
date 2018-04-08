package com.css.nsfw.dp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.StationOnlineTaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/stationOnline")
public class StationOnlineTaxServerController {

	@Autowired
	private StationOnlineTaxServerService onlineTaxServerStationService;

	// 预约总量 微信取号总量 业务受理总量 税企互动总量 合并的map
	@GetMapping(value = "/getAllMaps")
	public Map getAllMaps(HttpServletRequest request) {

		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");

		// 预约总量
		Map bespeakNum = onlineTaxServerStationService.getBespeakNum(timeSpan, unitCode);
		// 微信取号总量
		Map wechatNum = onlineTaxServerStationService.getOfferTotalCountByWeChat(timeSpan, unitCode);
		// 业务受理总量
		Map hanldedCount = onlineTaxServerStationService.getHanldedCount(timeSpan, unitCode);
		// 税企互动总量
		Map taxAndCominteractNum = onlineTaxServerStationService.getTaxAndCominteractNum(timeSpan, unitCode);
		bespeakNum.putAll(wechatNum);
		bespeakNum.putAll(hanldedCount);
		bespeakNum.putAll(taxAndCominteractNum);
		return bespeakNum;
	}

	// 本区预约 跨区预约
	@GetMapping(value = "/getAreaMaps")
	public Map getAreaMap(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		// 预约总量
		Map bespeakNum = onlineTaxServerStationService.getBespeakNum(timeSpan, unitCode);
		return bespeakNum;
	}

	// 预约总量
	@GetMapping(value = "/getBespeakNum")
	public Map getBespeakNum(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getBespeakNum(timeSpan, unitCode);
	}

	// 微信取号总量
	@GetMapping(value = "/getOfferTotalCountByWeChat")
	public Map getOfferTotalCountByWeChat(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getOfferTotalCountByWeChat(timeSpan, unitCode);
	}

	// 业务受理总量
	@GetMapping(value = "/getHanldedCount")
	public Map getHanldedCount(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getHanldedCount(timeSpan, unitCode);
	}

	// 税企互动总量
	@GetMapping(value = "/getTaxAndCominteractNum")
	public Map getTaxAndCominteractNum(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getTaxAndCominteractNum(timeSpan, unitCode);
	}

	// 跨区通办预约TOP5
	@GetMapping(value = "/getHandledByAreaRank")
	public List getHandledByAreaRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getHandledByAreaRank(timeSpan, unitCode);
	}

	// 微信取号业务量TOP5
	@GetMapping(value = "/getOfferNumByWeChatRank")
	public List getOfferNumByWeChatRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getOfferNumByWeChatRank(timeSpan, unitCode);
	}

	// 业务受理总量排行榜TOP5
	@GetMapping(value = "/getHandldedRank")
	public List getHandldedRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getHandldedRank(timeSpan, unitCode);
	}

	// 重点事项实时办理量
	@GetMapping(value = "/getHandledCountRealTime")
	public List getHandledCountRealTime(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getHandledCountRealTime(timeSpan, unitCode);

	}

	// 本区/跨区预约数量
	@GetMapping(value = "/getOfferNumByArea")
	public Map getOfferNumByArea(HttpServletRequest request) {
		return onlineTaxServerStationService.getOfferNumByArea((String) request.getSession().getAttribute("timeSpan"));
	}

	// 网厅办理业务总量趋势图（折线图）
	@GetMapping(value = "/getHanldedCountOnLine")
	public List getHanldedCountOnLine(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getHanldedCountOnLine(unitCode);
	}

	// 当前办理人数
	@GetMapping(value = "/getOnlineNum")
	public Map getOnlineNum(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getOnlineNum(unitCode);
	}

	// 办理业务量类型占比
	@GetMapping(value = "/getBusinessTypePersent")
	public List getBusinessTypePersent(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return onlineTaxServerStationService.getBusinessTypePersent(timeSpan, unitCode);
	}
}
