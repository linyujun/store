package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.express.ExpressInfo;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.order.OrderStatusVo;
import com.mtstore.server.beans.dto.order.OrderCartDto;
import com.mtstore.server.beans.dto.order.OrderTotalDateVo;
import com.mtstore.server.beans.dto.order.OrderTotalHourVo;
import com.mtstore.server.beans.vo.OrderTotalVo;
import com.mtstore.server.service.*;
import com.mtstore.server.util.FilterUtil;
import com.mtstore.server.util.OrderUtil;
import com.mtstore.server.util.converter.ListConverter;
import com.mtstore.server.util.helper.LocalDateConverter;
import com.mtstore.server.beans.dto.mall.order.*;
import com.mtstore.server.beans.entity.*;
import com.mtstore.server.beans.enums.*;
import com.mtstore.server.beans.mapper.StoreOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author songsir
* 管理后台-订单，包含多个商品
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class StoreOrderServiceImpl extends ServiceImpl<StoreOrderMapper, StoreOrderEntity> implements StoreOrderService {

    final StoreOrderDetailService orderDetailService;

    final StoreOrderStatusService orderStatusService;

    final StoreCartService cartService;

    final StoreCommentService commentService;

    final StoreExpressService storeExpressService;

    final CouponService couponService;

    final ExpressService expressService;

    final ProductService productService;

    final UserService userService;

    final UserBillService userBillService;

    final StoreCombinationService combinationService;

    final StoreBargainLogService bargainLogService;

    @Override
    public StoreOrderEntity findByOrderId(String orderId) {
        return getOne(lambdaQuery()
                .eq(StoreOrderEntity::getOrderId, orderId)
                .eq(StoreOrderEntity::getUid, LoggedUser.get().getUserId()).getWrapper());
    }

    @Override
    public StoreOrderEntity findByVerifyCode(String verifyCode) {

        return getOne(lambdaQuery()
                .eq(StoreOrderEntity::getVerifyCode, verifyCode)
                .getWrapper());
    }

    @Override
    public StoreOrderEntity getByOrderId(String orderId) {
        return getOne(lambdaQuery()
                .eq(StoreOrderEntity::getOrderId, orderId).getWrapper());
    }

    /**
     * 生成订单并写库
     * @param dto
     * @return
     */
    @Override
    public StoreOrderEntity createOrderByCart(OrderCartDto dto, PayTypeEnum payType) {

        return prepareOrderByCart(dto, true, payType);
    }

    /**
     * 计算实付金额
     * @param dto
     * @return
     */
    @Override
    public OrderTotalVo getOrderPrice(OrderCartDto dto) {
        return Optional.ofNullable(prepareOrderByCart(dto, false, null))
                .map(e -> new OrderTotalVo()
                        .setTotalPrice(e.getPayPrice())
                        .setPostagePrice(e.getPostagePrice())
                        .setTotalCredit(e.getTotalCredit())
                )
                .orElse(null);
    }

    /**
     * 创建待支付订单
     * @param dto
     * @param isSave
     * @param payType
     * @return
     */
    @Transactional
    public StoreOrderEntity prepareOrderByCart(OrderCartDto dto, Boolean isSave, PayTypeEnum payType) {
        List<StoreCartEntity> cartList = cartService.listByIds(dto.getCartIds());
        if (CollectionUtils.isEmpty(cartList)) {
            throw new RuntimeException("购物车不存在");
        }
        String orderId = OrderUtil.nextId();
        List<StoreOrderDetailEntity> orderDetailList = buildOrderDetailList(orderId, cartList);
        StoreOrderEntity orderEntity = buildOrderDto(orderId, orderDetailList, dto);
        //真正发起订单
        if (isSave) {
            //用户用积分抵扣
            if (orderEntity.getTotalCredit().compareTo(BigDecimal.ZERO) > 0) {
                //userService.findCurrentUser().getCredit();
                BigDecimal userCredit = Optional.ofNullable(userService.findCurrentUser())
                        .map(UserEntity::getCredit)
                        .orElseThrow(() -> new RuntimeException("用户不存在"));
                if (userCredit.compareTo(orderEntity.getTotalCredit()) < 0) {
                    throw new RuntimeException("订单创建失败，用户积分不足");
                }
            }
            StoreCartEntity cartEntity = cartList.stream().findFirst().orElse(null);
            OrderActivityDto activityInfo = BeanUtil.copyProperties(cartEntity, OrderActivityDto.class);
            orderEntity.setPayType(payType);
            orderEntity.setScope(cartEntity.getScope());
            orderEntity.setActivityInfo(activityInfo);
            saveOrUpdate(orderEntity);
            orderDetailService.saveBatch(orderDetailList);
            orderStatusService.create(orderId);
            //TODO 清空购物车
            cartService.forceDeleteByIds(dto.getCartIds());
        }

        return orderEntity;
    }

    /**
     * 订单已支付
     * @param orderId
     */
    @Override
    @Transactional
    public void paid(String orderId) {
        List<StoreOrderDetailEntity> detailList = orderDetailService.findAllByOrderId(orderId);
        StoreOrderEntity orderEntity = getByOrderId(orderId);
        //自提
        if (orderEntity.getShippingType().equals(ShippingTypeEnum.PICKUP)) {
            //生成提货码
            String verifyCode = RandomUtil.randomNumbers(12);
            orderEntity.setVerifyCode(verifyCode);
            orderEntity.setStatus(OrderEnum.PENDING_WRITE_OFF);
            orderEntity.setStatusDesc(OrderEnum.PENDING_WRITE_OFF.getDesc());
            orderEntity.setIsPaid(true);
        } else {
            orderEntity.setStatus(OrderEnum.PENDING_DELIVERY);
            orderEntity.setStatusDesc(OrderEnum.PENDING_DELIVERY.getDesc());
            orderEntity.setIsPaid(true);
        }
        //扣减积分
        if (orderEntity.getTotalCredit().compareTo(BigDecimal.ZERO) > 0) {
            userBillService.expand(orderEntity.getUid(), BillEnum.BILL_ACTION_BUY_PRODUCT,
                    BillEnum.BILL_CATEGORY_CREDIT,
                    orderEntity.getTotalCredit(),
                    orderEntity.getProductInfo());
        }
        //更新销量
        if (CollectionUtils.isNotEmpty(detailList)) {
            detailList.forEach(detail -> {
                productService.sales(detail.getProductId(), detail.getProductDetailId(), detail.getCartNum());
            });
        }
        handleActivity(orderEntity);
        saveOrUpdate(orderEntity);

        log.info("订单已支付，等待发货：{}", orderEntity);

        orderStatusService.paid(orderId);
    }

    /**
     * 处理活动信息
     * @param orderEntity
     */
    private void handleActivity(StoreOrderEntity orderEntity) {
        if (orderEntity.getScope().equals(CartScopeEnum.COMBINATION)) {
            combinationService.create(orderEntity);
        }

        if (orderEntity.getScope().equals(CartScopeEnum.JOIN_COMBINATION)) {
            combinationService.join(orderEntity);
        }
    }

    /**
     * 订单已发货
     * @param orderId
     */
    @Override
    @Transactional
    public void send(String orderId, OrderDeliveryDto dto) {
        StoreOrderEntity orderEntity = getByOrderId(orderId);
        if (null == orderEntity) {
            throw new RuntimeException("订单不存在！");
        }
        orderEntity.setDeliveryInfo(dto);
        orderEntity.setStatus(OrderEnum.DELIVERED);
        orderEntity.setStatusDesc(OrderEnum.DELIVERED.getDesc());
        saveOrUpdate(orderEntity);

        orderStatusService.send(orderId);
    }

    /**
     * 订单已收货
     * @param orderId
     */
    @Override
    @Transactional
    public void receive(String orderId) {
        //确认收货
        Boolean result = lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.CONFIRM_DELIVERY)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.CONFIRM_DELIVERY.getDesc())
                .eq(StoreOrderEntity::getStatus, 3)
                .eq(StoreOrderEntity::getUid, LoggedUser.get().getUserId())
