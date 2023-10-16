package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.product.ProductDto;
import com.kinzhan.dev.platform.beans.dto.mall.product.ActivityProductDto;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/**
* @author songsir
* @date 2023-04-11
*/
public interface ProductService extends IKService<ProductEntity, ProductDto>{

    ProductEntity getDetail(Serializable id);

    ProductEntity save(ProductDto dto);

    ProductEntity saveSeckillProdcut(ActivityProductDto dto);

    ProductEntity saveCreditProduct(ActivityProductDto dto);

    ProductEntity saveCombinationProduct(ActivityProductDto dto);

    ProductEntity saveBargainProduct(ActivityProductDto dto);

    ProductEntity saveRebateProduct(ActivityProductDto dto);

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
