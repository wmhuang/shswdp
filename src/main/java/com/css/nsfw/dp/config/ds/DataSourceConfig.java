package com.css.nsfw.dp.config.ds;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean(name = "armDataSource")
	@ConfigurationProperties(prefix = "arm.datasource")
	// application.properteis中对应属性的前缀
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	//
	@Bean(name = "nfptDataSource")
	@ConfigurationProperties(prefix = "nfpt.datasource")
	// application.properteis中对应属性的前缀
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "wtDataSource")
	@ConfigurationProperties(prefix = "wt.datasource")
	// application.properteis中对应属性的前缀
	public DataSource dataSource3() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "localDataSource")
	@ConfigurationProperties(prefix = "local.datasource")
	// application.properteis中对应属性的前缀
	public DataSource dataSource10() {
		return DataSourceBuilder.create().build();
	}
}