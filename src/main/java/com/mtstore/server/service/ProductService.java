package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.product.ProductDto;
import com.mtstore.server.beans.dto.mall.product.ActivityProductDto;
import com.mtstore.server.beans.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
* @author songsir
* spu
*/
public interface ProductService extends IKService<ProductEntity, ProductDto>{

    ProductEntity getDetail(Serializable id);

    ProductEntity save(ProductDto dto);

    ProductEntity saveCreditProduct(ActivityProductDto dto);

    void removeActivity(String activity ,Integer id);

    void enabled(Integer id);

    void online(Integer id);

    void offline(Integer id);

    List<ProductEntity> findAllByCategoryId(Integer categoryId);

    Boolean stock(Integer productId, Integer detailId, Integer cartNum);

    void sales(Integer productId, Integer detailId, Integer cartNum);

    BigDecimal getTotalVisits();

    BigDecimal getTotalSales();
}
