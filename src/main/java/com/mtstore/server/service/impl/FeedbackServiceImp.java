package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.feedback.FeedbackDto;
import com.mtstore.server.beans.dto.feedback.ReplyDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.FeedbackEntity;
import com.mtstore.server.beans.mapper.FeedbackMapper;
import com.mtstore.server.service.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author songsir
 * 意见反馈
 */
@Service
public class FeedbackServiceImp extends ServiceImpl<FeedbackMapper, FeedbackEntity> implements FeedbackService {

    @Override
    public FeedbackEntity saveOrUpdate(FeedbackDto dto) {
        FeedbackEntity entity = BeanUtil.copyProperties(dto, FeedbackEntity.class);
        BeanUtils.copyProperties(LoggedUser.get(), entity);
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public void doReplay(ReplyDto reply) {
        lambdaUpdate()
                .eq(FeedbackEntity::getId, reply.getId())
                .set(FeedbackEntity::getResult, reply.getResult())
                .set(FeedbackEntity::getStatus, reply.getStatus())
                .set(FeedbackEntity::getStatusDesc, reply.getStatus().equals(0) ? "未处理": "已处理").update();
    }
}
