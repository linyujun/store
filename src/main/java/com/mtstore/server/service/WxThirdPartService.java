package com.mtstore.server.service;

import com.mtstore.server.beans.dto.wx.CompanyMpDto;
import com.mtstore.server.beans.dto.wx.PersonalMpDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.result.WxOpenRegisterPersonalWeappResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WxThirdPartService {
    final WxOpenService wxOpenService;

    /**
     * 创建个人微信小程序
     */
    @SneakyThrows
    public WxOpenRegisterPersonalWeappResult createPersonalMp(PersonalMpDto dto) {
        log.info("Ticket {}", wxOpenService.getWxOpenConfigStorage().getComponentVerifyTicket());
        WxOpenRegisterPersonalWeappResult result = wxOpenService
                .getWxOpenComponentService()
                .fastRegisterPersonalWeapp(dto.getIdname(), dto.getWxuser(), dto.getComponentPhone());
        log.info("result {}", result);

        return result;
    }

    /**
     * 创建企业,微信小程序
     */
    @SneakyThrows
    public WxOpenResult createCompanyMp(CompanyMpDto dto) {
        WxOpenResult result = wxOpenService
                .getWxOpenComponentService()
                .fastRegisterWeapp(dto.getName(),
                        dto.getCode(),
                        dto.getCodeType(),
                        dto.getLegalPersonaWechat(),
                        dto.getLegalPersonaName(),
                        dto.getComponentPhone());
        log.info("result {}", result);

        return result;
    }
}
