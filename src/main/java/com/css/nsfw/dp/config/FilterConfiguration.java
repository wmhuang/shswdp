package com.css.nsfw.dp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
	@Bean
	public FilterRegistrationBean filterParamsRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		// 注入过滤器
		registration.setFilter(new ParamsFilter());
		// 拦截规则
		registration.addUrlPatterns("/station12366/*");
		registration.addUrlPatterns("/stationEntity/*");
		registration.addUrlPatterns("/stationOnline/*");
		registration.addUrlPatterns("/stationSelfHelp/*");
		// 过滤器名称
		registration.setName("ParamsFilter");
		// 是否自动注册 false 取消Filter的自动注册
		//registration.setEnabled(false);
		// 过滤器顺序
		registration.setOrder(1);
		
		return registration;
	}
}