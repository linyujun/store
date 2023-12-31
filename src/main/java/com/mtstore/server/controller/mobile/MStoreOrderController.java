package com.mtstore.server.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.order.OrderQueryDto;
import com.mtstore.server.beans.dto.mall.order.OrderSimpleDto;
import com.mtstore.server.beans.dto.order.OrderStatusVo;
import com.mtstore.server.beans.dto.order.OrderCartDto;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.service.StoreOrderDetailService;
import com.mtstore.server.service.StoreOrderService;
import com.mtstore.server.service.StoreOrderStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Api(tags = "移动端-商城-订单管理")
@RestController
@Slf4j
@RequestMapping("/app/store/order")
public class MStoreOrderController {

    private final StoreOrderService storeOrderService;

    @GetMapping("{id}")
    @ApiOperation("查询商城订单")
    public Object getOne(@PathVariable("id") String orderId){
        StoreOrderEntity entity = storeOrderService.findByOrderId(orderId);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/express/{orderId}")
    @ApiOperation("跟踪物流状态")
    public Object getExpressInfo(@PathVariable("orderId") String orderId){
        log.info(LoggedUser.get().getUserId() + " 跟踪物流状态：" + orderId);
        return R.ok("获取成功", storeOrderService.getExpressInfo(orderId));
    }

    @GetMapping("/confirm/{orderId}")
    @ApiOperation("确认收货")
    public Object confirmOrder(@PathVariable("orderId") String orderId){
        storeOrderService.receive(orderId);
        storeOrderService.initComments(orderId);
        log.info(LoggedUser.get().getUserId() + " 确认收货：" + orderId);
        return R.ok("操作成功");
    }

    @GetMapping("/verify/{code}")
    @ApiOperation("通过核销码查询订单信息")
    public Object getVerifyOrder(@PathVariable("code") String code){

        return R.ok("获取成功", storeOrderService.findByVerifyCode(code));
    }

    @PostMapping("/verify")
    @ApiOperation("订单核销")
    public Object verifyOrder(@Validated @RequestBody OrderSimpleDto dto){
        storeOrderService.verify(dto.getOrderId());
        storeOrderService.finish(dto.getOrderId());

        return R.ok("操作成功");
    }

    @PostMapping("/totalPrice")
    @ApiOperation("获取订单实付金额")
    public Object getOrderPrice(@RequestBody OrderCartDto dto){

        return R.ok("获取成功", storeOrderService.getOrderPrice(dto));
    }


    @GetMapping("/group")
    @ApiOperation("获取订单状态分组")
    public Object groupByStatus(){
        List<OrderStatusVo> result = storeOrderService.findOrderStatusGroup(LoggedUser.get().getUserId());

        return R.ok("获取成功", result);
    }

    @ApiOperation("分页查询商城订单")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody QueryDto<OrderQueryDto> queryDto) {
        QueryWrapper<StoreOrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StoreOrderEntity::getUid, LoggedUser.get().getUserId());
        queryWrapper.lambda().orderByDesc(StoreOrderEntity::getId);

        return R.ok("获取成功", storeOrderService.pageList(queryDto, queryWrapper));
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
}
