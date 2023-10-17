package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.diy.DiyNavDto;
import com.mtstore.server.beans.entity.DiyNavEntity;
import com.mtstore.server.beans.mapper.DiyNavMapper;
import com.mtstore.server.service.DiyNavService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-03-31
 */
@Service
public class DiyNavServiceImp extends ServiceImpl<DiyNavMapper, DiyNavEntity> implements DiyNavService {

    @Override
    @Transactional
    public void save(List<DiyNavDto> todoList) {
        if (CollectionUtils.isEmpty(todoList)) {
            throw new RuntimeException("列表数据必填");
        }
        List<DiyNavEntity> entityList = todoList.stream()
                .map(dto -> BeanUtil.copyProperties(dto, DiyNavEntity.class))
                .collect(Collectors.toList());
        forceDelete();
        saveBatch(entityList);
    }

    @Override
    public void forceDelete() {
        baseMapper.forceDeleteAll();
    }
}
