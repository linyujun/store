package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.CategoryEntity;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT title FROM kz_category where id = #{id} limit 1")
    String selectNameById(Integer id);
}
