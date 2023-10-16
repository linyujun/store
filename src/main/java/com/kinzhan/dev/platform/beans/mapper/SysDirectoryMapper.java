package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.sys.SysDirectoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 视频目录表 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
public interface SysDirectoryMapper extends BaseMapper<SysDirectoryEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_sys_directory where id = #{id}")
    void forceDelete(Integer id);
}
