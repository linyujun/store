package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.comment.UserCommentDto;
import com.mtstore.server.beans.entity.StoreCommentEntity;
import com.mtstore.server.service.StoreCommentService;
import com.mtstore.server.service.StoreOrderService;
import com.mtstore.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@Api(tags = "移动端-商城-评论管理")
@RestController
@Slf4j
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

        log.info(LoggedUser.get().getUserId() + " 用户评价： {}" , dtoList);
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
