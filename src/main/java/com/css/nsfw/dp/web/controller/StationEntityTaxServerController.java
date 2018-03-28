package com.css.nsfw.dp.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.StationEntityTaxServerService;

@RestController
@RequestMapping(value = "/stationEntity")
public class StationEntityTaxServerController {
	
	@Autowired
	private StationEntityTaxServerService stationEntityTaxServerService;

	// 实体窗口情况
	@GetMapping(value = "/getEntityWindows")
	public Map getEntityWindows(HttpServletRequest request) {
		return stationEntityTaxServerService.getEntityWindows((String) request
				.getSession().getAttribute("timeSpan"));
	}

 
}
