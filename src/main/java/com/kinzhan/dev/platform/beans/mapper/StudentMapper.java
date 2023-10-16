package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.dto.dashboard.MonthTotalVo;
import com.kinzhan.dev.platform.beans.entity.StudentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户绑定的机构 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2023-01-03
 */
public interface StudentMapper extends BaseMapper<StudentEntity> {

    @Select("SELECT nick_name FROM kz_student where id = #{id} limit 1")
//    @Cacheable(cacheManager = "cacheManager", cacheNames = "students", key = "#id")
    String selectNameById(@Param("id") Integer id);

    @Select("SELECT count(id) as total FROM kz_student where tenant_id = #{tenantId} limit 1")
    Long countByTenantId(@Param("tenantId") Integer tenantId);

    @Select("SELECT * from kz_student WHERE id in " +
            "(SELECT sid FROM `kz_grade_user` WHERE is_delete = 0 AND enabled = 1 AND grade_id = #{gradeId}) ${ew.customSqlSegment}")
    Page<StudentEntity> findStudentsByGradeId(Integer gradeId,
                                              Page<StudentEntity> page,
                                              @Param("ew") Wrapper<StudentEntity> wrapper);

    /**
     * 学员月份分组
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(create_time,'%M') months,count(id) count from kz_student " +
            "WHERE DATE_FORMAT(create_time,'%Y') = #{year} group by months; ")
    List<MonthTotalVo> groupByMonth(String year);

    /**
     * 强制删除
     */
    @Delete("delete from kz_student where id = #{id}")
    void forceDelete(Integer id);
}
