package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreBargainLogEntity;
import com.kinzhan.dev.platform.service.StoreBargainLogService;
import com.kinzhan.dev.platform.beans.mapper.StoreBargainLogMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-07
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
