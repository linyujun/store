package com.mtstore.server.controller.mobile;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.StoreOrderDetailEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.beans.entity.UserLevelEntity;
import com.mtstore.server.beans.enums.BillEnum;
import com.mtstore.server.beans.enums.PayBizEnum;
import com.mtstore.server.beans.enums.PayTypeEnum;
import com.mtstore.server.service.*;
import com.mtstore.server.util.OrderUtil;
import com.mtstore.server.beans.dto.order.*;
import com.mtstore.server.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

@Api(tags = "移动端-支付接口")
@RestController
@RequestMapping({"/app/wx/pay"})
@Slf4j
@RequiredArgsConstructor
public class WxOrderController {

    private final WxPayService wxPayService;

    private final OrderService orderService;

    private final StoreOrderService storeOrderService;

    private final StoreOrderDetailService storeOrderDetailService;

    private final UserLevelService userLevelService;

    private final UserBillService userBillService;

    private final SysPropertyService sysPropertyService;

    @ApiOperation(value = "购买商品-余额支付")
    @PostMapping("/balanceOrder")
    @SneakyThrows
    @Transactional
    public Object createBalanceOrder(@RequestBody @Validated OrderCartDto dto){
        StoreOrderEntity orderEntity = storeOrderService.createOrderByCart(dto, PayTypeEnum.BALANCE);
        String orderId = orderEntity.getOrderId();
        userBillService.expand(LoggedUser.get().getUserId(),
            BillEnum.BILL_ACTION_BUY_PRODUCT,
            BillEnum.BILL_CATEGORY_BALANCE,
            orderEntity.getPayPrice(),
            orderEntity.getProductInfo()
        );
        storeOrderService.paid(orderId);
        log.info(LoggedUser.get().getUserId() + " 购买商品-余额支付: {}", dto);
        return R.ok("购买成功", orderId);
    }

    @ApiOperation(value = "购买商品-微信支付")
    @PostMapping("/cartOrder")
    @SneakyThrows
    public Object createCartOrder(@RequestBody @Validated OrderCartDto dto){
        StoreOrderEntity orderEntity = storeOrderService.createOrderByCart(dto, PayTypeEnum.WEIXIN);
        String orderId = orderEntity.getOrderId();
        SortedMap orderInfo = createPrepayOrder(orderId, orderEntity.getPayPrice(), PayBizEnum.STORE, convertToJSONObject(dto));
        log.info(LoggedUser.get().getUserId() + " 购买商品-微信支付: {}", dto);
        return R.ok("成功", orderInfo);
    }


    @ApiOperation(value = "充值")
    @PostMapping("/recharge")
    @SneakyThrows
    public Object createRechargeOrder(@RequestBody @Validated OrderRechargeDto orderDto) throws WxPayException {
        final String orderId = OrderUtil.nextId();
        SortedMap orderInfo = createPrepayOrder(orderId, orderDto.getTotal(), PayBizEnum.RECHARGE, convertToJSONObject(orderDto));
        log.info(LoggedUser.get().getUserId() + " 充值-微信支付: {}", orderInfo);
        return R.ok("成功", orderInfo);
    }


    @ApiOperation(value = "当面付")
    @PostMapping("/direct")
    @SneakyThrows
    public Object createDirectPayOrder(@RequestBody @Validated OrderDirectPayDto orderDto) throws WxPayException {
        final String orderId = OrderUtil.nextId();
        SortedMap orderInfo = createPrepayOrder(orderId, orderDto.getTotal(), PayBizEnum.DIRECT, convertToJSONObject(orderDto));
        log.info(LoggedUser.get().getUserId() + " 购买商品-当面付: {}", orderInfo);
        return R.ok("成功", orderInfo);
    }


    @ApiOperation(value = "开通VIP支付")
    @PostMapping("/vipOrder")
    @SneakyThrows
    public Object createVipOrder(@RequestBody @Validated OrderVipDto dto){
        final String orderId = OrderUtil.nextId();
        UserLevelEntity userLevelEntity = userLevelService.getById(dto.getLevelId());
        SortedMap orderInfo = createPrepayOrder(orderId, userLevelEntity.getPrice(), PayBizEnum.VIP, convertToJSONObject(dto));
        log.info(LoggedUser.get().getUserId() + "开通VIP: {}", dto);
        return R.ok("成功", orderInfo);
    }

    @ApiOperation(value = "重新支付")
    @PostMapping("/rePayOrder")
    @SneakyThrows
    @Transactional
    public Object rePayOrder(@RequestBody RePayOrderDto orderDto) {
        log.info(LoggedUser.get().getUserId() + " 重新支付订单: {}", orderDto);
        StoreOrderEntity orderEntity = storeOrderService.findByOrderId(orderDto.getOrderId());
        if (null == orderEntity) {
            throw new RuntimeException("订单不存在，请确认！");
        }
        final String orderId = OrderUtil.nextId();
        storeOrderDetailService.lambdaUpdate()
                .eq(StoreOrderDetailEntity::getOrderId, orderEntity.getOrderId())
                .set(StoreOrderDetailEntity::getOrderId, orderId)
                .update();
        orderEntity.setOrderId(orderId);
        storeOrderService.saveOrUpdate(orderEntity);
        SortedMap orderInfo = createPrepayOrder(orderId, orderEntity.getPayPrice(), PayBizEnum.STORE, convertToJSONObject(orderDto));

        return R.ok("成功", orderInfo);
    }

    /**
     * 创建微信预订单
     * @param orderId
     * @param total
     * @return
     */
    @SneakyThrows
    private SortedMap createPrepayOrder(String orderId, BigDecimal total, PayBizEnum bizType, JSONObject orderDto) {

        if (null == LoggedUser.get().getOpenId()) {
            throw new RuntimeException("请在微信中发起支付功能");
        }
        log.info(LoggedUser.get().getUserId() + " 创建微信预订单: " + orderId + " -> " + total);

        String notifyUrl = sysPropertyService.getValue("notifyUrl");
        if (StringUtils.isEmpty(notifyUrl)) {
            notifyUrl = "https://www.bitworldonline.cn/wx/notify/order";
        }
        final WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder()
                //调起支付的人的 openId
                .openid(LoggedUser.get().getOpenId())
                //订单编号
                .outTradeNo(orderId)
                //订单金额
                .totalFee(OrderUtil.yuanToFen(total.toString()))
                //商品描述
                .body("订单信息")
                .tradeType("JSAPI")
                //获取本地IP
                .spbillCreateIp("127.0.0.1")
                //回调的 URL 地址
                .notifyUrl(notifyUrl)
                .build();

        WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        SortedMap orderInfo = convertToSortedMap(wxPayMpOrderResult);
        orderInfo.put("package", wxPayMpOrderResult.getPackageValue());
        orderInfo.put("orderId", orderId);
        orderService.saveOrderInfo(orderId, total, bizType, orderDto, wxPayMpOrderResult);

        return orderInfo;
    }


    private JSONObject convertToJSONObject(Object target) {
       return (JSONObject) JSONObject.toJSON(target);
    }

    private SortedMap convertToSortedMap(Object object) {
        String json = JSON.toJSONString(object);
        return JSON.parseObject(
                json, new TypeReference<TreeMap>() {
        });
    }
}
