package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.UserLevelEntity;
import com.kinzhan.dev.platform.service.UserLevelService;
import com.kinzhan.dev.platform.beans.mapper.UserLevelMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-04-25
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
