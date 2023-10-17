package com.mtstore.server.config.init;


import com.mtstore.server.service.SysPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.api.WxOpenService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 通知 开放平台 启动票据推送服务
 * @author songsir
 */
@Order(3)
@Component
@Slf4j
@RequiredArgsConstructor
public class FetchTicketInitialize implements Initialize {

    final WxOpenService wxOpenService;

    final SysPropertyService sysPropertyService;

    @Override
    public void execute() throws Exception {
//        wxOpenService.getWxOpenComponentService().startPushTicket();
//        log.info("fetch ticket init");
//        Optional.ofNullable(sysPropertyService.getValue("componentVerifyTicket")).ifPresent(ticket -> {
//            wxOpenService.getWxOpenConfigStorage().setComponentVerifyTicket(ticket);
//        });
    }
}
