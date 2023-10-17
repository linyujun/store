package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.StoreExpressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-05-31
*/
public interface StoreExpressMapper extends BaseMapper<StoreExpressEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_store_express where parent_id = #{id}")
    void forceDeleteByParentId(Integer id);
}
