package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.dto.system.UploadFileMoveDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysUploadEntity;
import com.kinzhan.dev.platform.beans.mapper.SysUploadMapper;
import com.kinzhan.dev.platform.service.SysUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
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
