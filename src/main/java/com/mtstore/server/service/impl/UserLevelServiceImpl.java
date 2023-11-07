package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.UserLevelEntity;
import com.mtstore.server.beans.mapper.UserLevelMapper;
import com.mtstore.server.service.UserLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 用户等级
*/
@Service
@RequiredArgsConstructor
public class UserLevelServiceImpl extends ServiceImpl<UserLevelMapper, UserLevelEntity> implements UserLevelService {

    @Override
    public String findLevelNameById(Integer id) {
        return Optional.ofNullable(
                getOne(lambdaQuery().eq(UserLevelEntity::getId, id).getWrapper()))
                .map(UserLevelEntity::getTitle)
                .orElse(null);
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());

            saveOrUpdate(entity);
        });
    }

    @Override
    public void copyOne(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setId(null);
            entity.setTitle(entity.getTitle() + "-复制");

            saveOrUpdate(entity);
        });
    }
}
