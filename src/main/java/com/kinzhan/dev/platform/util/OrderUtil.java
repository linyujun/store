package com.kinzhan.dev.platform.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    public static String nextId() {
        return sdf.format(new Date()) + "" + Math.round(Math.random() * 10000);
    }

    public static Integer yuanToFen(String yuan) {
        return (new BigDecimal(yuan)).setScale(2, 4).multiply(new BigDecimal(100)).intValue();
    }

    public static void main(String[] args) {
        System.out.println(OrderUtil.nextId());
    }
}
