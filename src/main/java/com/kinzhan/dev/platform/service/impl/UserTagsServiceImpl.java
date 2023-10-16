package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.UserTagsEntity;
import com.kinzhan.dev.platform.service.UserTagsService;
import com.kinzhan.dev.platform.beans.mapper.UserTagsMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserTagsServiceImpl extends ServiceImpl<UserTagsMapper, UserTagsEntity> implements UserTagsService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
