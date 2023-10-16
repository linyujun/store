package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.StoreSeckillDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-05-23
*/
public interface StoreSeckillDetailMapper extends BaseMapper<StoreSeckillDetailEntity> {


    @Delete("delete from kz_store_seckill_detail where seckill_id = #{seckillId}")
    void forceDeleteBySeckillId(Integer seckillId);
}
