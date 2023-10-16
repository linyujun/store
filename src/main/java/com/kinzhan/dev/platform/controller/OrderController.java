package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.OrderEntity;
import com.kinzhan.dev.platform.service.OrderService;
import com.kinzhan.dev.platform.service.WxOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author songsir
 * @since 2021-11-22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    final OrderService service;

    final WxOrderService wxOrderService;

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        OrderEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/outer/{orderId}")
    public Object findOrder(@PathVariable("orderId") String orderId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", orderId);
        OrderEntity entity = service.getOne(queryWrapper);

        return R.ok("获取成功", entity);
    }

    /**
     * 订单核销
     * @param orderId
     * @return
     */
    @GetMapping("/confirm/{orderId}")
    public Object confirmOrder(@PathVariable("orderId") String orderId) {
        service.confirmOrder(orderId);

        return R.ok("操作成功");
    }

    /**
     * 订单退款
     * @param orderId
     * @return
     */
    @GetMapping("/refund/{orderId}")
    public Object refundOrder(@PathVariable("orderId") String orderId) {
        wxOrderService.refundOrder(orderId);

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        log.info("pageDto {}", pageDto);
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        return R.ok("获取成功", service.getPageList(pageDto, wrapper));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);
        wrapper.eq("is_delete", 0);

        return R.ok("获取成功", service.list(wrapper));
    }
}
