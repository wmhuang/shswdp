package com.css.nsfw.dp.controller;

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

import com.css.nsfw.dp.service.DpIndexViewService;
import com.css.nsfw.dp.utils.Utils;

/**
 * Created by autod on 2017/5/17.
 */

@Controller
@RequestMapping(value = "/index")
public class DpIndexViewController {
	@Autowired
	private DpIndexViewService dpIndexViewService;

	@Value("${main.station.code}")
	private String mainStationCode;

	@Value("${default.timeSpan}")
	private String defaultTimeSpan;

	@GetMapping(value = "/{unitCode}")
	public String view(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("unitCode") String unitCode, Model model) {
		Cookie cookies[] = request.getCookies();
		try {
			model.addAttribute("unitName",
					dpIndexViewService.getUnitNameByCode(unitCode));
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "error";
		}

		// String timeSpan = (String) request.getSession()
		// .getAttribute("timeSpan");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		if (StringUtils.isBlank(timeSpan)) {
			// 如果session中没有timeSpan信息 则将默认时间间隔参数保存在session中
//			request.getSession().setAttribute("timeSpan", defaultTimeSpan);
			timeSpan = defaultTimeSpan;
		}
		// 返回前台 用于展示
		model.addAttribute("timeSpan", timeSpan);

		Cookie cookie = new Cookie("unitCode", unitCode);
		Cookie cookie1 = new Cookie("timeSpan", timeSpan);
		cookie.setPath("/");
		cookie1.setPath("/");
		response.addCookie(cookie);
		response.addCookie(cookie1);

		// HttpCookie cookie =new HttpCookie("timeSpan", timeSpan);
		if (StringUtils.isNotBlank(unitCode)) {
			request.getSession().setAttribute("unitCode", unitCode);
			if (mainStationCode.equals(unitCode)) {
				return "index";
			} else {
				return "station/index";
			}

		} else {
			return "error";
		}
	}

}
