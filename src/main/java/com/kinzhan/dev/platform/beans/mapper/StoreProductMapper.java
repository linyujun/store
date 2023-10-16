package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.dto.mall.product.ProductTotalDateVo;
import com.kinzhan.dev.platform.beans.dto.order.OrderStatusVo;
import com.kinzhan.dev.platform.beans.dto.order.OrderTotalDateVo;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
