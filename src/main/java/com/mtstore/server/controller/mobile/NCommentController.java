package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.mall.comment.CommentQueryDto;
import com.mtstore.server.beans.entity.StoreCommentEntity;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.service.StoreCommentService;
import com.mtstore.server.service.StoreOrderService;
import com.mtstore.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-评论")
@RequestMapping("/open/comment")
public class NCommentController {
    private final StoreCommentService storeCommentService;

    private final UserService userService;

    @ApiOperation("分页查询评论数据")
    @PostMapping("/getPageList")
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
