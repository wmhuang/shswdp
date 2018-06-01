package com.css.nsfw.dp.controller.station;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.station.StationEntityTaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/stationEntity")
public class StationEntityTaxServerController {

	@Autowired
	private StationEntityTaxServerService stationEntityTaxServerService;

	// 实体窗口情况
	@GetMapping(value = "/getEntityWindows")
	public Map getEntityWindows(HttpServletRequest request, String busiType) {

		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");

		return stationEntityTaxServerService.getEntityWindows(unitCode, busiType);

	}

	// 窗口具体信息
	@GetMapping(value = "/getDetailInfo")
	public Map getDetailInfo(String dtdm, String ckhm) {
		return stationEntityTaxServerService.getDetailInfo(dtdm, ckhm);
	}

	// 服务事项排行榜
	@GetMapping(value = "/getFwsxphb")
	public List getFwsxphb(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return stationEntityTaxServerService.getFwsxphb(unitCode, timeSpan);
	}

	// 服务明星排行榜
	@GetMapping(value = "/getFwmxphb")
	public List getFwmxphb(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return stationEntityTaxServerService.getFwmxphb(unitCode, timeSpan);
	}

	// 饼状图
	@GetMapping(value = "/getPieEcharts")
	public List getPieEcharts(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return stationEntityTaxServerService.getPieEcharts(unitCode, timeSpan);
	}

	// 主页实体厅排行榜

	@GetMapping(value = "/getIndexEntityRank")
	public List getIndexEntityRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return stationEntityTaxServerService.getIndexEntityRank(unitCode, timeSpan);
	}

	// 主页办税业务厅业务总量
	@GetMapping(value = "/getTotalCount")
	public Map getTotalCount(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		return stationEntityTaxServerService.getTotalCount(unitCode, timeSpan);
	}
}
