package com.kinzhan.dev.platform.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.kinzhan.dev.platform.schedule.event.order.OrderPaidEvent;
import com.kinzhan.dev.platform.service.WxOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@Api(tags="支付回调接口")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/callback")
public class CallbackController {

    final WxOrderService wxOrderService;

    final WxPayService wxService;

    private final ApplicationEventPublisher publisher;

    @ApiOperation("微信支付回调接口")
    @PostMapping("/notify/order")
    public String orderNotify(@RequestBody String xmlData) throws WxPayException {
        final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
        log.info("进入微信小程序支付回调开始");
        try {
            if (!notifyResult.getResultCode().equals("SUCCESS")) {
                throw new RuntimeException("微信回调失败");
            }
            if(!wxOrderService.syncOrder(notifyResult.getOutTradeNo())) {
                throw new RuntimeException("本地处理失败");
            }
            publisher.publishEvent(new OrderPaidEvent(this, notifyResult.getOutTradeNo()));
            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            e.printStackTrace();

            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
