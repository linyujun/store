package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.UserTagsEntity;
import com.mtstore.server.beans.mapper.UserTagsMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.UserTagsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
