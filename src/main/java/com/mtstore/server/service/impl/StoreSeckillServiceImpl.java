package com.mtstore.server.service.impl;

import com.mtstore.server.beans.dto.mall.seckill.SeckillDto;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import com.mtstore.server.service.StoreSeckillDetailService;
import com.mtstore.server.beans.entity.StoreSeckillEntity;
import com.mtstore.server.beans.mapper.StoreSeckillMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreSeckillService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-23
*/
@Service
@RequiredArgsConstructor
public class StoreSeckillServiceImpl extends ServiceImpl<StoreSeckillMapper, StoreSeckillEntity> implements StoreSeckillService {

    final StoreSeckillDetailService seckillDetailService;

    @Override
    public StoreSeckillEntity saveOrUpdate(SeckillDto dto) {
        LocalDateTime startTime = dto.getActivityTime().atTime(dto.getHours());
        LocalDateTime endTime = startTime.plusHours(2L);
        StoreSeckillEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new StoreSeckillEntity());
        BeanUtils.copyProperties(dto, entity);
        entity.setStartTime(startTime);
        entity.setEndTime(endTime);
        entity.setStatus(ActivityStatusEnum.PENDING);
        entity.setStatusDesc(ActivityStatusEnum.PENDING.getDesc());
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void check() {
        //开启秒杀
        lambdaUpdate()
                .eq(StoreSeckillEntity::getEnabled, true)
                .eq(StoreSeckillEntity::getStatus, 0)
                .le(StoreSeckillEntity::getStartTime, LocalDateTime.now())
                .set(StoreSeckillEntity::getStatus, 1)
                .set(StoreSeckillEntity::getStatusDesc, "进行中")
                .update();

        //结束秒杀，不可购买
        lambdaUpdate()
                .eq(StoreSeckillEntity::getEnabled, true)
                .eq(StoreSeckillEntity::getStatus, 1)
                .lt(StoreSeckillEntity::getEndTime, LocalDateTime.now())
                .set(StoreSeckillEntity::getStatus, 2)
                .set(StoreSeckillEntity::getStatusDesc, "已结束")
                .update();
    }
}
