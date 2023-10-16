package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.entity.sys.SysDirectoryEntity;
import com.kinzhan.dev.platform.beans.mapper.SysDirectoryMapper;
import com.kinzhan.dev.platform.service.SysDirectoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频目录表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
@Service
public class SysDirectoryServiceImp extends ServiceImpl<SysDirectoryMapper, SysDirectoryEntity> implements SysDirectoryService {

    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id);
    }
}
