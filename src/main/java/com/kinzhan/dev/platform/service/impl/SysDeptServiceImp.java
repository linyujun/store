package com.kinzhan.dev.platform.service.impl;

import com.kinzhan.dev.platform.beans.entity.sys.SysDeptEntity;
import com.kinzhan.dev.platform.beans.mapper.SysDeptMapper;
import com.kinzhan.dev.platform.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织架构 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-03-03
 */
@Service
public class SysDeptServiceImp extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {

    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id);
    }
}
