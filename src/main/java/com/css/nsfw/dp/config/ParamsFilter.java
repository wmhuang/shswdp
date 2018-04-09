package com.css.nsfw.dp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.css.nsfw.dp.utils.Utils;

public class ParamsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 检查每个查询请求的组织机构和时间参数不能为空

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie cookies[] = httpRequest.getCookies();
		String timeSpan = Utils.getCookieValueByName(cookies, "timeSpan");
		String unitCode = Utils.getCookieValueByName(cookies, "unitCode");
		if (StringUtils.isBlank(timeSpan) || StringUtils.isBlank(unitCode)) {
			throw new ServletException();
		} else {
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}
}