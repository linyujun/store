package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.sys.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 组织架构 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2023-03-03
 */
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    @Cacheable(cacheManager = "cacheManager", cacheNames = "sysDepts", key = "#id")
    @Select("SELECT dept_name FROM kz_sys_dept where id = #{id} limit 1")
    String selectNameById(@Param("id") Integer id);

    /**
     * 强制删除
     */
    @Delete("delete from kz_sys_dept where id = #{id}")
    void forceDelete(Integer id);
}
