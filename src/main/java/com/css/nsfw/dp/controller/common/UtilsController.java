package com.css.nsfw.dp.controller.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.nsfw.dp.utils.Utils;


/**
 * Created by autod on 2017/5/17.
 */

@RestController
@RequestMapping(value = "/system")
public class UtilsController {

	@GetMapping(value = "/setSeesionParams")
	public void setSeesionParams(HttpServletRequest request, HttpServletResponse response, String timeSpan) {
		// 将查询年月日信息保存在session中
		request.getSession().setAttribute("timeSpan", timeSpan);
	}

	@GetMapping(value = "/setCookiesParams")
	public void setCookiesParams(HttpServletRequest request, HttpServletResponse response, String timeSpan) {
		// 将查询年月日信息保存在cookie中
		Cookie cookie = new Cookie("timeSpan", timeSpan);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@GetMapping(value = "/getSqlPage")
	public String getSqlPage(HttpServletRequest request, String pageId) {

		Cookie cookies[] = request.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");

		switch (pageId) {
		case "entity":
			return getSql("nfpt\\StationEntityTaxServerMapper", timeSpan, unitCode);
		case "online":
			return getSql("wt\\StationOnlineTaxServerMapper", timeSpan, unitCode);
		case "selfHelper":
			return getSql("arm\\StationSelfHelpTaxServerMapper", timeSpan, unitCode);
		case "12366":
			return getSql("nfpt\\Station12366Mapper", timeSpan, unitCode);
		default:
			break;
		}
		return pageId;
	}

	public String getSql(String fileName, String timeSpan, String unitCode) {
		String timeSpan_CN = "";
		switch (timeSpan) {
		case "D":
			timeSpan_CN = "当天";
			break;
		case "M":
			timeSpan_CN = "当月";
			break;
		case "Y":
			timeSpan_CN = "当年";
			break;
		default:
			break;
		}
		String fileFullName = "src\\main\\resources\\mapper\\" + fileName + ".xml";
		String str;
		File file = new File(fileFullName);
		try (FileReader reader = new FileReader(file); BufferedReader bReader = new BufferedReader(reader);) {
			StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
			sb.append("查询页面：" + fileName + "\n");
			sb.append("机构代码：" + unitCode + "\n");
			sb.append("时间段：" + timeSpan_CN + "\n");
			String s = "";
			while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
				sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
			}
			str = sb.toString();
		} catch (Exception e) {
			str = e.getMessage();
		}

		str = str.replace("\n", "**").replace("\t", "##").replace("\"", "'");
		str = StringEscapeUtils.escapeHtml(str);

		str = str.replace("**", "</br>").replace("##", "&nbsp;&nbsp;").replace("#{unitCode}", "'" + unitCode + "'")
				.replace("${unitCode}", "'" + unitCode + "'");

		switch (timeSpan) {
		case "D":
			str = str.replace("&lt;include refid='timeSpanSql'&gt;&lt;/include&gt;", "trunc(sysdate)");
			break;
		case "M":
			str = str.replace("&lt;include refid='timeSpanSql'&gt;&lt;/include&gt;", "trunc(sysdate,'MM')");
			break;
		case "Y":
			str = str.replace("&lt;include refid='timeSpanSql'&gt;&lt;/include&gt;", "trunc(sysdate,'yyyy')");
			break;
		default:
			break;
		}
		return str;
	}

}
