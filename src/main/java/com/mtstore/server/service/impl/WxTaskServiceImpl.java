package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.WxTaskEntity;
import com.mtstore.server.beans.mapper.WxTaskMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.WxTaskService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* @date 2023-05-23
*/
@Service
@RequiredArgsConstructor
public class WxTaskServiceImpl extends ServiceImpl<WxTaskMapper, WxTaskEntity> implements WxTaskService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
