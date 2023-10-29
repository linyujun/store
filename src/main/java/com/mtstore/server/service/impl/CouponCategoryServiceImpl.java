package com.mtstore.server.service.impl;

import com.mtstore.server.beans.dto.mall.coupon.CouponCategoryDto;
import com.mtstore.server.beans.entity.CouponCategoryEntity;
import com.mtstore.server.beans.mapper.StoreCouponCategoryMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.CouponCategoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Optional;

/**
* @author songsir
* 优惠券-分类关系
*/
@Service
@RequiredArgsConstructor
public class CouponCategoryServiceImpl extends ServiceImpl<StoreCouponCategoryMapper, CouponCategoryEntity> implements CouponCategoryService {

    @Override
    public CouponCategoryEntity findByCouponId(Integer couponId) {

        return getOne(lambdaQuery().eq(CouponCategoryEntity::getCouponId, couponId).getWrapper());
    }

    @Override
    public CouponCategoryEntity save(Integer couponId, Integer categoryId, List<Integer> categoryList) {
        forceDeleteByCouponId(couponId);
        CouponCategoryDto dto = new CouponCategoryDto()
                .setCouponId(couponId)
                .setCategoryId(categoryId)
                .setCategoryList(categoryList);

        return saveOrUpdate(dto);
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
