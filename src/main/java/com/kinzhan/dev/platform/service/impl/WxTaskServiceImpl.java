package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.WxTaskEntity;
import com.kinzhan.dev.platform.service.WxTaskService;
import com.kinzhan.dev.platform.beans.mapper.WxTaskMapper;
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
public class WxTaskServiceImpl extends ServiceImpl<WxTaskMapper, WxTaskEntity> implements WxTaskService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
