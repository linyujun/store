package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.order.OrderDto;
import com.mtstore.server.beans.dto.mall.order.OrderDeliveryDto;
import com.mtstore.server.beans.dto.mall.order.OrderExportDto;
import com.mtstore.server.service.OrderService;
import com.mtstore.server.service.StoreOrderDetailService;
import com.mtstore.server.service.StoreOrderStatusService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
 * 订单状态修改
* @date 2023-04-20
*/
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/store/order")
public class StoreOrderController {

    private final StoreOrderService storeOrderService;

    private final StoreOrderDetailService storeOrderDetailService;

    private final StoreOrderStatusService storeOrderStatusService;

    private final OrderService orderService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreOrderEntity entity = storeOrderService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    @Transactional
    public Object save(@Validated @RequestBody OrderDto dto){
        storeOrderService.saveOrUpdate(dto);
        log.info(LoggedUser.get().getUserId() + " 保存或者更新订单状态 {}" , dto);
        return R.ok("保存成功", true);
    }

    /**
     * 发货
     * @param dto
     * @return
     */
    @PostMapping("/sendOut")
    public Object sendOut(@Validated @RequestBody OrderDeliveryDto dto){
        storeOrderService.send(dto.getOrderId(), dto);
        log.info(LoggedUser.get().getUserId() + " 订单发货 {}" , dto);
        return R.ok("操作成功", true);
    }


    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", storeOrderService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeOrderService.removeById(id);
        });
        log.info(LoggedUser.get().getUserId() + " 批量删除订单");
        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeOrderService.removeById(id);
        log.info(LoggedUser.get().getUserId() + " 删除订单：" + id);
        return R.ok("操作成功");
    }

    /**
     * 导出订单
     * @param response
     * @param pageDto
     * @throws IOException
     */
    @PostMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {
        QueryWrapper<StoreOrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(StoreOrderEntity::getId);

        storeOrderService.download(response, OrderExportDto.class, pageDto, queryWrapper);
    }

    @GetMapping("/status/{orderId}")
    public Object getStatus(@PathVariable("orderId") String orderId) {

        return R.ok("获取成功", storeOrderStatusService.findAllByOrderId(orderId));
    }

    /**
     * 获取订单分组状态
     * @return
     */
    @GetMapping("/group")
    public Object grouping(){

        return R.ok("获取成功", storeOrderService.groupByStatus());
    }
}
