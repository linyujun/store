package com.mtstore.server.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mtstore.server.schedule.event.order.OrderPaidEvent;
import com.mtstore.server.service.WxOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags="支付回调接口")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/wx/notify")
public class CallbackController {

    final WxOrderService wxOrderService;

    final WxPayService wxService;

    @ApiOperation("微信支付回调接口")
    @PostMapping("/order")
    public String orderNotify(@RequestBody String xmlData) throws WxPayException {
        // 同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
        // 推荐的做法是，当商户系统收到通知进行处理时，先检查对应业务数据的状态，并判断该通知是否已经处理。
        // 如果未处理，则再进行处理；如果已处理，则直接返回结果成功。
        // 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
        // 对后台通知交互时，如果微信收到商户的应答不符合规范或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，
        // 尽可能提高通知的成功率，但微信不保证通知最终能成功。（
        // 通知频率为15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m）
        final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
        log.info("微信支付回调接口：{}", notifyResult);
        try {
            if (!notifyResult.getResultCode().equals("SUCCESS")) {
                throw new RuntimeException("微信回调失败");
            }
            if(!wxOrderService.syncOrder(notifyResult.getOutTradeNo())) {
                throw new RuntimeException("本地处理失败");
            }
            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            e.printStackTrace();

            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
