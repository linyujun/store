package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.UserFollowEntity;

import java.util.List;

/**
 * <p>
 * 用户加入的计划 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-04-12
 */
public interface UserFollowService extends IService<UserFollowEntity> {
    void follow(Integer planId);

    void unFollow(Integer userId, Integer planId);

    UserFollowEntity findByPlanId(Integer planId);

    List<Integer> findMyPlanList();

    Boolean isFollow(Integer planId);

    void saveFinished(Integer planId, Integer userId, Integer videoId);

    Page getPageList(PageDto pageDto, QueryWrapper wrapper);
}
