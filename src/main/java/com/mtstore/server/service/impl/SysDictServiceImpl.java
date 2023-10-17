package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.system.DictDto;
import com.mtstore.server.beans.entity.sys.SysDictEntity;
import com.mtstore.server.beans.mapper.SysDictMapper;
import com.mtstore.server.service.SysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {


    @Override
    public SysDictEntity saveOrUpdate(DictDto dto) {
        SysDictEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElseGet(() -> new SysDictEntity());
        BeanUtils.copyProperties(dto, entity);
        saveOrUpdate(entity);
        if (null == dto.getSort() || dto.getSort().equals(0)) {
            lambdaUpdate()
                    .eq(SysDictEntity::getId, entity.getId())
                    .set(SysDictEntity::getSort, entity.getId())
                    .update();
        }

        return entity;
    }

    @Override
    public List<SysDictEntity> findAllChildren(Integer parentId) {

        return this.baseMapper.findAllChildren(parentId);
    }

    @Override
    public List<SysDictEntity> findAllByParentId(Integer parentId) {

        return this.baseMapper.findAllByParentId(parentId);
    }

    @Override
    public List<HashMap> findAllByName(String dictValue) {

        return this.baseMapper.findAllByName(dictValue);
    }

    private SysDictEntity getFront(Integer parentId, Integer sort) {
        return getOne(lambdaQuery().lt(SysDictEntity::getSort, sort)
                .eq(SysDictEntity::getParentId, parentId)
                .orderByDesc(SysDictEntity::getSort)
                .last("LIMIT 1").getWrapper(), false);
    }

    private SysDictEntity getNext(Integer parentId, Integer sort) {
        return getOne(lambdaQuery().gt(SysDictEntity::getSort, sort)
                .eq(SysDictEntity::getParentId, parentId)
                .orderByAsc(SysDictEntity::getSort)
                .last("LIMIT 1").getWrapper(), false);
    }

    private Integer getMin(Integer parentId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("min(sort) as min")
                .eq("parent_id", parentId);

        return Optional
                .ofNullable(getMap(queryWrapper))
                .map(item -> Integer.parseInt(item.get("min").toString()))
                .orElse(null);
    }

    private Integer getMax(Integer parentId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(sort) as min")
                .eq("parent_id", parentId);

        return Optional
                .ofNullable(getMap(queryWrapper))
                .map(item -> Integer.parseInt(item.get("max").toString()))
                .orElse(null);
    }

    @Override
    public void setSort(Integer currentId, String action) {
        SysDictEntity current = baseMapper.selectById(currentId);
        if ("UP".equals(action)) {
            SysDictEntity front = getFront(current.getParentId(), current.getSort());
            if (null != front) {
                //跟上面的进行交换
                lambdaUpdate()
                        .eq(SysDictEntity::getId, current.getId())
                        .set(SysDictEntity::getSort, front.getSort())
                        .update();

                lambdaUpdate()
                        .eq(SysDictEntity::getId, front.getId())
                        .set(SysDictEntity::getSort, current.getSort())
                        .update();
            } else {
                lambdaUpdate()
                        .eq(SysDictEntity::getId, current.getId())
                        .setSql(" sort  = sort - 1")
                        .update();
            }
        }
        if ("DOWN".equals(action)) {
            SysDictEntity next = getNext(current.getParentId(), current.getSort());
            if (null != next) {
                lambdaUpdate()
                        .eq(SysDictEntity::getId, current.getId())
                        .set(SysDictEntity::getSort, next.getSort())
                        .update();

                lambdaUpdate()
                        .eq(SysDictEntity::getId, next.getId())
                        .set(SysDictEntity::getSort, current.getSort())
                        .update();
            } else {
                lambdaUpdate()
                        .eq(SysDictEntity::getId, current.getId())
                        .setSql(" sort  = sort + 1")
                        .update();
            }
        }
        if ("TOP".equals(action)) {
            Optional.ofNullable(getMin(current.getParentId())).ifPresent(source -> {
                lambdaUpdate().eq(SysDictEntity::getId, current.getId()).set(SysDictEntity::getSort, source - 10);
            });

            //设置成最小
        }
        if ("BOTTOM".equals(action)) {
            Optional.ofNullable(getMax(current.getParentId())).ifPresent(source -> {
                lambdaUpdate().eq(SysDictEntity::getId, current.getId()).set(SysDictEntity::getSort, source + 10);
            });
            //设置成最大
        }
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());

            saveOrUpdate(entity);
        });
    }
}
