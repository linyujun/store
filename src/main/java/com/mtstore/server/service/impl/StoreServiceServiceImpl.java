package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreServiceEntity;
import com.mtstore.server.beans.mapper.StoreServiceMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreServiceService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 商品服务
*/
@Service
@RequiredArgsConstructor
public class StoreServiceServiceImpl extends ServiceImpl<StoreServiceMapper, StoreServiceEntity> implements StoreServiceService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
