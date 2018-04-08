package com.css.nsfw.dp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.nsfw.dp.utils.Utils;

/**
 * Created by wmhuang on 2018/1/17.
 */

@Controller
@RequestMapping(value = "/view")
public class DpPageViewController {

	@Value("${main.station.code}")
	private String mainStationCode;

	@GetMapping(value = "/{page}")
	public String view1(HttpServletRequest request,
			@PathVariable("page") String page, Model model) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		model.addAttribute("unitCode", unitCode);
		if (StringUtils.isNotBlank(unitCode)) {
			if (mainStationCode.equals(unitCode)) {
				return page;
			} else {
				return "station/" + page;
			}
		} else {
			model.addAttribute("errorMsg", "访问过期，请刷新网页重试。");
			return "error";
		}
	}

	@GetMapping(value = "/common")
	public String common() {
		return "common/common";
	}

}
