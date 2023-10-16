package com.kinzhan.dev.platform.util.aliyun;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dypnsapi20170525.models.GetMobileResponse;
import com.aliyun.dypnsapi20170525.models.GetMobileResponseBody;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliyunKeyLoginUtil {

    final static String accessKeyId = "LTAI5t7JR7wwTXgZ32gJaen6";
    final static String accessKeySecret = "OvLcLDVgzqN7SMggHMLVcwMjcZhCxk";

    public static com.aliyun.dypnsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dypnsapi.aliyuncs.com";
        return new com.aliyun.dypnsapi20170525.Client(config);
    }

    @SneakyThrows
    public static String getMobile(String accessToken){
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.dypnsapi20170525.Client client = AliyunKeyLoginUtil.createClient(accessKeyId, accessKeySecret);
        com.aliyun.dypnsapi20170525.models.GetMobileRequest getMobileRequest = new com.aliyun.dypnsapi20170525.models.GetMobileRequest()
                .setAccessToken(accessToken);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        // 复制代码运行请自行打印 API 的返回值
        GetMobileResponse resp = client.getMobileWithOptions(getMobileRequest, runtime);
        GetMobileResponseBody responseBody = resp.getBody();
        if (!"OK".equals(responseBody.getCode())) {
            log.info(com.aliyun.teautil.Common.toJSONString(responseBody));
            throw new RuntimeException(responseBody.getMessage());
        }
        log.info(JSONObject.toJSONString(responseBody.getGetMobileResultDTO()));

        return responseBody.getGetMobileResultDTO().getMobile();
    }


    public static void main(String[] args_) throws Exception {
        getMobile("ddd");
    }
}
