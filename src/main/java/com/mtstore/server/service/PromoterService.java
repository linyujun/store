package com.mtstore.server.service;

import com.mtstore.server.beans.dto.user.UserInfoDto;
import com.mtstore.server.beans.dto.user.UserPromoterDto;
import com.mtstore.server.beans.entity.UserEntity;

import java.util.List;

/**
 * 分销员服务类
 */
public interface PromoterService extends IKService<UserEntity, UserInfoDto>{

    /**
     * 获取一级成员
     * @param userId
     * @return
     */
    List<UserEntity> findFirstChildren(Integer userId);


    /**
     * 获取二级成员
     * @param userId
     * @return
     */
    List<UserEntity> findSecondChildren(Integer userId);

    /**
     * 取消分销资格
     * @param userId
     */
    void cancel(Integer userId);

    /**
     * 统计团队成员
     * @param userId
     * @return
     */
    Long countChild(Integer userId);

    /**
     * 获取上级
     * @param userId
     * @return
     */
    List<UserPromoterDto> findParentPromoters(Integer userId);
}
