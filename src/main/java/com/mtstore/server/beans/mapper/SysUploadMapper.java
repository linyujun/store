package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.entity.sys.SysUploadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
public interface SysUploadMapper extends BaseMapper<SysUploadEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_sys_upload where id = #{id}")
    void forceDelete(Integer id);
}
