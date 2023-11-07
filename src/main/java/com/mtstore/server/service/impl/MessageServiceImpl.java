package com.mtstore.server.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DateTime;
import com.mtstore.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 小程序消息
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    final WxMaService wxService;

    @Override
    public void sendMessage() {
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                new WxMaSubscribeMessage.MsgData("order", "您的订单已经送达，请前往门口领取"),
                new WxMaSubscribeMessage.MsgData("active", "今日特价：精品五花肉、特级肥牛")
        );
        try {
            WxMaSubscribeMessage message = WxMaSubscribeMessage.builder()
                    // 要给谁发送,微信的openId
                    .toUser("openId")
                    // 模板id
                    .templateId("0uukmJWShyzp8WRq9098CR5Z7JMXHb8u3-nBqy-Htn8")
                    // 数据
                    .data(msgData)
                    //跳转路径
                    .page("/pages/home")
                    .build();
            log.info("sendMessage {}", message);
            wxService.getSubscribeService().sendSubscribeMsg(message);
        } catch (WxErrorException e) {
            log.error(e.toString());
        }
    }
}
