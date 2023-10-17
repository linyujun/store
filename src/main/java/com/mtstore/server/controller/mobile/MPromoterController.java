package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.order.OrderQueryDto;
import com.mtstore.server.beans.dto.mall.product.ProductQueryDto;
import com.mtstore.server.beans.dto.user.UserPromoterQueryDto;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/app/promoter")
@Api(tags = "移动端-分销")
public class MPromoterController {
    final UserService userService;
    final PromoterService promoterService;
    final StoreOrderService storeOrderService;
    final ProductService productService;
    final CategoryService categoryService;
    /**
     * 获取一级成员
     * @return
     */
    @ApiOperation("获取当前分销员信息")
    @GetMapping("/current")
    public Object getPromoterInfo() {
        UserEntity userEntity = userService.findCurrentUser();
        Optional.ofNullable(userEntity).ifPresent(v -> {
            v.setOrderNum(storeOrderService.getTotalPromoteOrder(v.getId()));
            v.setInviteNum(promoterService.countChild(v.getId()));
        });

        return R.ok("获取成功", userEntity);
    }

    /**
     * 分销员分页
     * @param queryDto
     * @return
     */
    @ApiOperation("分销员分页")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody QueryDto<UserPromoterQueryDto> queryDto) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getIsPromoter, true);
        Integer level = queryDto.getFilter().getLevel();
        if (level.equals(1)) {
            queryWrapper.lambda().eq(UserEntity::getInviteId, LoggedUser.get().getUserId());
        }
        if (level.equals(2)) {
            List<UserEntity> resultList = promoterService.findFirstChildren(LoggedUser.get().getUserId());
            if (CollectionUtils.isEmpty(resultList)) {
                queryWrapper.lambda().eq(UserEntity::getId, -1);
            }
            queryWrapper.lambda().in(UserEntity::getInviteId, resultList.stream().map(UserEntity::getId).collect(Collectors.toList()));
        }
        queryDto.getFilter().setLevel(null);
        Page<UserEntity> page = userService.pageList(queryDto, queryWrapper);
        page.getRecords().forEach(v -> {
            v.setInviteNum(promoterService.countChild(v.getId()));
            v.setBrokerageOrderNum(storeOrderService.getTotalPromoteOrder(v.getId()));
            v.setBrokerageOrderPrice(BigDecimal.TEN);
        });

        return R.ok("获取成功", page);
    }

    /**
     * 分销订单分页
     * @param queryDto
     * @return
     */
    @ApiOperation("分销订单分页")
    @PostMapping("/order/getPageList")
    public Object findOrderPage(@RequestBody QueryDto<OrderQueryDto> queryDto) {
        QueryWrapper<StoreOrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().and(i-> i.eq(StoreOrderEntity::getFirstUid, LoggedUser.get().getUserId()));
        queryWrapper.lambda().or(i -> i.eq(StoreOrderEntity::getSecondUid, LoggedUser.get().getUserId()));
        Page<StoreOrderEntity> page = storeOrderService.pageList(queryDto, queryWrapper);
        page.getRecords().forEach(v -> {
            v.setUser(userService.getById(v.getUid()));
        });

        return R.ok("获取成功", page);
    }


    /**
     * 分销商品分页
     * @param queryDto
     * @return
     */
    @ApiOperation("分销商品分页")
    @PostMapping("/product/getPageList")
    public Object findProductPage(@RequestBody QueryDto<ProductQueryDto> queryDto) {
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.lambda().eq(ProductEntity::getEnabled, true);
        wrapper.lambda().eq(ProductEntity::getIsDelete, false);
        queryDto.getFilter().setActivity("\"PROMOTE\"");
        Optional.ofNullable(queryDto.getFilter()).map(ProductQueryDto::getCategoryId).ifPresent(categoryId -> {
            List<Integer> cateIds = categoryService.findAllIdByParentId(categoryId);
            if (cateIds.size() > 1) {
                wrapper.lambda().in(ProductEntity::getCategoryId, cateIds);
                queryDto.getFilter().setCategoryId(null);
            }
        });

        return R.ok("获取成功", productService.pageList(queryDto, wrapper));
    }
}
