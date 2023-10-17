/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.util;


import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具 2019-01-06
 * @author Zheng Jie
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static void throwForeignKeyException(Throwable e, String msg){
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new RuntimeException(msg);
        }
        assert false;
        throw new RuntimeException("删除失败：" + t.getMessage());
    }
}
