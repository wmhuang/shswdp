package com.css.nsfw.dp.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.Station12366TaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/station12366")
public class Station12366TaxServerController {

	@Autowired
	private Station12366TaxServerService station12366TaxServerService;

	// 话务总量，人工服务量，自动接听服务量
	@GetMapping(value = "/getCallCount")
	public Map getCallCount(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getCallCount(timeSpan, unitCode);
	}

	// 接通率
	@GetMapping(value = "/getCallSuccess")
	public Map getCallSuccess(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getCallSuccess(timeSpan, unitCode);
	}

	// 满意率
	@GetMapping(value = "/getCallDegree")
	public Map getCallDegree(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getCallDegree(timeSpan, unitCode);
	}

	// 咨询热点 占比
	@GetMapping(value = "/getHotQuestion")
	public List getHotQuestion(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getHotQuestion(timeSpan, unitCode);
	}

	// 当前来电
	@GetMapping(value = "/getCurrentCallNum")
	public Map getCurrentCallNum(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getCurrentCallNum(unitCode);
	}

	// 首页问题类型占比
	@GetMapping(value = "/getQuestionTypePersent")
	public Map getQuestionTypePersent(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return station12366TaxServerService.getQuestionTypePersent(timeSpan, unitCode);
	}
}
