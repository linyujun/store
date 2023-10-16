package com.kinzhan.dev.platform.beans.dto.login;

import lombok.Data;

/**
 * @author ww
 * @date 2021/7/29
 **/
@Data
public class WxUserInfo {
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String province;
    private String city;
    private String country;
}
