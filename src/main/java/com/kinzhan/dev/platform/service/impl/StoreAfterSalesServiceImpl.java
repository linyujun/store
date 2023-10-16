package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreAfterSalesAuditDto;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreAfterSalesDto;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreReturnDto;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderAddressInfoDto;
import com.kinzhan.dev.platform.beans.entity.StoreOrderEntity;
import com.kinzhan.dev.platform.beans.entity.SysAddressEntity;
import com.kinzhan.dev.platform.beans.enums.AfterSaleStatusEnum;
import com.kinzhan.dev.platform.beans.enums.AfterSaleTypeEnum;
import com.kinzhan.dev.platform.service.*;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreAfterSalesEntity;
import com.kinzhan.dev.platform.beans.mapper.StoreAfterSalesMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-12
*/
@Service
@RequiredArgsConstructor
public class StoreAfterSalesServiceImpl extends ServiceImpl<StoreAfterSalesMapper, StoreAfterSalesEntity> implements StoreAfterSalesService {

    final StoreOrderService storeOrderService;
    final WxOrderService wxOrderService;
    final SysAddressService sysAddressService;

    @Override
    @Transactional
    public StoreAfterSalesEntity saveOrUpdate(StoreAfterSalesDto dto) {
        //TODO 订单超过某个时间，无法发起售后
        StoreOrderEntity orderEntity = Optional.ofNullable(storeOrderService.findByOrderId(dto.getOrderId()))
                .orElseThrow(() -> new RuntimeException("订单不存在！"));
        StoreAfterSalesEntity entity = Optional.ofNullable(findByOrderId(dto.getOrderId())).orElse(new StoreAfterSalesEntity());
        BeanUtil.copyProperties(dto, entity, "id");
        entity.setPayPrice(orderEntity.getPayPrice());
        entity.setRefundPrice(orderEntity.getPayPrice());
        entity.setStatus(AfterSaleStatusEnum.PENDING);
        entity.setProductInfo(orderEntity.getProductInfo());
        entity.setStatusDesc(AfterSaleStatusEnum.PENDING.getDesc());

        saveOrUpdate(entity);
        storeOrderService.afterSales(dto.getOrderId());
        return entity;
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public StoreAfterSalesEntity findByOrderId(String orderId) {
        return lambdaQuery().eq(StoreAfterSalesEntity::getOrderId, orderId).last("limit 1").one();
    }

    /**
     * 商家处理
     * @param dto
     * @return
     */
    @Override
    public StoreAfterSalesEntity audit(StoreAfterSalesAuditDto dto) {
        StoreAfterSalesEntity entity = Optional.ofNullable(getById(dto.getId()))
                .orElseThrow(() -> new RuntimeException("售后记录不存在~"));
        if (dto.getStatus().equals(AfterSaleStatusEnum.REJECTED)) {
            if (StringUtils.isEmpty(dto.getReply())) {
                throw new RuntimeException("驳回理由必填~");
            }
            entity.setStatusDesc(AfterSaleStatusEnum.REJECTED.getDesc());
        }
        //退款直接退
        if (dto.getStatus().equals(AfterSaleStatusEnum.PASS) &&
                entity.getApplyType().equals(AfterSaleTypeEnum.REFUND)) {
            if (dto.getRefundPrice().compareTo(entity.getPayPrice()) > 0) {
                throw new RuntimeException("退款金额必须小于实际支付金额");
            }
            //TODO 执行退款逻辑
            wxOrderService.refundOrder(entity.getOrderId(), dto.getRefundPrice());
            entity.setStatus(AfterSaleStatusEnum.MERCHANT_REFUND_SUCCESS);
            entity.setStatusDesc(AfterSaleStatusEnum.MERCHANT_REFUND_SUCCESS.getDesc());
        }

        if (dto.getStatus().equals(AfterSaleStatusEnum.MERCHANT_REFUND_SUCCESS) &&
                entity.getApplyType().equals(AfterSaleTypeEnum.REFUND_GOODS)) {
            if (dto.getRefundPrice().compareTo(entity.getPayPrice()) > 0) {
                throw new RuntimeException("退款金额必须小于实际支付金额");
            }
            //TODO 执行退款逻辑
            wxOrderService.refundOrder(entity.getOrderId(), dto.getRefundPrice());
            entity.setStatus(AfterSaleStatusEnum.MERCHANT_REFUND_SUCCESS);
            entity.setStatusDesc(AfterSaleStatusEnum.MERCHANT_REFUND_SUCCESS.getDesc());
        }

        if (dto.getStatus().equals(AfterSaleStatusEnum.PASS) &&
                entity.getApplyType().equals(AfterSaleTypeEnum.REFUND_GOODS)) {
            if (null == dto.getAddressId()) {
                throw new RuntimeException("商家收货地址必填！");
            }

            SysAddressEntity sysAddressEntity = sysAddressService.getById(dto.getAddressId());
            if (null == sysAddressEntity) {
                throw new RuntimeException("地址不存在~");
            }
            OrderAddressInfoDto addressInfoDto = BeanUtil.copyProperties(sysAddressEntity, OrderAddressInfoDto.class);
            entity.setAddressInfo(addressInfoDto);
            entity.setStatus(AfterSaleStatusEnum.WAIT_USER_RETURN);
            entity.setStatusDesc(AfterSaleStatusEnum.WAIT_USER_RETURN.getDesc());
        }

        BeanUtil.copyProperties(dto, entity);
        saveOrUpdate(entity);

        return entity;
    }

    /**
     * 退货方式
     * @param dto
     * @return
     */
    @Override
    public StoreAfterSalesEntity returned(StoreReturnDto dto) {
        StoreAfterSalesEntity entity = Optional.ofNullable(getById(dto.getId()))
                .orElseThrow(() -> new RuntimeException("售后记录不存在~"));
        BeanUtil.copyProperties(dto, entity);
        entity.setStatus(AfterSaleStatusEnum.MERCHANT_WAIT_RECEIPT);
        entity.setStatusDesc(AfterSaleStatusEnum.MERCHANT_WAIT_RECEIPT.getDesc());
        saveOrUpdate(entity);

        return entity;
    }
}
