package com.css.nsfw.dp.controller.station;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.service.station.StationSelfHelpTaxServerService;
import com.css.nsfw.dp.utils.Utils;

@RestController
@RequestMapping(value = "/stationSelfHelp")
public class StationSelfHelpTaxServerController {

	@Autowired
	private StationSelfHelpTaxServerService stationSelfHelpTaxServerService;

	// arm分类
	@GetMapping(value = "/getArmByType")
	public List getArmByType(HttpServletRequest request, String type) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		if (StringUtils.isBlank(unitCode) || StringUtils.isBlank(type)) {
			System.out.println("获取arm机分类时:参数为空！");
			return null;
		}
		return stationSelfHelpTaxServerService.getArmByType(unitCode, type);
	}

	// 自助终端arm业务总量排行榜
	@GetMapping(value = "/getArmHandledCountRank")
	public List getArmHandledCountRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return stationSelfHelpTaxServerService.getArmHandledCountRank(unitCode,
				timeSpan);
	}

	// 放置点用量排行top5
	@GetMapping(value = "/getArmUsedCountRank")
	public List getArmUsedCountRank(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return stationSelfHelpTaxServerService.getArmUsedCountRank(unitCode,
				timeSpan);
	}

	// 自助终端arm处理业务总量折线图
	@GetMapping(value = "/getArmHandledCountTotalLine")
	public Map<String, Object> getArmHandledCountTotalLine(
			HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return stationSelfHelpTaxServerService
				.getArmHandledCountTotalLine(unitCode);
	}

	// 自助办税终端业务总量
	@GetMapping(value = "/getArmHandledTotalCount")
	public Map getArmHandledTotalCount(
			HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		return stationSelfHelpTaxServerService
				.getArmHandledTotalCount(unitCode,timeSpan);
	}
}
