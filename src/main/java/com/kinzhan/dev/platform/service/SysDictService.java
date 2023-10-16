package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.system.DictDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysDictEntity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 系统字典表 服务类
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
public interface SysDictService extends IKService<SysDictEntity, DictDto> {

    List<SysDictEntity> findAllChildren(Integer parentId);


    List<SysDictEntity> findAllByParentId(@Param("parentId") Integer parentId);

    List<HashMap> findAllByName(@Param("dictValue") String dictValue);

    /**
     * 字典排序
     * @param currentId
     * @param action
     */
    void setSort(Integer currentId, String action);

    /**
     * 禁用，启用
     * @param id
     */
    void disable(Integer id);
}
