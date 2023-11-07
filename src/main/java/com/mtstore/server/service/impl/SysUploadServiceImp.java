package com.mtstore.server.service.impl;

import com.mtstore.server.beans.dto.system.UploadFileMoveDto;
import com.mtstore.server.beans.entity.sys.SysUploadEntity;
import com.mtstore.server.beans.mapper.SysUploadMapper;
import com.mtstore.server.service.SysUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songsir
 * 系统上传路径
 */
@Service
public class SysUploadServiceImp extends ServiceImpl<SysUploadMapper, SysUploadEntity> implements SysUploadService {


    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id);
    }

    @Override
    public void forceDeleteByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return;
        ids.stream().forEach(id -> forceDelete(id));
    }

    @Override
    public void moveByIds(UploadFileMoveDto dto) {
        lambdaUpdate()
                .in(SysUploadEntity::getId, dto.getFileIds())
                .set(SysUploadEntity::getDirectoryId, dto.getDirectoryId())
                .update();
    }
}
