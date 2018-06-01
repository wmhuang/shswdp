package com.css.nsfw.dp.controller.main;

import java.util.List;

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
}
