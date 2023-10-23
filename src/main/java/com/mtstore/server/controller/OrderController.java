package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.OrderEntity;
import com.mtstore.server.service.OrderService;
import com.mtstore.server.service.WxOrderService;
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
        log.info(LoggedUser.get().getUserId() + " 订单核销：" + orderId);
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
        log.info(LoggedUser.get().getUserId() + " 订单退款：" + orderId);
        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);
        log.info(LoggedUser.get().getUserId() + " 删除订单：" + id);
        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
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
