package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.sys.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @author songsir
 * @date 2021/7/16
 **/
@Mapper
public interface SysDictMapper extends BaseMapper<SysDictEntity> {
    /**
     * 将父级和子级一起返回
     * @param parentId
     * @return
     */
    @Select("SELECT * FROM kz_sys_dict WHERE parent_id = #{parentId}\n" +
            "UNION\n" +
            "SELECT * FROM kz_sys_dict WHERE parent_id IN (SELECT id FROM kz_sys_dict WHERE parent_id = #{parentId})  order by sort asc")
    List<SysDictEntity> findAllChildren(@Param("parentId") Integer parentId);

    /**
     * 查找下级
     */
    @Select("SELECT * FROM kz_sys_dict WHERE parent_id = #{parentId} order by sort asc")
    List<SysDictEntity> findAllByParentId(@Param("parentId") Integer parentId);

    /**
     * 通过唯一值查找下级
     */
    @Select("SELECT dict_name as text, dict_value as value From  `kz_sys_dict` WHERE " +
            "parent_id = (SELECT id FROM `kz_sys_dict` WHERE dict_value = #{dictValue})")
    List<HashMap> findAllByName(@Param("dictValue") String dictValue);
}
