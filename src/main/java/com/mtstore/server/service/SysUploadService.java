package com.mtstore.server.service;

import com.mtstore.server.beans.dto.system.UploadFileDto;
import com.mtstore.server.beans.dto.system.UploadFileMoveDto;
import com.mtstore.server.beans.entity.sys.SysUploadEntity;

import java.util.List;

/**
 * <p>
 *  系统上传路径
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
public interface SysUploadService extends IKService<SysUploadEntity, UploadFileDto> {


    void forceDelete(Integer id);

    void forceDeleteByIds(List<Integer> ids);

    void moveByIds(UploadFileMoveDto dto);
}
