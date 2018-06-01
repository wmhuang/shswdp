package com.css.nsfw.dp.controller.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.nsfw.dp.service.common.DpIndexViewService;
import com.css.nsfw.dp.service.station.Station12366TaxServerService;
import com.css.nsfw.dp.utils.Utils;

/**
 * Created by autod on 2017/5/17.
 */

@Controller
@RequestMapping(value = "/index")
public class DpIndexViewController {
	@Autowired
	private DpIndexViewService dpIndexViewService;
	@Autowired
	private Station12366TaxServerService station12366TaxServerService;

	@Value("${main.station.code}")
	private String mainStationCode;

	@Value("${default.timeSpan}")
	private String defaultTimeSpan;

	@GetMapping(value = "/{unitCode}")
	public String view(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("unitCode") String unitCode, Model model) {
		Cookie cookies[] = request.getCookies();
		
		String unitCode12366 = station12366TaxServerService.changeUnitCode(unitCode);
		 
		try {
			model.addAttribute("unitName", dpIndexViewService.getUnitNameByCode(unitCode));
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}

		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		if (StringUtils.isBlank(timeSpan)) {
			//如果cookie中没有信息
			timeSpan = defaultTimeSpan;
		}
		// 返回前台 用于展示
		model.addAttribute("timeSpan", timeSpan);

		Cookie cookie = new Cookie("unitCode", unitCode);
		Cookie cookie1 = new Cookie("timeSpan", timeSpan);
		Cookie cookie2 = new Cookie("unitCode12366", unitCode12366);
		cookie.setPath("/");
		cookie1.setPath("/");
		cookie2.setPath("/");
		response.addCookie(cookie);
		response.addCookie(cookie1);
		response.addCookie(cookie2);

		if (StringUtils.isNotBlank(unitCode)) {
			request.getSession().setAttribute("unitCode", unitCode);
			if (mainStationCode.equals(unitCode)) {
				return "main/index";
			} else {
				return "station/index";
			}

		} else {
			return "common/error";
		}
	}

}
