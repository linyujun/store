package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.product.ProductDetailDto;
import com.mtstore.server.beans.entity.ProductDetailEntity;
import com.mtstore.server.beans.entity.ProductEntity;

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
