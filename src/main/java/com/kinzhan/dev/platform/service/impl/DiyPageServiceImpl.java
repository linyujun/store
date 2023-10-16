package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.kinzhan.dev.platform.beans.dto.diy.DiyPageDto;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.DiyPageEntity;
import com.kinzhan.dev.platform.service.DiyPageService;
import com.kinzhan.dev.platform.beans.mapper.DiyPageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Optional;

/**
* @author songsir
* @date 2023-04-17
*/
@Service
@RequiredArgsConstructor
public class DiyPageServiceImpl extends ServiceImpl<DiyPageMapper, DiyPageEntity> implements DiyPageService {

    @Override
    public DiyPageEntity saveOrUpdate(DiyPageDto dto) {
        DiyPageEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElseGet(() -> new DiyPageEntity());
        BeanUtils.copyProperties(dto, entity);
        if (null == dto.getId()) {
            entity.setUuid(RandomUtil.randomString(8));
        }
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void setDefault(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(v -> {
            if (v.getPageType().equals("home") || v.getPageType().equals("userCenter")) {
                lambdaUpdate()
                        .eq(DiyPageEntity::getPageType, v.getPageType())
                        .set(DiyPageEntity::getIsDefault, false)
                        .update();
            }
            v.setIsDefault(true);
            updateById(v);
        });
    }

    @Override
    public void copyOne(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setId(null);
            entity.setTitle(entity.getTitle() + "-复制");
            entity.setIsDefault(false);
            entity.setUuid(RandomUtil.randomString(8));

            saveOrUpdate(entity);
        });
    }
}
