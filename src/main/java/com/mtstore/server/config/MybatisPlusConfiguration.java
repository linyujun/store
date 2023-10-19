package com.mtstore.server.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.mtstore.server.config.handle.MyTenantLineHandler;
import com.mtstore.server.config.handle.MybatisObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author ww
 * @date 2021/7/26
 **/
@EnableTransactionManagement
@Configuration
@MapperScan({"com.mtstore.server.beans.mapper"})
public class MybatisPlusConfiguration {
    @Bean
    public MybatisObjectHandler mybatisObjectHandler() {
        return new MybatisObjectHandler();
    }

//    @Bean
//    @Profile({"dev", "test"}) // 配置仅在dev和test就环境下生效
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        // 设置sql最大执行时间（毫秒），超过则不执行
//        performanceInterceptor.setMaxTime(500);
//        // 设置对输出的sql格式化
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //注册多租户插件
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantLineHandler()));
        //注册分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //注册乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
//        //这里为不同的表设置对应表名处理器
//        dynamicTableNameInnerInterceptor.setTableNameHandler(new TmpSeatTableNameParser());
//        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }
}
