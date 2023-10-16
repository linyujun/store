package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.UserBalanceEntity;
import com.kinzhan.dev.platform.util.FilterUtil;

/**
 * <p>
 * 用户账户余额表 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-04-06
 */
public interface UserBalanceService extends IService<UserBalanceEntity> {
    default Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        Page page = new Page<UserBalanceEntity>(pageDto.getPage(),pageDto.getSize());
        return page(page, wrapper);
    }
}
