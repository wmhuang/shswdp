package com.css.nsfw.dp.controller.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.main.OnlineTaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/onlineTaxServer")
public class OnlineTaxServerController {

	@Autowired
	private OnlineTaxServerService onlineTaxServerService;

	// 主页 业务受理情况
	@GetMapping(value = "/getYwslqk")
	public List getYwslqk(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getYwslqk(timeSpan);
	}

	// 预约总量 微信取号总量 业务受理总量 税企互动总量 合并的map
	@GetMapping(value = "/getAllMaps")
	public Map getAllMaps(HttpServletRequest request) {

		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		// 预约总量
		Map bespeakNum = onlineTaxServerService.getBespeakNum(timeSpan);
		// 微信取号总量
		Map wechatNum = onlineTaxServerService.getOfferTotalCountByWeChat(timeSpan);
		// 业务受理总量
		Map hanldedCount = onlineTaxServerService.getHanldedCount(timeSpan);
		// 税企互动总量
		Map taxAndCominteractNum = onlineTaxServerService.getTaxAndCominteractNum(timeSpan);
		bespeakNum.putAll(wechatNum);
		bespeakNum.putAll(hanldedCount);
		bespeakNum.putAll(taxAndCominteractNum);
		return bespeakNum;
	}

	// 业务受理总量TOP5
	@GetMapping(value = "/getHandldedRank")
	public List getHandldedRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getHandldedRank(timeSpan);
	}

	@GetMapping(value = "/getBusinessRankByStation")
	public List getBusinessRankByStation(HttpServletRequest request, String swjgDm) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getBusinessRankByStation(timeSpan, swjgDm);
	}

	@GetMapping(value = "/getOfferNumByWeChatRank")
	public List getOfferNumByWeChatRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getOfferNumByWeChatRank(timeSpan);
	}

	@GetMapping(value = "/getTbyyfj")
	public List getTbyyfj(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getTbyyfj(timeSpan);
	}

	@GetMapping(value = "/getBqyyfj")
	public List getBqyyfj(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getBqyyfj(timeSpan);
	}

	@GetMapping(value = "/getZdsxbll")
	public Map getZdsxbll(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return onlineTaxServerService.getZdsxbll(timeSpan);
	}
}
