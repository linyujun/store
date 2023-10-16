package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.mall.comment.CommentQueryDto;
import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponQueryDto;
import com.kinzhan.dev.platform.beans.entity.CouponEntity;
import com.kinzhan.dev.platform.beans.entity.StoreCommentEntity;
import com.kinzhan.dev.platform.beans.entity.UserEntity;
import com.kinzhan.dev.platform.service.CouponService;
import com.kinzhan.dev.platform.service.StoreCommentService;
import com.kinzhan.dev.platform.service.StoreOrderService;
import com.kinzhan.dev.platform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-评论")
public class NCommentController {
    private final StoreCommentService storeCommentService;

    private final UserService userService;

    private final StoreOrderService storeOrderService;

    @ApiOperation("分页查询评论数据")
    @PostMapping("/comment/getPageList")
    public Object findPage(@RequestBody QueryDto<CommentQueryDto> pageDto) {
        QueryWrapper<StoreCommentEntity> queryWrapper = new QueryWrapper();
        Page<StoreCommentEntity> pageResult = storeCommentService.pageList(pageDto, queryWrapper);
        Optional.of(pageDto.getFilter()).map(CommentQueryDto::getProductId).ifPresent(v -> {
            pageResult.getRecords().forEach(item -> {
                UserEntity userEntity = userService.getById(item.getUid());
                if (null != userEntity) {
                    item.setNickname(userEntity.getNickName());
                    item.setAvatarUrl(userEntity.getAvatarUrl());
                }
            });
        });

        return R.ok("获取成功", pageResult);
    }

}
