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
@MapperScan(basePackages = {"com.css.nsfw.dp.dao.local"}, sqlSessionFactoryRef = "sqlSessionFactory0")
public class MybatisDbLocalConfig {

    @Autowired
    @Qualifier("localDataSource")
    private DataSource localDataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactory0() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(localDataSource); // local数据库
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()  
        .getResources("classpath:mapper/local/*.xml"));  
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory0()); // 使用上面配置的Factory
        return template;
    }
}