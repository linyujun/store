package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinzhan.dev.platform.beans.entity.CategoryEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

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
