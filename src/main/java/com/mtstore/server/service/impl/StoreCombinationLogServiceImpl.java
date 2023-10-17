package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.beans.mapper.StoreCombinationLogMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreCombinationLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-02
*/
@Service
@RequiredArgsConstructor
public class StoreCombinationLogServiceImpl extends ServiceImpl<StoreCombinationLogMapper, StoreCombinationLogEntity> implements StoreCombinationLogService {

    @Override
    public List<StoreCombinationLogEntity> findAllByGroupId(Integer groupId) {
        return lambdaQuery()
                .eq(StoreCombinationLogEntity::getGroupId, groupId)
                .orderByAsc(StoreCombinationLogEntity::getId)
                .list();
    }

    /**
     * 查找正在拼的记录
     * @param combinationId
     * @return
     */
    @Override
    public List<StoreCombinationLogEntity> findAllByCombinationId(Integer combinationId) {
        return lambdaQuery()
                .eq(StoreCombinationLogEntity::getCombinationId, combinationId)
                .eq(StoreCombinationLogEntity::getStatus, 0)
                .orderByAsc(StoreCombinationLogEntity::getId)
                .last("limit 3")
                .list();
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void check() {

    }
}
