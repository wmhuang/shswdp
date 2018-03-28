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
@MapperScan(basePackages = {"com.css.nsfw.dp.dao.wt"}, sqlSessionFactoryRef = "sqlSessionFactory3")
public class MybatisDbWtConfig {

    @Autowired
    @Qualifier("wtDataSource")
    private DataSource wtDataSource;


    @Bean
    public SqlSessionFactory sqlSessionFactory3() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(wtDataSource); // wt数据库
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()  
        .getResources("classpath:mapper/wt/*.xml"));  
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory3()); // 使用上面配置的Factory
        return template;
    }
}