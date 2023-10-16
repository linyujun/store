package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kinzhan.dev.platform.beans.dto.mall.seckill.SeckillDetailDto;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreSeckillDetailEntity;
import com.kinzhan.dev.platform.service.StoreSeckillDetailService;
import com.kinzhan.dev.platform.beans.mapper.StoreSeckillDetailMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-23
*/
@Service
@RequiredArgsConstructor
public class StoreSeckillDetailServiceImpl extends ServiceImpl<StoreSeckillDetailMapper, StoreSeckillDetailEntity> implements StoreSeckillDetailService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void saveAll(List<SeckillDetailDto> details, Integer seckillId) {
        baseMapper.forceDeleteBySeckillId(seckillId);
        if (CollectionUtils.isEmpty(details)) {
            throw new RuntimeException("活动商品必填");
        }
        List<StoreSeckillDetailEntity> todoList = new ArrayList();
        details.forEach(dto -> {
            StoreSeckillDetailEntity entity = BeanUtil.copyProperties(dto, StoreSeckillDetailEntity.class);
            entity.setSeckillId(seckillId);
            todoList.add(entity);
        });
        saveBatch(todoList);
    }
}
