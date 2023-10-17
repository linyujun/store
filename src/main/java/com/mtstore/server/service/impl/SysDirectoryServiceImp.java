package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.sys.SysDirectoryEntity;
import com.mtstore.server.beans.mapper.SysDirectoryMapper;
import com.mtstore.server.service.SysDirectoryService;
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
