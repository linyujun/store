package com.mtstore.server.beans.vo;

import com.mtstore.server.beans.dto.user.UserPayloadDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenVo {
    private String accessToken;
    private UserPayloadDto userInfo;
}
