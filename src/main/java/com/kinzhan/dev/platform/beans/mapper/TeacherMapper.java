package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinzhan.dev.platform.beans.entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2022-04-11
 */
public interface TeacherMapper extends BaseMapper<TeacherEntity> {

    @Select("SELECT nick_name FROM kz_teacher where id = #{id} limit 1")
    @Cacheable(cacheManager = "cacheManager", cacheNames = "teachers", key = "#id")
    String selectNameById(@Param("id") Integer id);

    @Select("SELECT count(id) as total FROM kz_teacher where tenant_id = #{tenantId} limit 1")
    Long countByTenantId(@Param("tenantId") Integer tenantId);

    @Update("update kz_teacher set uuid = concat('O', tenant_id,'-T',uid);")
    void updateUUID();
}
