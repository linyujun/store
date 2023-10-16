package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.UserEntity;
import com.kinzhan.dev.platform.service.PromoterService;
import com.kinzhan.dev.platform.service.StoreOrderService;
import com.kinzhan.dev.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分销
 * </p>
 *
 * @author songsir
 * @since 2021-11-23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promoter")
public class PromoterController {

    final UserService userService;

    final PromoterService promoterService;

    final StoreOrderService storeOrderService;

    /**
     * 保存分销员信息
     * @return
     */
    @PostMapping
    public Object save(@RequestBody UserEntity user) {

        return R.ok("获取成功", userService.saveOrUpdate(user));
    }


    /**
     * 获取一级成员
     * @param userId
     * @return
     */
    @GetMapping("/first/{uid}")
    public Object findFirstChildren(@PathVariable("uid") Integer userId) {
        List<UserEntity> resultList = promoterService.findFirstChildren(userId);

        return R.ok("获取成功", resultList);
    }

    /**
     * 获取二级成员
     * @param userId
     * @return
     */
    @GetMapping("/second/{uid}")
    public Object findSecondChildren(@PathVariable("uid") Integer userId) {
        List<UserEntity> resultList = promoterService.findSecondChildren(userId);

        return R.ok("获取成功", resultList);
    }


    /**
     * 取消分销资格
     * @param userId
     * @return
     */
    @GetMapping("/cancel/{uid}")
    public Object cancel(@PathVariable("uid") Integer userId) {
        promoterService.cancel(userId);

        return R.ok("操作成功");
    }


    /**
     * 分销员分页
     * @param pageDto
     * @return
     */
    @PostMapping("/child/getPageList")
    public Object findChildPage(@RequestBody PageDto pageDto) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getIsPromoter, true);
        if (null == pageDto.getExtra() || !pageDto.getExtra().containsKey("uid")) throw new RuntimeException("用户id必填");
        if (null == pageDto.getExtra() || !pageDto.getExtra().containsKey("level")) throw new RuntimeException("分销级别必填");
        String level = pageDto.getExtra().get("level");
        Integer uid = Integer.parseInt(pageDto.getExtra().get("uid"));

        if (level.equals("1")) {
            queryWrapper.lambda().eq(UserEntity::getInviteId, pageDto.getExtra().get("uid"));
        }

        if (level.equals("2")) {
            List<UserEntity> resultList = promoterService.findFirstChildren(uid);
            if (CollectionUtils.isEmpty(resultList)) {
                queryWrapper.lambda().eq(UserEntity::getId, -1);
            }
            queryWrapper.lambda().in(UserEntity::getInviteId, resultList.stream().map(UserEntity::getId).collect(Collectors.toList()));
        }

        Page<UserEntity> page = userService.getPageList(pageDto, queryWrapper);
        page.getRecords().forEach(v -> {
            v.setInviteNum(promoterService.countChild(v.getId()));
        });

        return R.ok("获取成功", page);
    }

    /**
     * 分销员分页
     * @param pageDto
     * @return
     */
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getIsPromoter, true);
        Page<UserEntity> page = userService.getPageList(pageDto, queryWrapper);
        page.getRecords().forEach(v -> {
            v.setInviteNum(promoterService.countChild(v.getId()));
            v.setOrderNum(storeOrderService.getTotalPromoteOrder(v.getId()));
        });

        return R.ok("获取成功", page);
    }
}
