package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.mall.seckill.SeckillDetailDto;
import com.mtstore.server.beans.entity.StoreSeckillDetailEntity;
import com.mtstore.server.beans.mapper.StoreSeckillDetailMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreSeckillDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* 秒杀商品关系
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
