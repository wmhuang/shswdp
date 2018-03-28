package com.css.nsfw.dp.web.controller;

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

/**
 * Created by autod on 2017/5/17.
 */

@RestController
@RequestMapping(value = "/system")
public class UtilsController {

	@GetMapping(value = "/setSeesionParams")
	public void setSeesionParams(HttpServletRequest request,
			HttpServletResponse response, String timeSpan) {
		// 将查询年月日信息保存在cookie中
		request.getSession().setAttribute("timeSpan", timeSpan);
	}

	@GetMapping(value = "/setCookiesParams")
	public void setCookiesParams(HttpServletRequest request,
			HttpServletResponse response, String timeSpan) {
		// 将查询年月日信息保存在cookie中
		Cookie cookie = new Cookie("timeSpan", timeSpan);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@GetMapping(value = "/getEntityTaxServerSql")
	public String getEntityTaxServerSql() {
		return getSql("EntityTaxServerMapper");
	}

	@GetMapping(value = "/getOnlineTaxServerSql")
	public String getOnlineTaxServerSql() {
		return getSql("OnlineTaxServerMapper");
	}

	@GetMapping(value = "/getSelfHelpTaxServerSql")
	public String getSelfHelpTaxServerSql() {
		return getSql("SelfHelpTaxServerMapper");
	}

	public String getSql(String fileName) {
		String fileFullName = "src/main/resources/mapper/nfpt/" + fileName
				+ ".xml";
		String str;
		File file = new File(fileFullName);
		try (FileReader reader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(reader);) {
			StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
			String s = "";
			while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
				sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
			}
			str = sb.toString();
		} catch (Exception e) {
			str = e.getMessage();
		}
		str = str.replace("\n", "**").replace("\t", "##");
		str = StringEscapeUtils.escapeHtml(str);
		str = str.replace("**", "</br>").replace("##", "&nbsp;&nbsp;");
		return str;
	}

}
