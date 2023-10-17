package com.mtstore.server.config;

import com.mtstore.server.config.plugins.MybatisResultInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MybatisInterceptorConfiguration {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @PostConstruct
    public void addInterceptor() {
        this.sqlSessionFactory.getConfiguration().addInterceptor(new MybatisResultInterceptor());
    }
}
