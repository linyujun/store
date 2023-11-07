package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.mall.StoreExpressDto;
import com.mtstore.server.beans.entity.StoreExpressEntity;
import com.mtstore.server.beans.mapper.StoreExpressMapper;
import com.mtstore.server.service.StoreExpressService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author songsir
* 运费模板
*/
@Service
@RequiredArgsConstructor
public class StoreExpressServiceImpl extends ServiceImpl<StoreExpressMapper, StoreExpressEntity> implements StoreExpressService {



    @Override
    @Transactional()
    public StoreExpressEntity saveOrUpdate(StoreExpressDto dto) {
        StoreExpressEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new StoreExpressEntity());
        BeanUtils.copyProperties(dto, entity);
        saveOrUpdate(entity);
        if (CollectionUtils.isNotEmpty(dto.getDetails())) {
            List details = dto.getDetails().stream().map(
                    detail -> {
                        StoreExpressEntity child = BeanUtil.copyProperties(detail, StoreExpressEntity.class);
                        child.setParentId(entity.getId());

                        return child;
                    }
            ).collect(Collectors.toList());
            baseMapper.forceDeleteByParentId(entity.getId());
            saveBatch(details);
        }

        return entity;
    }

    /**
     * 获取默认模板
     * @return
     */
    @Override
    public StoreExpressEntity getDefault() {
        StoreExpressEntity entity = lambdaQuery().eq(StoreExpressEntity::getIsDefault, true)
                .eq(StoreExpressEntity::getEnabled, true).one();
        Optional.ofNullable(entity).ifPresent(v -> {
            v.setDetails(findAllByParentId(v.getId()));
        });

        return entity;
    }

    @Override
    public StoreExpressEntity getDetail(Integer id) {
        StoreExpressEntity entity = getById(id);
        Optional.ofNullable(entity).ifPresent(v -> {
            v.setDetails(findAllByParentId(id));
        });

        return entity;
    }

    @Override
    public List<StoreExpressEntity> findAllByParentId(Integer id) {
        return lambdaQuery().eq(StoreExpressEntity::getParentId, id).list();
    }

    @Override
    public void deleteByParentId(Integer id) {

        baseMapper.forceDeleteByParentId(id);
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    /**
     * 设置默认运费模板
     * @param id
     */
    @Override
    public void setDefault(Integer id) {
        lambdaUpdate()
                .set(StoreExpressEntity::getIsDefault, false).update();

        lambdaUpdate().eq(StoreExpressEntity::getId, id)
                .set(StoreExpressEntity::getIsDefault, true).update();
    }
}
