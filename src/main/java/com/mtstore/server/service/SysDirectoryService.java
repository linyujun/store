package com.mtstore.server.service;

import com.mtstore.server.beans.dto.system.DirectoryDto;
import com.mtstore.server.beans.entity.sys.SysDirectoryEntity;

/**
 * <p>
 * 视频目录表 服务类
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
public interface SysDirectoryService extends IKService<SysDirectoryEntity, DirectoryDto> {

    void forceDelete(Integer id);
}
