package com.mtstore.server.config.plugins;

import com.mtstore.server.config.plugins.annotion.OneToMany;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * TODO 自定义mybatis 拦截器
 */
@Slf4j
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
public class MybatisResultInterceptor implements Interceptor {

    private long time;

    //方法拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object result = invocation.proceed();
        Object[] args = invocation.getArgs();
        if(null != invocation.proceed() && result instanceof List && target instanceof DefaultResultSetHandler) {
            DefaultResultSetHandler resultSetHandler= (DefaultResultSetHandler) invocation.getTarget();
            Field mappedStatement = resultSetHandler.getClass().getDeclaredField("mappedStatement");
            mappedStatement.setAccessible(true);
            MappedStatement o = (MappedStatement) mappedStatement.get(resultSetHandler);
            Configuration configuration = o.getConfiguration();
            // 迭代处理
            List proceed = (List) result;
            if (CollectionUtils.isEmpty(proceed)) {
                return result;
            }
            if (null != IgnoreRelationHelper.get()) {
                log.info("------   ignore relation work -----------------------------------------");
                return result;
            }
            Iterator iterator = proceed.iterator();
            MybatisResultConverter converter = SpringContextHolder.getBean(MybatisResultConverter.class);
            while (iterator.hasNext()){
                Object next = iterator.next();
                if (null != next) {
                    Field[] fields = (Field[]) ArrayUtils.addAll(next.getClass().getDeclaredFields(), next.getClass().getSuperclass().getDeclaredFields());
                    for(Field field: fields){
                        field.setAccessible(true);
                        if(field.isAnnotationPresent(OneToOne.class)){
                            converter.handleOne(next , field);
                        }

                        if(field.isAnnotationPresent(OneToMany.class)){
                            converter.handleMany(next , field);
                        }
                    }
                }
            }
            return proceed;
        }

        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    //获取设置的阈值等参数
    @Override
    public void setProperties(Properties properties) {
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}
