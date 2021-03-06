package com.css.nsfw.dp.controller.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.main.TaxServer12366Service;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/taxServer12366")
public class TaxServer12366Controller {

	@Autowired
	private TaxServer12366Service taxServer12366Service;

	// 话务总量，人工服务量，自动接听服务量
	@GetMapping(value = "/getCallCount")
	public Map getCallCount(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getCallCount(timeSpan, unitCode);
	}

	// 接通率
	@GetMapping(value = "/getCallSuccess")
	public Map getCallSuccess(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getCallSuccess(timeSpan, unitCode);
	}

	// 满意率
	@GetMapping(value = "/getCallDegree")
	public Map getCallDegree(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getCallDegree(timeSpan, unitCode);
	}

	// 咨询热点 占比
	@GetMapping(value = "/getHotQuestion")
	public List getHotQuestion(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getHotQuestion(timeSpan, unitCode);
	}

	// 当前来电
	@GetMapping(value = "/getCurrentCallNum")
	public Map getCurrentCallNum(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getCurrentCallNum(unitCode);
	}

	// 首页问题类型占比
	@GetMapping(value = "/getQuestionTypePersent")
	public Map getQuestionTypePersent(HttpServletRequest request) throws Exception {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode12366");
		checkUntiCodeNotNull(unitCode);
		return taxServer12366Service.getQuestionTypePersent(timeSpan, unitCode);
	}

	public void checkUntiCodeNotNull(String unitCode) throws Exception {
		if (StringUtils.isBlank(unitCode)) {
			throw new Exception("该税务机关代码未匹配12366平台机关代码，请联系管理员。");
		}
	}
}
