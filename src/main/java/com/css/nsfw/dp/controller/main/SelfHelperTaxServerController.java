package com.css.nsfw.dp.controller.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.main.SelfHelperTaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/selfTaxServer")
public class SelfHelperTaxServerController {
	@Autowired
	private SelfHelperTaxServerService selfHelperTaxServerService;

	// 业务受理总量
	@GetMapping(value = "/getBusiTotalCount")
	public Map getBusiTotalCount(HttpServletRequest request) {

		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return selfHelperTaxServerService.getBusiTotalCount(timeSpan);
	}

	// 重点事项实时办理量
	@GetMapping(value = "/getKeyBusiCount")
	public Map getKeyBusiCount(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return selfHelperTaxServerService.getKeyBusiCount(timeSpan);
	}

	// arm机区域分布
	@GetMapping(value = "/getArmLocation")
	public List getArmLocation(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return selfHelperTaxServerService.getArmLocation(timeSpan);
	}

	// 业务受理量TOP5
	@GetMapping(value = "/getBusiRank")
	public List getBusiRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		List list = selfHelperTaxServerService.getBusiRank(timeSpan);
		return list;
	}

	// 业务量分局TOP5
	@GetMapping(value = "/getStationRankByBusi")
	public List getStationRankByBusi(HttpServletRequest request,String swjgDm) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return selfHelperTaxServerService.getStationRankByBusi(timeSpan,swjgDm);
	}

}
