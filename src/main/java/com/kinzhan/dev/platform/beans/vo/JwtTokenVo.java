package com.kinzhan.dev.platform.beans.vo;

import com.kinzhan.dev.platform.beans.dto.user.UserPayloadDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenVo {
    private String accessToken;
    private UserPayloadDto userInfo;
}
