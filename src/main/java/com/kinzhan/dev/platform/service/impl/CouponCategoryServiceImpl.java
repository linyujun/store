package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponCategoryDto;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.CouponCategoryEntity;
import com.kinzhan.dev.platform.service.CouponCategoryService;
import com.kinzhan.dev.platform.beans.mapper.StoreCouponCategoryMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-06
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
