/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.mtstore.server.beans.dto.express.ExpressInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 物流查询服务
 * <p>
 * 快递鸟即时查询API http://www.kdniao.com/api-track
 */
@Slf4j
@Component
public class ExpressService{

    //请求url
    private String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    private String appId = "1776448";
    private String appKey = "bbdc65a8-2d86-40ac-96a2-c027da02087d";

    /**
     * Json方式 查询订单物流轨迹
     *
     * @throws Exception
     */
    @SneakyThrows
    public ExpressInfo getExpressInfo(String OrderCode, String ShipperCode, String LogisticCode, String phone){
        //处理顺丰查询轨迹需手机号码后4位
        String requestData;
        if (ShipperCode.equals("SF")) {
            final String lastFourNumber = getLastFourNumber(phone);
            requestData = "{'OrderCode':'" + OrderCode + "','ShipperCode':'" + ShipperCode + "','LogisticCode':'" + LogisticCode + "','CustomerName':'" + lastFourNumber + "'}";
        } else {
            requestData = "{'OrderCode':'" + OrderCode + "','ShipperCode':'" + ShipperCode + "','LogisticCode':'" + LogisticCode + "'}";
        }

        Map<String, Object> params = new HashMap<>();
        params.put("RequestData", URLEncoder.encode(requestData, "UTF-8"));
        params.put("EBusinessID", appId);
        params.put("RequestType", "1002");
        String dataSign = encrypt(requestData, appKey, "UTF-8");
        params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result = HttpUtil.post(ReqURL, params);
        //根据公司业务处理返回的信息......

        return JSONObject.parseObject(result, ExpressInfo.class);
    }

    /**
     * 顺丰快递接口要求手机号后四位
     * @param phone
     * @return
     */
    private String getLastFourNumber(String phone) {
        //顺丰轨迹查询处理
        String lastFourNumber = "";
        if (phone.length() == 11) {
            lastFourNumber = StrUtil.sub(lastFourNumber, lastFourNumber.length(), -4);
        }

        return lastFourNumber;
    }

    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @param charset  编码方式
     * @return DataSign签名
     */
    private String encrypt(String content, String keyValue, String charset) {
        if (keyValue != null) {
            content = content + keyValue;
        }
        byte[] src;
        try {
            src = MD5(content, charset).getBytes(charset);
            return Base64Utils.encodeToString(src);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }


}
