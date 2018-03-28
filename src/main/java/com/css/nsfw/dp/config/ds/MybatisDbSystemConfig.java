package com.css.nsfw.dp.config.ds;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = { "com.css.nsfw.dp.dao.system" }, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisDbSystemConfig {
	@Autowired
	@Qualifier("systemDataSource")
	private DataSource systemDataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(systemDataSource);
		factoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources("classpath:mapper/system/*.xml"));
		return factoryBean.getObject();

	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(
				sqlSessionFactory2());
		return template;
	}
}