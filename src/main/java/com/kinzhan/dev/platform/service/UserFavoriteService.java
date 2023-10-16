package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.UserFavoriteEntity;

/**
 * <p>
 * 用户收藏 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-04-12
 */
public interface UserFavoriteService extends IService<UserFavoriteEntity> {

    void addFavoritePlan(Integer planId);

    void addFavoriteVideo(Integer videoId);

    Boolean hasPlanId(Integer targetId);

    Boolean hasVideoId(Integer targetId);

    Page getFavoriteVideoPageList(PageDto pageDto, QueryWrapper wrapper);

    Page getFavoritePlanPageList(PageDto pageDto, QueryWrapper wrapper);
}