//                .eq(StoreOrderEntity::getIsPaid, true)
                .update();

        if (!result) {
            throw new RuntimeException("操作失败！请检查状态");
        }
        orderStatusService.receive(orderId);
    }

    /**
     * 订单核销
     * @param orderId
     */
    @Override
    @Transactional
    public void verify(String orderId) {
        //确认核销
        Boolean result = lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.WRITE_OFF_COMPLETE)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.WRITE_OFF_COMPLETE.getDesc())
                .eq(StoreOrderEntity::getStatus, OrderEnum.PENDING_WRITE_OFF)
                .set(StoreOrderEntity::getVerifyUid, LoggedUser.get().getUserId())
                .set(StoreOrderEntity::getVerifyTime, LocalDateTime.now())
                .update();
        if (!result) {
            throw new RuntimeException("操作失败！请检查状态");
        }

        orderStatusService.verify(orderId);
    }

    /**
     * 用户点评
     * @param orderId
     */
    @Override
    public void comment(String orderId) {
        orderStatusService.commented(orderId);
    }

    /**
     * 订单已关闭
     * @param orderId
     */
    @Override
    public void close(String orderId) {
        lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.CANCELLED)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.CANCELLED.getDesc())
                .update();

        orderStatusService.close(orderId);
    }

    /**
     * 订单已完成
     * @param orderId
     */
    @Override
    public void finish(String orderId) {
        lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.COMPLETE)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.COMPLETE.getDesc())
                .update();

        orderStatusService.finish(orderId);
    }

    /**
     * 退款归还优惠券，积分，库存等
     * @param orderId
     */
    @Override
    public void refund(String orderId) {
        close(orderId);
    }

    /**
     * 订单售后中
     * @param orderId
     */
    @Override
    public void afterSales(String orderId) {
        lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.AFTER_SALE)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.AFTER_SALE.getDesc())
                .update();

        orderStatusService.afterSales(orderId);
    }

    @Override
    public List<OrderStatusVo> findOrderStatusGroup(Integer userId) {

        return baseMapper.findOrderStatusByUserId(userId);
    }

    @Override
    public ExpressInfo getExpressInfo(String orderId) {
        StoreOrderEntity entity = getByOrderId(orderId);
        if (null == entity) {
            throw new RuntimeException("订单不存在~");
        }
        OrderDeliveryDto deliveryDto = entity.getDeliveryInfo();
        OrderAddressInfoDto addressInfoDto = entity.getAddressInfo();
        if (null == deliveryDto) {
            throw new RuntimeException("订单还未发货或物流信息不存在~");
        }
        ExpressInfo expressInfo = expressService.getExpressInfo(orderId, deliveryDto.getDeliveryId(), deliveryDto.getDeliveryNo(), addressInfoDto.getPhone());
        expressInfo.setShipperName(deliveryDto.getDeliveryCompany());

        return expressInfo;
    }

    /**
     * 初始化评论数据
     * @param orderId
     */
    @Override
    public void initComments(String orderId) {
        List<StoreOrderDetailEntity> todoList = orderDetailService.findAllByOrderId(orderId);
        if (CollectionUtils.isEmpty(todoList)) return;
        List<StoreCommentEntity> commentList = todoList.stream().map(detail -> {
            return BeanUtil.copyProperties(detail, StoreCommentEntity.class,"id", "createTime",
                    "updateTime", "createUser",  "status",
                    "statusDesc", "isDelete", "enabled", "tenantId");
        }).collect(Collectors.toList());
        commentService.saveBatch(commentList);
        //待评价
        lambdaUpdate().eq(StoreOrderEntity::getOrderId, orderId)
                .set(StoreOrderEntity::getStatus, OrderEnum.PENDING_RATE)
                .set(StoreOrderEntity::getStatusDesc, OrderEnum.PENDING_RATE.getDesc())
                .eq(StoreOrderEntity::getStatus, 4)
                .eq(StoreOrderEntity::getUid, LoggedUser.get().getUserId())
                .update();
    }

    @Override
    public List<Map<String, Object>> groupByStatus() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("status, status_desc, count(id) as total");
        queryWrapper.groupBy("status");

        return listMaps(queryWrapper);
    }

    @Override
    public Long orderTotal(String when) {
        LocalDateTime now = LocalDateTime.now();
        if ("today".equalsIgnoreCase(when)) {
            LocalDateTime startOfDay = now.with(LocalTime.MIN);
            LocalDateTime endOfDay = now.with(LocalTime.MAX);
            return lambdaQuery().between(StoreOrderEntity::getCreateTime, startOfDay, endOfDay).count();
        }
        if ("yesterday".equalsIgnoreCase(when)) {
            LocalDateTime yesterday = now.minusDays(1);
            LocalDateTime startOfDay = yesterday.with(LocalTime.MIN);
            LocalDateTime endOfDay = yesterday.with(LocalTime.MAX);
            return lambdaQuery().between(StoreOrderEntity::getCreateTime, startOfDay, endOfDay).count();
        }

        return 0L;
    }



    @Override
    public Integer orderTotal(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.groupBy("uid");

        return Optional.ofNullable(list(queryWrapper)).map(List::size).orElse(0);
    }

    @Override
    public Integer dealTotal(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.gt("status", 1);
        queryWrapper.groupBy("uid");

        return Optional.ofNullable(list(queryWrapper)).map(List::size).orElse(0);
    }

    @Override
    public Map<String, List<Object>> groupByDate(String date) {
        Map<String, List<Object>> result = new LinkedHashMap();
        //当天数据
        if (date.equals("day")) {
            result.putAll(getDefaultHourMap());
            List<OrderTotalHourVo> records = baseMapper.groupByHour();
            if (CollectionUtils.isNotEmpty(records)) {
                Map<String, List<Object>> existMap = records.stream()
                        .collect(Collectors.toMap(
                                e -> e.getHour(),
                                e -> Arrays.asList(e.getTotal(), e.getTotalPrice())
                        ));
                result.putAll(existMap);
            }
        }
        //本周
        if (date.equals("week")) {
            List<OrderTotalDateVo> records = baseMapper.groupByWeek();
            result.putAll(getDefaultWeekMap());
            if (CollectionUtils.isNotEmpty(records)) {
                Map<String, List<Object>> existMap = records.stream()
                        .collect(Collectors.toMap(
                                e -> e.getDate(),
                                e -> Arrays.asList(e.getTotal(), e.getTotalPrice())
                        ));
                result.putAll(existMap);
            }
        }
        //本月
        if (date.equals("month")) {
            List<OrderTotalDateVo> records = baseMapper.groupByMonth();
            result.putAll(getDefaultMonthMap());
            if (CollectionUtils.isNotEmpty(records)) {
                Map<String, List<Object>> existMap = records.stream()
                        .collect(Collectors.toMap(
                                e -> e.getDate(),
                                e -> Arrays.asList(e.getTotal(), e.getTotalPrice())
                        ));
                result.putAll(existMap);
            }
        }
        //本年
        if (date.equals("year")) {
            List<OrderTotalDateVo> records = baseMapper.groupByYear();
            result.putAll(getDefaultYearMap());
            if (CollectionUtils.isNotEmpty(records)) {
                Map<String, List<Object>> existMap = records.stream()
                        .collect(Collectors.toMap(
                                e -> e.getDate(),
                                e -> Arrays.asList(e.getTotal(), e.getTotalPrice())
                        ));
                result.putAll(existMap);
            }
        }

        return result;
    }

    @Override
    public BigDecimal getSumPrice() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(pay_price) as sumPrice");
        queryWrapper.gt("status", OrderEnum.PENDING_PAYMENT);
        BigDecimal consumeTotalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(StoreOrderEntity::getSumPrice)
                .orElse(BigDecimal.ZERO);

        return consumeTotalPrice;
    }

    @Override
    public BigDecimal balanceTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(pay_price) as sumPrice");
        queryWrapper.between("create_time", startTime, endTime);
        queryWrapper.eq("pay_type", PayTypeEnum.BALANCE);
        BigDecimal consumeTotalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(StoreOrderEntity::getSumPrice)
                .orElse(BigDecimal.ZERO);

        return consumeTotalPrice;
    }

    @Override
    public BigDecimal brokerageTotalPrice(LocalDateTime startTime, LocalDateTime endTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(first_fee + second_fee) as sumPrice");
        queryWrapper.between("create_time", startTime, endTime);
        BigDecimal consumeTotalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(StoreOrderEntity::getSumPrice)
                .orElse(BigDecimal.ZERO);

        return consumeTotalPrice;
    }

    /**
     * 生成小时默认数据
     * @return
     */
    private Map<String, List<Object>> getDefaultHourMap() {
        Map<String, List<Object>> hoursMap = new LinkedHashMap<>();
        for (int hour = 0; hour < 24; hour++) {
            hoursMap.put(String.format("%d:00", hour), Arrays.asList(0L, BigDecimal.ZERO));
        }

        return hoursMap;
    }

    /**
     * 生成周默认数据
     * @return
     */
    private Map<String, List<Object>> getDefaultWeekMap() {
        List<String> weekDaysList = new ArrayList<>(Arrays.asList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
                ));
        Map<String, List<Object>> resultMap = new LinkedHashMap<>();
        weekDaysList.stream().forEach(w -> {
            resultMap.put(w, Arrays.asList(0L, BigDecimal.ZERO));
        });

        return resultMap;
    }

    /**
     * 生成月默认数据
     * @return
     */
    private Map<String, List<Object>> getDefaultMonthMap() {
        LocalDate now = LocalDate.now();
        int daysInMonth = now.lengthOfMonth();
        Map<String, List<Object>> result = new LinkedHashMap<>();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = now.withDayOfMonth(day);

            result.put(date.toString(), Arrays.asList(0L, BigDecimal.ZERO));
        }

        return result;
    }

    /**
     * 生成年默认数据
     * @return
     */
    private Map<String, List<Object>> getDefaultYearMap() {
        Map<String, List<Object>> monthMap = new LinkedHashMap<>();
        for (int month = 1; month < 13; month++) {
            monthMap.put(String.format("%d", month), Arrays.asList(0L, BigDecimal.ZERO));
        }

        return monthMap;
    }

    /**
     * 组装订单详情 sku
     * @param orderId
     * @param cartList
     * @return
     */
    private List<StoreOrderDetailEntity> buildOrderDetailList(String orderId, List<StoreCartEntity> cartList) {
        List<StoreOrderDetailEntity> orderDetailList = new ArrayList<>();
        cartList.stream().forEach(cartEntity -> {
            ProductDetailEntity productDetail = cartEntity.getProductDetail();
            ProductEntity product = cartEntity.getProduct();
            if (null == productDetail) {
                throw new RuntimeException("购物车有商品已经下架，请重新购买");
            }
            StoreOrderDetailEntity detail = BeanUtil.copyProperties(productDetail, StoreOrderDetailEntity.class,
                    "id", "createTime", "updateTime", "createUser",  "status",
                    "statusDesc", "isDelete", "enabled", "tenantId");
            detail.setCartNum(cartEntity.getCartNum());
            detail.setOrderId(orderId);
            detail.setTotalCredit(BigDecimal.ZERO);
            detail.setTotalPrice(BigDecimal.ZERO);
            detail.setProductName(product.getProductName());
            detail.setProductDetailId(productDetail.getId());
            //根据不同的活动场景，获取价格
            if (cartEntity.getScope().equals(CartScopeEnum.SECKILL)) {
                detail.setPrice(productDetail.getSeckillPrice());
                detail.setTotalPrice(productDetail.getSeckillPrice().multiply(new BigDecimal(cartEntity.getCartNum())));
            } else if (cartEntity.getScope().equals(CartScopeEnum.BARGAIN)) {
                StoreBargainLogEntity bargainLogEntity = bargainLogService.getById(cartEntity.getBargainLogId());
                if (null == bargainLogEntity) throw new RuntimeException("请从砍价记录发起支付");
                detail.setPrice(bargainLogEntity.getPayPrice());
                detail.setTotalPrice(bargainLogEntity.getPayPrice());
            } else if (cartEntity.getScope().equals(CartScopeEnum.COMBINATION)
                    || cartEntity.getScope().equals(CartScopeEnum.JOIN_COMBINATION)) {
                detail.setPrice(productDetail.getCombinationPrice());
                detail.setTotalPrice(productDetail.getCombinationPrice().multiply(new BigDecimal(cartEntity.getCartNum())));
            } else if (cartEntity.getScope().equals(CartScopeEnum.CREDIT)) {
                detail.setPrice(productDetail.getCreditPrice());
                detail.setCredit(productDetail.getCredit());
                detail.setTotalPrice(productDetail.getCreditPrice().multiply(new BigDecimal(cartEntity.getCartNum())));
                detail.setTotalCredit(productDetail.getCredit().multiply(new BigDecimal(cartEntity.getCartNum())));
            } else {
                detail.setTotalPrice(productDetail.getPrice().multiply(new BigDecimal(cartEntity.getCartNum())));
            }

            orderDetailList.add(detail);
        });

        return orderDetailList;
    }

    /**
     * 组装订单主表数据
     * @param orderId
     * @param orderDetailList
     * @param cartDto
     * @return
     */
    private StoreOrderEntity buildOrderDto(String orderId, List<StoreOrderDetailEntity> orderDetailList, OrderCartDto cartDto) {
        BigDecimal couponPrice = BigDecimal.ZERO;
        //TODO 秒杀，拼团，砍价等活动不可用券
        Integer totalNum = orderDetailList.stream()
                .collect(Collectors.summingInt(StoreOrderDetailEntity::getCartNum));
        BigDecimal totalPrice = orderDetailList.stream()
                .map(StoreOrderDetailEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCredit = orderDetailList.stream()
                .map(StoreOrderDetailEntity::getTotalCredit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //计算邮费
        BigDecimal postagePrice = calcPostagePrice(orderDetailList, cartDto);
        log.error("postagePrice {}", postagePrice);
        if (null != cartDto.getCouponId()) {
            couponPrice = couponService.calcPriceByCouponId(totalPrice, cartDto.getCouponId(), LoggedUser.get().getUserId());
            log.error("couponPrice {}", couponPrice);
        }
        List<String> products = orderDetailList.stream()
                .map(product -> {
                    return String.format("%s : %s × %d", product.getProductName(), product.getAttrKey(), product.getCartNum());
                }).collect(Collectors.toList());
        OrderDto orderDto = BeanUtil.copyProperties(cartDto, OrderDto.class);
        if (CollectionUtils.isNotEmpty(products)) {
            orderDto.setProductInfo(String.join(",\r\n", products));
        }
        orderDto.setOrderId(orderId);
        orderDto.setCouponId(cartDto.getCouponId());
        orderDto.setTotalNum(totalNum);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setTotalCredit(totalCredit);
        orderDto.setCouponPrice(couponPrice);
        orderDto.setPostagePrice(postagePrice);
        orderDto.setPayPrice(totalPrice.subtract(couponPrice).add(postagePrice));
        orderDto.setStatus(OrderEnum.PENDING_PAYMENT);
        orderDto.setStatusDesc(OrderEnum.PENDING_PAYMENT.getDesc());

        return BeanUtil.copyProperties(orderDto, StoreOrderEntity.class);
    }

    /**
     * 计算运费
     * @return
     */
    private BigDecimal calcPostagePrice(List<StoreOrderDetailEntity> orderDetailList, OrderCartDto cartDto) {
        //自提免运费
        BigDecimal todoValue = BigDecimal.ZERO;
        if (cartDto.getShippingType().equals(ShippingTypeEnum.PICKUP)) return BigDecimal.ZERO;
        StoreExpressEntity expressEntity = storeExpressService.getDefault();
        Optional.ofNullable(expressEntity).orElseThrow(() -> new RuntimeException("商家未配置运费模板1~"));
        BigDecimal totalPrice = orderDetailList.stream()
                .map(StoreOrderDetailEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //匹配包邮策略，优先级最高
        if (expressEntity.getIsFree() && totalPrice.compareTo(expressEntity.getFullPrice()) > 0) {
            return BigDecimal.ZERO;
        }
        List<String> address = cartDto.getAddressInfo().getAddress();
        StoreExpressEntity matchedExpressInfo = matchExpress(address, expressEntity);
        log.error("match express template {}", matchedExpressInfo);
        Optional.ofNullable(matchedExpressInfo).orElseThrow(() -> new RuntimeException("商家未配置运费模板2~"));
        if (!matchedExpressInfo.getIsDelivery()) throw new RuntimeException("当前地址未开放购买，请更换");
        //按件计费
        if (matchedExpressInfo.getCalcType().equals("num")) {
            Integer totalNum = orderDetailList.stream()
                    .mapToInt(StoreOrderDetailEntity::getCartNum).sum();
            todoValue = new BigDecimal(totalNum);
        }
        //按重量计费
        if (matchedExpressInfo.getCalcType().equals("weight")) {
            todoValue = orderDetailList.stream()
                    .map(StoreOrderDetailEntity::getWeight)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        //按体积计费
        if (matchedExpressInfo.getCalcType().equals("volume")) {
            todoValue = orderDetailList.stream()
                    .map(StoreOrderDetailEntity::getVolume)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        //不超过基础按照基本运费算
        if (todoValue.compareTo(matchedExpressInfo.getNum()) <= 0) {
            return matchedExpressInfo.getPrice();
        }
        log.error("totalValue {}", todoValue);
        BigDecimal resultValue = todoValue
                .subtract(matchedExpressInfo.getNum())
                .divide(matchedExpressInfo.getExtraNum(), 0, BigDecimal.ROUND_UP)
                .multiply(matchedExpressInfo.getExtraPrice()).add(matchedExpressInfo.getPrice());
        log.error("resultValue {}", resultValue);
        return resultValue;
    }

    /**
     * 匹配物流模板
     * @param userAddressList
     * @param expressEntity
     * @return
     */
    private StoreExpressEntity matchExpress(List<String> userAddressList, StoreExpressEntity expressEntity) {
        if (CollectionUtils.isEmpty(expressEntity.getDetails())) {
            return expressEntity;
        }
        //查找是否命中单独设置
        List<StoreExpressEntity> filterList = expressEntity.getDetails().stream().filter(entity -> {
            List<String> searchList = entity.getAddress();
            searchList.removeIf(addr -> addr.equals("全部"));
            final String searchAddress = String.join("", searchList);
            final String userAddress = String.join("", userAddressList);
            if (userAddress.contains(searchAddress)) {
                return  true;
            }

            return false;
        }).collect(Collectors.toList());

        return filterList.stream().findFirst().orElse(expressEntity);
    }

    @Override
    public void download(HttpServletResponse response, Class clazz, PageDto pageDto, QueryWrapper wrapper) throws IOException {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        List<StoreOrderEntity> todoList = list(wrapper);
        List<OrderExportDto> resultList = new ArrayList();
        for(StoreOrderEntity fromBean: todoList) {
            OrderExportDto exportDto = BeanUtil.copyProperties(fromBean, OrderExportDto.class);
            BeanUtil.copyProperties(fromBean.getAddressInfo(), exportDto);
            BeanUtil.copyProperties(fromBean.getDeliveryInfo(), exportDto);
            resultList.add(exportDto);
        }
        log.info("resultList {}", resultList.size());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = IdUtil.fastSimpleUUID();
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), OrderExportDto.class)
                .sheet("Sheet1")
                .registerConverter(new LocalDateConverter())
                .registerConverter(new ListConverter())
                .doWrite(resultList);
    }
}
