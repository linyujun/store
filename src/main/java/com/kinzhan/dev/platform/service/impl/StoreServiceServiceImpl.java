package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreServiceEntity;
import com.kinzhan.dev.platform.service.StoreServiceService;
import com.kinzhan.dev.platform.beans.mapper.StoreServiceMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-13
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
