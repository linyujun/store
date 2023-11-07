package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.product.CartDto;
import com.mtstore.server.beans.enums.CartScopeEnum;
import com.mtstore.server.service.ProductService;
import com.mtstore.server.beans.entity.StoreCartEntity;
import com.mtstore.server.beans.mapper.StoreCartMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author songsir
* 购物车
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCartServiceImpl extends ServiceImpl<StoreCartMapper, StoreCartEntity> implements StoreCartService {

    final ProductService productService;

    @Override
    @Transactional
    public StoreCartEntity add(CartDto cartDto) {
        if (cartDto.getScope().equals(CartScopeEnum.COMBINATION)
                || cartDto.getScope().equals(CartScopeEnum.JOIN_COMBINATION)) {
            if (null == cartDto.getCombinationId()) {
                throw new RuntimeException("拼团活动id必填");
            }
        }
        if (cartDto.getScope().equals(CartScopeEnum.JOIN_COMBINATION)) {
            if (null == cartDto.getCombinationLogId()) {
                throw new RuntimeException("参团活动id必填");
            }
        }
        if (cartDto.getScope().equals(CartScopeEnum.BARGAIN)
                || cartDto.getScope().equals(CartScopeEnum.HELP_BARGAIN)) {
            if (null == cartDto.getBargainId()) {
                throw new RuntimeException("砍价活动id必填");
            }
        }
        if (cartDto.getScope().equals(CartScopeEnum.HELP_BARGAIN)) {
            if (null == cartDto.getBargainLogId()) {
                throw new RuntimeException("帮砍活动id必填");
            }
        }
        Boolean result = productService.stock(cartDto.getProductId(), cartDto.getProductDetailId(), cartDto.getCartNum());
        //TODO 检查活动库存
        if (!result) throw new RuntimeException("操作失败，库存不足或商品已下线");
        StoreCartEntity cartEntity = findByCart(cartDto);
        if (null != cartEntity) {
            cartEntity.setCartNum(cartDto.getCartNum());
        } else {
            cartEntity = BeanUtil.copyProperties(cartDto, StoreCartEntity.class);
        }

        saveOrUpdate(cartEntity);
        return cartEntity;
    }

    private StoreCartEntity findByCart(CartDto cartDto) {
        log.info("cartDto {}", cartDto);
        return getOne(lambdaQuery()
                .eq(StoreCartEntity::getProductId, cartDto.getProductId())
                .eq(StoreCartEntity::getUid, LoggedUser.get().getUserId())
                .eq(StoreCartEntity::getScope, cartDto.getScope())
                .eq(StoreCartEntity::getProductDetailId, cartDto.getProductDetailId()).getWrapper());
    }

    @Override
    public void forceDelete(Integer id) {

        baseMapper.forceDelete(id);
    }

    @Override
    public void forceDeleteByUserId(Integer userId) {

        baseMapper.forceDeleteByUserId(userId);
    }

    @Override
    public void forceDeleteByIds(List<Integer> ids) {

        ids.forEach(id -> forceDelete(id));
    }
}
