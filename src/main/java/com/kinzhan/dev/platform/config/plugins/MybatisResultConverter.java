package com.kinzhan.dev.platform.config.plugins;

import com.kinzhan.dev.platform.config.plugins.annotion.LazyField;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToMany;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
import com.kinzhan.dev.platform.util.helper.ReflectHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
@Component
@RequiredArgsConstructor
public class MybatisResultConverter{

    final SqlSessionFactory sqlSessionFactory;

    final ApplicationContext applicationContext;

    @SneakyThrows
    public void handleOne(Object target, Field field) {
        OneToOne annotation = field.getAnnotation(OneToOne.class);
        Object value = ReflectHelper.getValueByFieldName(target, annotation.from());
        if (null == value) return;
        if (null != annotation.clazz() && annotation.clazz() != void.class) {
            Object beanObj = applicationContext.getBean(annotation.clazz());
            Class valClass = value.getClass();
            //FIXED 兼容mybatis_plus
            if (annotation.method().equals("selectById")) {
                valClass = Serializable.class;
            }
            Method method = beanObj.getClass().getMethod(annotation.method(), valClass);
            Object result = method.invoke(beanObj, value);
            if (null != result) {
                ReflectHelper.setValueByFieldName(target, annotation.to(), result);
            }
        }
    }

    @SneakyThrows
    public void handleMany(Object target, Field field) {
        Object result = null;
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            OneToMany annotation = field.getAnnotation(OneToMany.class);
            Object value = ReflectHelper.getValueByFieldName(target, annotation.from());
            if (null == value) return;
            if (null != annotation.clazz() && annotation.clazz() != void.class) {
                Object beanObj = applicationContext.getBean(annotation.clazz());
                Method method = beanObj.getClass().getMethod(annotation.method(), value.getClass());
                result = method.invoke(beanObj, value);
//                log.info("result {}", result);
            } else {
                result = sqlSession.selectList(annotation.mapper(), value);
            }
            if (null != result) {
                ReflectHelper.setValueByFieldName(target, annotation.to(), result);
            }
        }
    }

    public void handleLazyField(Object target, Field field) {
        Object result = null;
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            LazyField annotation = field.getAnnotation(LazyField.class);
            Object value = ReflectHelper.getValueByFieldName(target, annotation.from());
            if (null == value) return;
            if (annotation.multiple().equals("true")) {
                result = sqlSession.selectList(annotation.mapper(), value);
            }
            if (annotation.multiple().equals("false")) {
                result = sqlSession.selectOne(annotation.mapper(), value);
            }
            if (null != result) {
                ReflectHelper.setValueByFieldName(target, annotation.to(), result);
            }
        }
    }

    /**
     * 字段映射
     * @param target
     * @return
     */
    public Object translate(String target) {
        return target;
    }

    public Object translate(Object target) {
        return target;
    }

    public Object translate(Integer target) {
        return target;
    }
}
