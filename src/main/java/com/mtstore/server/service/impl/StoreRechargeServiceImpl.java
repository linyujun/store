package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreRechargeEntity;
import com.mtstore.server.beans.mapper.StoreRechargeMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreRechargeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 商城充值
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
