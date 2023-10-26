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

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    final WxMaService wxService;

    @Override
    public void sendMessage() {
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                new WxMaSubscribeMessage.MsgData("amount1", "0.1"),
                new WxMaSubscribeMessage.MsgData("thing2", "为避免影响用餐，请您确保余额充足")
//                new WxMaSubscribeMessage.MsgData("thing2", "为避免影响到您的孩子正常用餐，请您确保余额充足~")
        );
        try {
            WxMaSubscribeMessage message = WxMaSubscribeMessage.builder()
                    // 要给谁发送
                    .toUser("openId")
                    // 模板id
                    .templateId("0uukmJWShyzp8WRq9098CR5Z7JMXHb8u3-nBqy-Htn8")
                    // 数据
                    .data(msgData)
                    .build();
            log.info("wxService {}", wxService);
            wxService.getSubscribeService().sendSubscribeMsg(message);
        } catch (WxErrorException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void balanceNotice(String openId, BigDecimal totalAmount) {
        log.info("余额不足提醒 {}, {}", openId, totalAmount);
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                new WxMaSubscribeMessage.MsgData("amount1", totalAmount.toString()),
                new WxMaSubscribeMessage.MsgData("thing2", "为避免影响用餐，请您确保余额充足")
        );
        try {
            WxMaSubscribeMessage messageDto = WxMaSubscribeMessage.builder()
                    // 要给谁发送
                    .toUser(openId)
                    // 模板id
                    .templateId("0uukmJWShyzp8WRq9098CR5Z7JMXHb8u3-nBqy-Htn8")
                    // 数据
                    .data(msgData)
                    .page("/pages/recharge/recharge")
                    .build();
            wxService.getSubscribeService().sendSubscribeMsg(messageDto);
        } catch (WxErrorException e) {
            log.error(e.toString());
        }
    }

    @Override
    @Async
    public void rechargeSuccess(String idNo, String openId, BigDecimal total, String orderNo) {
        List<WxMaSubscribeMessage.MsgData> msgData = Arrays.asList(
                //充值金额
                new WxMaSubscribeMessage.MsgData("amount3", total.toString()),
                //充值账户
                new WxMaSubscribeMessage.MsgData("number7", idNo),
                //充值时间
                new WxMaSubscribeMessage.MsgData("date5", DateTime.now().toString()),
                //提示信息
                new WxMaSubscribeMessage.MsgData("thing10", "提示信息"),
                //订单号
                new WxMaSubscribeMessage.MsgData("character_string1", orderNo)
        );
        try {
            WxMaSubscribeMessage messageDto = WxMaSubscribeMessage.builder()
                    // 要给谁发送
                    .toUser(openId)
                    // 模板id
                    .templateId("7eLFvfbP_QxjYcvHyFkCgZ_pvegULJ_KxHoHytQ9PIg")
                    // 数据
                    .data(msgData)
                    .page("/pages/recharge/balance")
                    .build();
            wxService.getSubscribeService().sendSubscribeMsg(messageDto);
        } catch (WxErrorException e) {
            log.error(e.toString());
        }
    }
}
