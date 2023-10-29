package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.CouponProductEntity;
import com.mtstore.server.beans.mapper.StoreCouponProductMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.CouponProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author songsir
* 优惠券-商品关系
*/
@Service
@RequiredArgsConstructor
public class CouponProductServiceImpl extends ServiceImpl<StoreCouponProductMapper, CouponProductEntity> implements CouponProductService {

    @Override
    public List<CouponProductEntity> findListByCouponId(Integer couponId) {

        return lambdaQuery().eq(CouponProductEntity::getCouponId,couponId).list();
    }

    @Override
    public Boolean save(Integer couponId, List<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            throw new RuntimeException("未选择商品");
        }
        List<CouponProductEntity> todoList = productIds.stream()
                .map(i -> new CouponProductEntity().setProductId(i).setCouponId(couponId))
                .collect(Collectors.toList());

        return saveBatch(todoList);
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void forceDeleteByCouponId(Integer couponId) {
        baseMapper.forceDeleteByCouponId(couponId);
    }
}
