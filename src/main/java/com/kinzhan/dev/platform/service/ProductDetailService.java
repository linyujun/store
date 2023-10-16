package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.product.ProductDetailDto;
import com.kinzhan.dev.platform.beans.entity.ProductDetailEntity;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;

import java.io.Serializable;
import java.util.List;

/**
* @author songsir
* @date 2023-04-14
*/
public interface ProductDetailService extends IKService<ProductDetailEntity, ProductDetailDto>{

    void save(List<ProductDetailDto> details, Integer productId);

    void save(ProductEntity productInfo, Integer productId);

    List<ProductDetailEntity> findAllByProductId(Serializable productId);

    Boolean stock(Integer id, Integer cartNum);

    void sales(Integer id, Integer cartNum);
}
