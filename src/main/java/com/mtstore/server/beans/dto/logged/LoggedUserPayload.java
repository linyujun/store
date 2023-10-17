package com.mtstore.server.beans.dto.logged;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoggedUserPayload {
    Integer userId;
    String nickName;
    String phone;
    String openId;
    String role;
    Integer tenantId = 0;
}
