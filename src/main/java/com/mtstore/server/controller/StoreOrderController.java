package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

/**
* @author songsir
* @date 2023-04-20
*/
@RequiredArgsConstructor
@RestController
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

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeOrderService.removeById(id);

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
