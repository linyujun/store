package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.comment.ReplyDto;
import com.kinzhan.dev.platform.beans.dto.mall.comment.UserCommentDto;
import com.kinzhan.dev.platform.beans.entity.StoreCommentEntity;
import com.kinzhan.dev.platform.beans.dto.mall.comment.CommentDto;

import java.util.List;

/**
* @author songsir
* @date 2023-05-17
*/
public interface StoreCommentService extends IKService<StoreCommentEntity, CommentDto>{


    void reply(ReplyDto dto);


    void saveList(List<UserCommentDto> dtoList);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);


    List<StoreCommentEntity> findAllByOrderId(String orderId, Integer userId);
}
