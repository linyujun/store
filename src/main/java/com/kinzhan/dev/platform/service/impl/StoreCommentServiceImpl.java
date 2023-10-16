package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kinzhan.dev.platform.beans.dto.mall.comment.ReplyDto;
import com.kinzhan.dev.platform.beans.dto.mall.comment.UserCommentDto;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.StoreCommentEntity;
import com.kinzhan.dev.platform.service.StoreCommentService;
import com.kinzhan.dev.platform.beans.mapper.StoreCommentMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-05-17
*/
@Service
@RequiredArgsConstructor
public class StoreCommentServiceImpl extends ServiceImpl<StoreCommentMapper, StoreCommentEntity> implements StoreCommentService {

    @Override
    public void reply(ReplyDto dto) {
        Optional.ofNullable(getById(dto.getId())).ifPresent(e -> {
            BeanUtil.copyProperties(dto, e);
            e.setIsReply(true);
            saveOrUpdate(e);
        });
    }

    @Override
    @Transactional()
    public void saveList(List<UserCommentDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) throw new RuntimeException("评论数据为空");
        dtoList.stream().forEach(item -> {
            Optional.ofNullable(getById(item.getId())).ifPresent(entity -> {
                BeanUtil.copyProperties(item, entity);
                entity.setStatus(1);
                entity.setStatusDesc("已评价");

                saveOrUpdate(entity);
            });
        });
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public List<StoreCommentEntity> findAllByOrderId(String orderId, Integer userId) {
        return lambdaQuery()
                .eq(StoreCommentEntity::getOrderId,orderId)
                .eq(StoreCommentEntity::getUid, userId).list();
    }
}
