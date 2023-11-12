package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.dashboard.DateRangeDto;
import com.mtstore.server.beans.enums.PayBizEnum;
import com.mtstore.server.beans.entity.OrderEntity;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.beans.entity.WithdrawEntity;
import com.mtstore.server.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 控制面板，统计信息等
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    final OrderService orderService;
    final StoreOrderService storeOrderService;
    final StoreOrderDetailService storeOrderDetailService;
    final ProductService productService;
    final UserService userService;
    final UserBrokerageBillService brokerageBillService;
    final StoreRechargeService rechargeService;
    final WithdrawService withdrawService;

    /**
     * 数据统计-概况-头部四个，当日昨日数据汇总
     * @return
     */
    @GetMapping("/top")
    public Object top() {
        Map<String, Object> resultMap = new HashMap();
        //销售额
        resultMap.put("todayIncomeTotal", orderService.getTodayTotal());
        resultMap.put("yesterdayIncomeTotal", orderService.getYesterdayTotal());
        //用户访问量
        resultMap.put("todayVisitedTotal", userService.visitedTotal("today"));
        resultMap.put("yesterdayVisitedTotal", userService.visitedTotal("yesterday"));
        //订单量
        resultMap.put("todayOrderTotal", storeOrderService.orderTotal("today"));
        resultMap.put("yesterdayOrderTotal", storeOrderService.orderTotal("yesterday"));
        //用户注册量
        resultMap.put("todayRegisterTotal", userService.registerTotal("today"));
        resultMap.put("yesterdayRegisterTotal", userService.registerTotal("yesterday"));

        return R.ok("获取成功", resultMap);
    }

    /**
     * 数据统计-概况-待办数据
     * @return
     */
    @GetMapping("/todo")
    public Object todo() {
        Map<String, Object> resultMap = new HashMap();
        List<Map<String, Object>> orderStatusList = storeOrderService.groupByStatus();
        if (CollectionUtils.isNotEmpty(orderStatusList)) {
            Map<String, Object> orderStatusMap = orderStatusList.stream().collect(
                    Collectors.toMap(
                            v -> v.get("status").toString(),
                            v -> v.get("total")
                    ));
            resultMap.put("deliveryTotal", Optional.ofNullable(orderStatusMap.get("2")).orElse(0));
            resultMap.put("verifyTotal", Optional.ofNullable(orderStatusMap.get("5")).orElse(0));
            resultMap.put("afterSaleTotal", Optional.ofNullable(orderStatusMap.get("-4")).orElse(0));
        }
        //售罄
        resultMap.put("sellOutTotal", productService.lambdaQuery().lt(ProductEntity::getStockNum, 10).count());
        //上架
        resultMap.put("onSaleTotal", productService.lambdaQuery().eq(ProductEntity::getEnabled, true).count());
        //库存
        resultMap.put("onStockTotal", productService.count());
        //提现审核
        resultMap.put("withdrawTotal", withdrawService.lambdaQuery().eq(WithdrawEntity::getStatus, 0).count());

        return R.ok("获取成功", resultMap);
    }


    /**
     * 数据统计-用户概况
     * @return
     */
    @PostMapping("/user/index")
    public Object userBase(@Validated @RequestBody DateRangeDto dto) {
        HashMap<String, Object> resultMap = new HashMap();
        //注册量
        resultMap.put("registerTotal", userService.registerTotal(dto.getStartTime(), dto.getEndTime()));
        //活跃量
        resultMap.put("activeTotal", userService.visitedTotal(dto.getStartTime(), dto.getEndTime()));
        //充值量
        resultMap.put("rechargedTotal", orderService.rechargedTotal(dto.getStartTime(), dto.getEndTime()));
        //订单量
        resultMap.put("orderTotal", storeOrderService.orderTotal(dto.getStartTime(), dto.getEndTime()));
        //成交量
        resultMap.put("dealTotal", storeOrderService.dealTotal(dto.getStartTime(), dto.getEndTime()));
        //客单价
        resultMap.put("averageTotal", orderService.averageTotal(dto.getStartTime(), dto.getEndTime()));

        return R.ok("获取成功", resultMap);
    }

    /**
     * 数据统计-用户统计趋势.最近30天
     * @return
     */
    @GetMapping("/user/register")
    public Object userRegisters() {

        return R.ok("获取成功", userService.getLast30daysRegisterTotal());
    }


    /**
     * 用户统计-主数据
     * @return
     */
    @GetMapping("/user/total")
    public Object userTotal() {
        HashMap<String, Object> resultMap = new HashMap();
        resultMap.put("userTotal", userService.count());
        resultMap.put("rechargedTotal", orderService.lambdaQuery()
                .eq(OrderEntity::getBizType, PayBizEnum.RECHARGE)
                .count());
        resultMap.put("rechargedTotalPrice", orderService.rechargedTotal());
        resultMap.put("consumeTotalPrice", storeOrderService.getSumPrice());

        return R.ok("获取成功", resultMap);
    }


    /**
     * 数据统计-订单统计
     * @return
     */
    @GetMapping("/order/index/{date}")
    public Object orderIndex(@PathVariable("date") String date) {

        return R.ok("获取成功", storeOrderService.groupByDate(date));
    }


    /**
     * 商品统计-交易状况
     * @return
     */
    @PostMapping("/product/index")
    public Object productIndex(@Validated @RequestBody DateRangeDto dto) {
        HashMap<String, Object> resultMap = new HashMap();
        //新增商品
        resultMap.put("newTotal", productService
                .lambdaQuery()
                .between(ProductEntity::getCreateTime, dto.getStartTime(), dto.getEndTime())
                .count()
        );

        //浏览量
        resultMap.put("visitedTotal", productService.getTotalVisits());

        resultMap.put("favorTotal", 0);

        resultMap.put("cartTotal", 0);

        resultMap.put("orderTotal", productService.getTotalSales());

        resultMap.put("dealTotal", productService.getTotalSales());

        return R.ok("获取成功", resultMap);
    }

    /**
     * 商品统计-趋势图
     * @return
     */
    @GetMapping("/product/detail")
    public Object productDetail() {

        return R.ok("获取成功", storeOrderDetailService.getLast30daysTotal());
    }


    /**
     * 交易统计-TOP
     * @return
     */
    @GetMapping("/order/total")
    public Object orderTotal() {
        HashMap<String, Object> resultMap = new HashMap();
        //昨日订单量
        LocalDateTime now = LocalDateTime.now();
        resultMap.put("yesterdayOrderTotal", storeOrderService
                .lambdaQuery()
                .between(StoreOrderEntity::getCreateTime,
                        now.minusDays(1).with(LocalTime.MIN),
                        now.minusDays(1).with(LocalTime.MAX)
                ).count());
        //本月订单量
        resultMap.put("currentMonthOrderTotal", storeOrderService
                .lambdaQuery()
                .between(StoreOrderEntity::getCreateTime,
                        now.withDayOfMonth(1).with(LocalTime.MIN),
                        now.withDayOfMonth(now.getMonth().maxLength()).with(LocalTime.MAX)
                ).count());

        resultMap.put("yesterdayPaidTotal", orderService.getYesterdayTotal());
        resultMap.put("currentMonthPaidTotal", orderService.getCurrentMonthTotal());

        return R.ok("获取成功", resultMap);
    }


    /**
     * 交易统计-详情
     * @return
     */
    @PostMapping("/order/detail")
    public Object orderDetail(@Validated @RequestBody DateRangeDto dto) {
        HashMap<String, Object> resultMap = new HashMap();
        //营业额
        resultMap.put("tradeTotal", orderService.orderTotalPrice(dto.getStartTime(), dto.getEndTime()));
        //商品支付
        resultMap.put("orderTotal", orderService.orderProductTotalPrice(dto.getStartTime(), dto.getEndTime()));
        //充值
        resultMap.put("rechargeTotal", orderService.orderRechargedTotalPrice(dto.getStartTime(), dto.getEndTime()));
        //支出，用户提现
        resultMap.put("expendTotal", withdrawService.expendTotalPrice(dto.getStartTime(), dto.getEndTime()));

        resultMap.put("balanceTotal", storeOrderService.balanceTotalPrice(dto.getStartTime(), dto.getEndTime()));

        resultMap.put("brokerageTotal", storeOrderService.brokerageTotalPrice(dto.getStartTime(), dto.getEndTime()));

        resultMap.put("refundTotal", orderService.orderRefundTotalPrice(dto.getStartTime(), dto.getEndTime()));

        return R.ok("获取成功", resultMap);
    }

    /**
     * 交易统计-趋势
     * @return
     */
    @GetMapping("/order/trends")
    public Object orderTrends() {


        return R.ok("获取成功", orderService.getLast30daysTotal());
    }

    /**
     * 财务概况-详情
     * @return
     */
    @GetMapping("/fiance/index")
    public Object fianceDetail() {
        HashMap<String, Object> resultMap = new HashMap();
        //本月订单金额
        resultMap.put("monthOrderTotalPrice", orderService.getCurrentMonthTotal());
        //订单总金额
        resultMap.put("orderTotalPrice", orderService.getTotalPrice());
        //订单总数
        resultMap.put("orderTotal", orderService.getTotal());
        //退款总额
        resultMap.put("refundTotalPrice", orderService.getTotalRefundPrice());
        //退款笔数
        resultMap.put("refundTotal", orderService.getTotalRefund());
        //会员余额
        resultMap.put("balanceTotal", userService.getBalanceTotal());
        //会员积分
        resultMap.put("creditTotal", userService.getCreditTotal());
        //会员可提现佣金
        resultMap.put("userBrokerageTotal", userService.getBrokerageTotal());
        //会员已提现佣金，与已提现佣金一致   brokerageBillService
        resultMap.put("withdrawTotal", BigDecimal.valueOf(1111));
        //本月分销佣金
        resultMap.put("monthBrokerageTotal", storeOrderService.brokerageTotalPrice(getMonthDateRange().get(0), getMonthDateRange().get(1)));
        //总佣金
        resultMap.put("brokerageTotal", storeOrderService.brokerageTotalPrice(getDateRange().get(0), getDateRange().get(1)));
        //提现中佣金
        resultMap.put("pendingBrokerageTotal", BigDecimal.valueOf(43030));
        //已提现，与会员已提现一致

        return R.ok("获取成功", resultMap);
    }


    private List<LocalDateTime> getMonthDateRange() {
        List<LocalDateTime> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentMonthStart = now.withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime currentMonthEnd = now.withDayOfMonth(now.getMonth().maxLength()).with(LocalTime.MAX);
        result.add(currentMonthStart);
        result.add(currentMonthEnd);

        return result;
    }

    private List<LocalDateTime> getDateRange() {
        List<LocalDateTime> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.parse("2023-11-19T15:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        result.add(start);
        result.add(now);

        return result;
    }
}
