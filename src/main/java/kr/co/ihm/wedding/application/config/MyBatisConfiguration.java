package kr.co.ihm.wedding.application.config; /**
 * (주)오픈잇 | https://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author hdkim
 * @desc Mybatis 세팅
 */
@Configuration
@MapperScan(basePackages = "kr.co.ihm.wedding")
public class MyBatisConfiguration {
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));

        // 마이바티스 프로퍼티 설정
        Properties mybatisProperties = new Properties();
        mybatisProperties.setProperty("lazyLoadingEnabled", "true");
        mybatisProperties.setProperty("aggressiveLazyLoading", "false");
        mybatisProperties.setProperty("lazyLoadTriggerMethods", "");
        mybatisProperties.setProperty("mapUnderscoreToCamelCase", "true"); // CamelCase 자동맵핑
        sessionFactory.setConfigurationProperties(mybatisProperties);

        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
    	 
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sessionTemplate;
    }
}
