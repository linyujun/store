package com.kinzhan.dev.platform.config.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author ww
 * @date 2021/7/20
 **/
public class MybatisObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
//        setFieldValByName("sort", timestamp() ,metaObject);
        insertCreateUser(metaObject);
        insertTenantId(metaObject);
    }

    public void insertCreateUser(MetaObject metaObject) {
        String[] fields = {"createUser", "uid"};
        for (String fieldName: fields) {
            Object val = getFieldValByName(fieldName, metaObject);
            if (val == null || val.equals(0)) {
                if (null != LoggedUser.get()) {
                    setFieldValByName(fieldName, LoggedUser.get().getUserId(),metaObject);
                } else {
                    setFieldValByName(fieldName, 0, metaObject);
                }
            }
        }
    }

    /**
     * 多租户数据自动写入
     * @param metaObject
     */
    public void insertTenantId(MetaObject metaObject) {
        Object val = getFieldValByName("tenantId", metaObject);
        if (val == null || val.equals(0)) {
            if (null != LoggedUser.get()) {
                setFieldValByName("tenantId", LoggedUser.get().getTenantId(), metaObject);
            } else {
                setFieldValByName("tenantId", 0, metaObject);
            }
        }
    }

    public static int timestamp() {
        long time = System.currentTimeMillis();
        return (int) (time / 1000);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
