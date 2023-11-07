package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.comment.ReplyDto;
import com.mtstore.server.beans.dto.mall.comment.UserCommentDto;
import com.mtstore.server.beans.entity.StoreCommentEntity;
import com.mtstore.server.beans.dto.mall.comment.CommentDto;

import java.util.List;

/**
* @author songsir
* 商品评价
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
