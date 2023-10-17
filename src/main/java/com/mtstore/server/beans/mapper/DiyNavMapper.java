package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.DiyNavEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2023-03-31
 */
public interface DiyNavMapper extends BaseMapper<DiyNavEntity> {

    @Delete("delete from kz_diy_nav where 1 = 1")
    void forceDeleteAll();
}
