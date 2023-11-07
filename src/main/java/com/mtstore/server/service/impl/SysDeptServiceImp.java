package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.sys.SysDeptEntity;
import com.mtstore.server.beans.mapper.SysDeptMapper;
import com.mtstore.server.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author songsir
 * 组织架构 服务实现类
 */
@Service
public class SysDeptServiceImp extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {

    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id);
    }
}
