package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
* @author songsir
* @date 2023-04-11
*/
public interface StoreProductMapper extends BaseMapper<ProductEntity> {

    /**
     * 获取全部访问量
     * @return
     */
    @Select("SELECT sum(visited) as total FROM kz_store_product;")
    BigDecimal getTotalVisited();

    /**
     * 获取全部销量
     * @return
     */
    @Select("SELECT sum(sales) as total FROM kz_store_product;")
    BigDecimal getTotalSales();
}
