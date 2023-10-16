package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.comment.CommentQueryDto;
import com.kinzhan.dev.platform.beans.dto.mall.comment.UserCommentDto;
import com.kinzhan.dev.platform.beans.entity.StoreCommentEntity;
import com.kinzhan.dev.platform.beans.entity.UserEntity;
import com.kinzhan.dev.platform.service.StoreCommentService;
import com.kinzhan.dev.platform.service.StoreOrderService;
import com.kinzhan.dev.platform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@Api(tags = "移动端-商城-评论管理")
@RestController
@RequestMapping("/app/store/comment")
public class MStoreCommentController {

    private final StoreCommentService storeCommentService;

    private final StoreOrderService storeOrderService;

    private final UserService userService;

    @PostMapping
    @ApiOperation("用户评价")
    public Object save(@Validated @RequestBody List<UserCommentDto> dtoList){
        storeCommentService.saveList(dtoList);
        dtoList.stream().findFirst()
                .map(UserCommentDto::getId)
                .map(storeCommentService::getById)
                .map(StoreCommentEntity::getOrderId)
                .ifPresent(orderId -> {
                    storeOrderService.comment(orderId);
                    storeOrderService.finish(orderId);
                });

        return R.ok("评价成功");
    }


    @ApiOperation("获取待评论信息")
    @GetMapping("/order/{orderId}")
    public Object getListByOrder(@PathVariable("orderId") String orderId) {
        List<StoreCommentEntity> resultList = storeCommentService.findAllByOrderId(orderId, LoggedUser.get().getUserId());

        return R.ok("获取成功", resultList);
    }


    @ApiOperation("删除一条评论")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeCommentService.removeById(id);

        return R.ok("操作成功");
    }

    @ApiOperation("批量删除评论")
    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeCommentService.removeById(id);
        });

        return R.ok("操作成功");
    }
}
