package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.ProductDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-04-14
*/
public interface ProductDetailMapper extends BaseMapper<ProductDetailEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_store_product_detail where product_id = #{prodId}")
    void forceDeleteByProductId(Integer prodId);
}
