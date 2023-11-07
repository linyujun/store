package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreBargainLogEntity;
import com.mtstore.server.beans.mapper.StoreBargainLogMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreBargainLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 砍价记录
*/
@Service
@RequiredArgsConstructor
public class StoreBargainLogServiceImpl extends ServiceImpl<StoreBargainLogMapper, StoreBargainLogEntity> implements StoreBargainLogService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void check() {

    }
}
