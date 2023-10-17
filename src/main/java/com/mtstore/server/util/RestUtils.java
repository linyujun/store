package com.mtstore.server.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @description: RestTemplate 发送http请求
 * @author: Xiongxz
 * @create: 2018-09-28 08:54
 **/
public class RestUtils {
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * post请求,包含了路径,返回类型,Header,Parameter
     *
     * @param url:地址
     * @param returnClassName:返回对象类型,如:String.class
     * @param inputHeader
     * @param inputParameter
     * @param jsonBody
     * @return
     */
    public static <T> T post(String url, Class<T> returnClassName, Map<String, Object> inputHeader, Map<String, Object> inputParameter, String jsonBody) {
        //请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        //拼接Header
        if (inputHeader != null) {
            Set<String> keys = inputHeader.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpHeaders.add(key, inputHeader.get(key).toString());
            }
        }
        //设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "application/json");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("jsonstr", jsonBody);
        if (inputParameter == null) {
            return restTemplate.postForObject(url, paramMap, returnClassName);
        }
        return restTemplate.postForObject(url, paramMap, returnClassName, inputParameter);
    }
}

