package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.system.UploadFileDto;
import com.kinzhan.dev.platform.beans.dto.system.UploadFileMoveDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysUploadEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
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
