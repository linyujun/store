package com.mtstore.server.service;


import com.mtstore.server.beans.dto.feedback.FeedbackDto;
import com.mtstore.server.beans.dto.feedback.ReplyDto;
import com.mtstore.server.beans.entity.FeedbackEntity;

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
