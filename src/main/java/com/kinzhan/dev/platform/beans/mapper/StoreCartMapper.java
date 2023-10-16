package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.StoreCartEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-04-19
*/
public interface StoreCartMapper extends BaseMapper<StoreCartEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_store_cart where id = #{id}")
    void forceDelete(Integer id);


    /**
     * 清空购物车
     */
    @Delete("delete from kz_store_cart where uid = #{userId}")
    void forceDeleteByUserId(Integer userId);
}
