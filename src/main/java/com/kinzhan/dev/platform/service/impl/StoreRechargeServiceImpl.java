package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreRechargeEntity;
import com.kinzhan.dev.platform.service.StoreRechargeService;
import com.kinzhan.dev.platform.beans.mapper.StoreRechargeMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-26
*/
@Service
@RequiredArgsConstructor
public class StoreRechargeServiceImpl extends ServiceImpl<StoreRechargeMapper, StoreRechargeEntity> implements StoreRechargeService {



    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
