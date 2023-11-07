package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.mall.product.ProductDetailDto;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.beans.entity.ProductDetailEntity;
import com.mtstore.server.beans.mapper.ProductDetailMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.ProductDetailService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author songsir
* 商品详情 sku
*/
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetailEntity> implements ProductDetailService {

    private ProductDetailEntity findOne(Integer id) {
        return lambdaQuery()
                .eq(ProductDetailEntity::getId, id)
                .gt(ProductDetailEntity::getStockNum, 0)
                .eq(ProductDetailEntity::getEnabled, true).one();
    }

    @Override
    public void save(List<ProductDetailDto> details, Integer productId) {
        baseMapper.forceDeleteByProductId(productId);
        List<ProductDetailEntity> todoList = details.stream().map(dto -> {
            ProductDetailEntity  entity = BeanUtil.copyProperties(dto, ProductDetailEntity.class);
            entity.setProductId(productId);

            return entity;
        }).collect(Collectors.toList());

        saveBatch(todoList);
    }

    @Override
    public void save(ProductEntity productInfo, Integer productId) {
        baseMapper.forceDeleteByProductId(productId);
        ProductDetailEntity entity = BeanUtil.copyProperties(productInfo, ProductDetailEntity.class, "id");
        entity.setProductId(productId);
        entity.setAttrKey("默认");

        save(entity);
    }

    @Override
    public List<ProductDetailEntity> findAllByProductId(Serializable productId) {

        return lambdaQuery().eq(ProductDetailEntity::getProductId, productId).list();
    }

    @Override
    public Boolean stock(Integer id, Integer cartNum) {
        ProductDetailEntity entity = Optional.ofNullable(findOne(id)).orElseThrow(() -> new RuntimeException("库存不足"));
        Integer resultNum =  entity.getStockNum() - cartNum;
        if (resultNum < 0) {
            throw new RuntimeException("库存不足");
        }
        entity.setStockNum(resultNum);

        return updateById(entity);
    }

    @Override
    public void sales(Integer id, Integer cartNum) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setSales(entity.getSales() + cartNum);

            updateById(entity);
        });
    }
}
