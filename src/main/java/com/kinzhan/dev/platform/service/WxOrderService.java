package com.kinzhan.dev.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.kinzhan.dev.platform.beans.dto.order.OrderRechargeDto;
import com.kinzhan.dev.platform.beans.dto.order.OrderVipDto;
import com.kinzhan.dev.platform.beans.entity.OrderEntity;
import com.kinzhan.dev.platform.beans.entity.StoreRechargeEntity;
import com.kinzhan.dev.platform.beans.entity.UserLevelEntity;
import com.kinzhan.dev.platform.beans.enums.BillEnum;
import com.kinzhan.dev.platform.beans.enums.PayBizEnum;
import com.kinzhan.dev.platform.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * 微信支付
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WxOrderService {
    private final WxPayService wxPayService;

    private final OrderService orderService;

    private final StoreOrderService storeOrderService;

    private final OrderQueueService orderQueueService;

    private final StoreRechargeService rechargeService;

    private final UserBillService userBillService;

    private final UserService userService;

    private final UserLevelService userLeaveService;


    /**
     * 同步订单
     * @param outerOrderId
     */
    public Boolean syncOrder(String outerOrderId) {
        try {
            WxPayOrderQueryResult result = queryOrder(outerOrderId);
            if (null == result) {
                return false;
            }
            syncOrder(result);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 同步支付成功订单
     * @param orderResult
     */
    @Transactional
    public void syncOrder(WxPayOrderQueryResult orderResult) {
        String outerOrderId = orderResult.getOutTradeNo();
        if (null != orderResult && orderResult.getTradeState().equalsIgnoreCase("SUCCESS")) {
            OrderEntity orderEntity = orderService.findByOrderId(outerOrderId);
            if (null == orderEntity) {
                throw new RuntimeException("未找到对应订单~");
            }

            if (orderEntity.getBizType().equals(PayBizEnum.STORE)) {
                storeOrderService.paid(outerOrderId);
            }

            if (orderEntity.getBizType().equals(PayBizEnum.RECHARGE)) {
                handleRecharge(orderEntity);
            }

            if (orderEntity.getBizType().equals(PayBizEnum.VIP)) {
                handleVip(orderEntity);
            }

            orderQueueService.finishOrder(outerOrderId);
            orderService.orderPaySuccess(outerOrderId, orderResult.getTransactionId(), orderResult.getTimeEnd());
        }
    }

    /**
     * 回调充值处理订单
     * @param orderEntity
     */
    private void handleRecharge(OrderEntity orderEntity) {
        OrderRechargeDto rechargeDto = JSONObject.parseObject(orderEntity.getExtra(), OrderRechargeDto.class);
        if (null == rechargeDto) {
            throw new RuntimeException("充值信息不存在~请确认");
        }
        BigDecimal totalAmount = rechargeDto.getTotal();
        String description = String.format("充值 %s", totalAmount);
        String append = "";
        if (null != rechargeDto.getRechargeId() && rechargeDto.getRechargeId() > 0) {
            StoreRechargeEntity v = rechargeService.getById(rechargeDto.getRechargeId());
            if (totalAmount.equals(v.getPrice()) && v.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                totalAmount = totalAmount.add(v.getExtraPrice());
                append = String.format(";赠送 %s", v.getExtraPrice());
            }
        }
        userBillService.income(orderEntity.getCreateUser(),
                BillEnum.BILL_ACTION_RECHARGE,
                BillEnum.BILL_CATEGORY_BALANCE,
                totalAmount,
                description.concat(append));
    }


    /**
     * 回调开通VIP处理订单
     * @param orderEntity
     */
    private void handleVip(OrderEntity orderEntity) {
        OrderVipDto orderVipDto = JSONObject.parseObject(orderEntity.getExtra(), OrderVipDto.class);
        if (null == orderVipDto) {
            throw new RuntimeException("开通信息不存在~请确认");
        }
        String description = String.format("开通VIP %s", orderVipDto.getLevelId());
        String append = "";
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (null != orderVipDto.getLevelId()) {
            UserLevelEntity v = userLeaveService.getById(orderVipDto.getLevelId());
            totalAmount = totalAmount.add(v.getPrice());
            append = String.format(";花费 %s", v.getPrice());
            userService.openVip(orderEntity.getCreateUser(), v.getDays());
        }

        userBillService.expand(orderEntity.getCreateUser(),
                BillEnum.BILL_ACTION_VIP,
                BillEnum.BILL_CATEGORY_BALANCE,
                totalAmount,
                description.concat(append));
    }


    /**
     * 关闭支付超时订单
     * @param orderId
     */
    @Transactional
    public void killOrder(String orderId) {
        orderQueueService.finishOrder(orderId);
        orderService.orderPayTimeOut(orderId);
        OrderEntity orderEntity = orderService.findByOrderId(orderId);
        if (null != orderEntity) {
            OrderRechargeDto extraInfo = JSONObject.parseObject(orderEntity.getExtra(), OrderRechargeDto.class);
        }
    }

    /**
     * 查询订单状态
     * @param outerOrderId
     * @return
     */
    public WxPayOrderQueryResult queryOrder(String outerOrderId) {
        try {
            return wxPayService.queryOrder(null, outerOrderId);
        } catch (WxPayException e) {
            e.printStackTrace();

            return null;
        }
    }

    public void refundOrder(String orderId) {
        refundOrder(orderId, BigDecimal.ZERO);
    }
    /**
     * 订单退款
     */
    public void refundOrder(String orderId, BigDecimal refundPrice) {
        final String refundId = OrderUtil.nextId();
        OrderEntity orderEntity = orderService.findByOrderId(orderId);
        if (null == orderEntity) {
            throw new RuntimeException("订单不存在，请确认！");
        }
        Integer refundFee = OrderUtil.yuanToFen(orderEntity.getTotal().toString());
        if (null != refundPrice && refundPrice.compareTo(BigDecimal.ZERO) > 0) {
            refundFee = OrderUtil.yuanToFen(refundPrice.toString());
        }
        try {
            WxPayRefundResult result = this.wxPayService.refund(
                    WxPayRefundRequest.newBuilder()
                            .outRefundNo(refundId)
                            .outTradeNo(orderId)
                            .totalFee(OrderUtil.yuanToFen(orderEntity.getTotal().toString()))
                            .refundFee(refundFee)
                            .build());
            log.info("refund result {}", result);
            if (null == result) {
                throw new RuntimeException("退款失败，请联系系统管理员查看原因！");
            }
            if (result.getResultCode().equalsIgnoreCase("SUCCESS")) {
                orderService.refundOrder(orderId);
            }
        } catch (WxPayException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getErrCodeDes());
        }
    }
}
