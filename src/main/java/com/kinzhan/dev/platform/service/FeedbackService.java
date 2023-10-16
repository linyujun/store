package com.kinzhan.dev.platform.service;


import com.kinzhan.dev.platform.beans.dto.feedback.FeedbackDto;
import com.kinzhan.dev.platform.beans.dto.feedback.ReplyDto;
import com.kinzhan.dev.platform.beans.entity.FeedbackEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songsir
 * @since 2022-12-22
 */
public interface FeedbackService extends IKService<FeedbackEntity, FeedbackDto> {

    void doReplay(ReplyDto reply);
}
