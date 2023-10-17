package com.mtstore.server.service;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ww
 * @date 2021/6/5
 **/
@Service
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class SendMsgService {

    final String CAPTCHA_CODE_SUFFIX = "captcha:phone:";

    final Integer EXPIRED_SECOND = 300;

    final RedisService redisService;

    final SysPropertyService sysPropertyService;

    private Map getOssConfig() {
        return sysPropertyService.getFormData("aliyun.msg");
    }


    public String test(String phone) {
        Integer code = generateCaptchaCode(phone);
        return send(phone, code);
    }

    @Async
    public void send(String phone) {
        Integer code = generateCaptchaCode(phone);
        send(phone, code);
    }

    /**
     * 发送验证码
     * @param phone
     * @param code
     */
    @SneakyThrows
    public String send(String phone, Integer code) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, String> config = getOssConfig();
        map.put("code", code);
        DefaultProfile profile = DefaultProfile.getProfile(config.get("regionId"), config.get("accessKeyId"), config.get("accessSecret"));
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", config.get("signName"));
        request.putQueryParameter("TemplateCode", config.get("templateCode"));
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("result {}", response.getData());
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    /**
     * 生成验证码
     * @param phone
     * @return
     */
    public Integer generateCaptchaCode(String phone) {
        Integer code = RandomUtil.randomInt(100000, 999999);
        redisService.set(CAPTCHA_CODE_SUFFIX + phone, code.toString());
        redisService.expire(CAPTCHA_CODE_SUFFIX + phone, EXPIRED_SECOND);

        return code;
    }

    /**
     * 校验验证码
     * @param phone
     * @param code
     * @return
     */
    public Boolean verify(String phone, String code) {
        Boolean result = false;
        if (null != redisService.get(CAPTCHA_CODE_SUFFIX + phone) &&
                redisService.get(CAPTCHA_CODE_SUFFIX + phone).equals(code)) {
            result = true;
        }

        return result;
    }
}
