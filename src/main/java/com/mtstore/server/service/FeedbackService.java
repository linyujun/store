package com.mtstore.server.service;


import com.mtstore.server.beans.dto.feedback.FeedbackDto;
import com.mtstore.server.beans.dto.feedback.ReplyDto;
import com.mtstore.server.beans.entity.FeedbackEntity;

/**
 * @author songsir
 * 意见反馈
 */
public interface FeedbackService extends IKService<FeedbackEntity, FeedbackDto> {

    void doReplay(ReplyDto reply);
}
