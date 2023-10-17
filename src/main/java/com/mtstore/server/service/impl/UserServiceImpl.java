package com.mtstore.server.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.login.WxUserInfo;
import com.mtstore.server.beans.dto.user.UserInfoDto;
import com.mtstore.server.beans.dto.user.UserTotalDateVo;
import com.mtstore.server.beans.entity.UserCouponEntity;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.mapper.UserMapper;
import com.mtstore.server.beans.vo.CheckInStatusVo;
import com.mtstore.server.schedule.event.user.UserCheckInEvent;
import com.mtstore.server.service.UserCouponService;
import com.mtstore.server.util.FilterUtil;
import com.mtstore.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songsir
 * @since 2021-11-23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    final ApplicationEventPublisher publisher;

    final UserCouponService userCouponService;

    @Override
    public UserEntity saveOrUpdate(UserInfoDto dto) {
        UserEntity userEntity = getById(dto.getId());
        BeanUtils.copyProperties(dto, userEntity);
        saveOrUpdate(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity findCurrentUser() {
        UserEntity userEntity =  getById(LoggedUser.get().getUserId());
        Optional.ofNullable(userEntity).ifPresent(v -> {
            Long couponNum = userCouponService.lambdaQuery()
                    .eq(UserCouponEntity::getUid, v.getId())
                    .eq(UserCouponEntity::getStatus, 0).count();

            userEntity.setCouponNum(couponNum);
        });
        return userEntity;
    }

    @Override
    public UserEntity findByPhone(String phone) {

        return getOne(lambdaQuery().eq(UserEntity::getPhone, phone).getWrapper(), false);
    }

    private UserEntity findUserByOpenId(String openId) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();

        return getOne(queryWrapper.lambda().eq(UserEntity::getOpenId, openId), false);
    }

    private UserEntity findUserByPhone(String phone) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        return getOne(queryWrapper.lambda().eq(UserEntity::getPhone, phone), false);
    }

    @Override
    public UserEntity findUserByOpenIdOrPhone(String openId, String phone) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(openId)) {
            queryWrapper.lambda().eq(UserEntity::getOpenId, openId);
        }
        if (!StringUtils.isEmpty(phone)) {
            queryWrapper.lambda().eq(UserEntity::getPhone, phone);
        }
        return getOne(queryWrapper, false);
    }


    @Override
    public BigDecimal incBalance(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(v -> null != v.getBalance() ? v.getBalance() : BigDecimal.ZERO)
                .orElseThrow(() -> new RuntimeException("用户账户不存在！"));
        BigDecimal totalValue = oldValue.add(amount);
        userEntity.setBalance(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("余额修改失败！");
        }

        return totalValue;
    }

    @Override
    public BigDecimal decBalance(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(v -> null != v.getBalance() ? v.getBalance() : BigDecimal.ZERO)
                .orElseThrow(() -> new RuntimeException("用户账户不存在！"));
        if (oldValue.compareTo(BigDecimal.ONE) <= 0) {
            throw new RuntimeException("余额不足");
        }
        BigDecimal totalValue = oldValue.subtract(amount);
        userEntity.setBalance(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("余额修改失败！");
        }

        return totalValue;
    }

    @Override
    public BigDecimal setBalance(Integer uid, BigDecimal amount) {
        lambdaUpdate().set(UserEntity::getBalance, amount).eq(UserEntity::getId, uid).update();

        return amount;
    }

    @Override
    public BigDecimal setCredit(Integer uid, BigDecimal amount) {
        lambdaUpdate().set(UserEntity::getCredit, amount).eq(UserEntity::getId, uid).update();

        return amount;
    }

    @Override
    public BigDecimal incCredit(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(v -> null != v.getCredit() ? v.getCredit() : BigDecimal.ZERO)
                .orElseThrow(() -> new RuntimeException("用户账户不存在！"));
        log.info("oldValue {}", oldValue);
        log.info("amount {}", amount);
        BigDecimal totalValue = oldValue.add(amount);
        userEntity.setCredit(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("余额修改失败！");
        }

        return totalValue;
    }

    @Override
    public BigDecimal decCredit(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(v -> null != v.getCredit() ? v.getCredit() : BigDecimal.ZERO)
                .orElseThrow(() -> new RuntimeException("用户账户不存在！"));
        if (oldValue.compareTo(BigDecimal.ONE) <= 0) {
            throw new RuntimeException("余额不足");
        }
        BigDecimal totalValue = oldValue.subtract(amount);
        if (totalValue.compareTo(BigDecimal.ONE) <= 0) {
            throw new RuntimeException("余额不足");
        }
        userEntity.setCredit(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("余额修改失败！");
        }

        return totalValue;
    }

    @Override
    public BigDecimal getCredit(Integer uid) {

        return Optional.ofNullable(getById(uid)).map(v -> v.getCredit()).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getBalance(Integer uid) {

        return Optional.ofNullable(getById(uid)).map(v -> v.getBalance()).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal incBrokerage(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(UserEntity::getBrokerage)
                .orElse(BigDecimal.ZERO);
        log.info("oldValue {}", oldValue);
        log.info("amount {}", amount);
        BigDecimal totalValue = oldValue.add(amount);
        userEntity.setBrokerage(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("佣金修改失败！");
        }

        return totalValue;
    }

    @Override
    public BigDecimal decBrokerage(Integer uid, BigDecimal amount) {
        UserEntity userEntity = getById(uid);
        BigDecimal oldValue = Optional.ofNullable(getById(uid))
                .map(UserEntity::getBrokerage)
                .orElse(BigDecimal.ZERO);
        if (oldValue.compareTo(BigDecimal.ONE) <= 0) {
            throw new RuntimeException("佣金不足");
        }
        BigDecimal totalValue = oldValue.subtract(amount);
        if (totalValue.compareTo(BigDecimal.ONE) <= 0) {
            throw new RuntimeException("佣金不足");
        }
        userEntity.setBrokerage(totalValue);
        //乐观锁方式更新
        Boolean result = updateById(userEntity);
        if (!result) {
            throw new RuntimeException("佣金修改失败！");
        }

        return totalValue;
    }


    @Override
    public UserEntity loginByPhone(String phone) {
        UserEntity userEntity = findUserByPhone(phone);
        if (null == userEntity) {
            userEntity = new UserEntity();
            userEntity.setPhone(phone);
            userEntity.setUuid(getUuid());
            userEntity.setIsFirstLogin(true);
            userEntity.setNickName(getNickName());
        }
        saveOrUpdate(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity loginByWx(String phone,
                                String openId,
                                WxUserInfo dto) {
        UserEntity userEntity = Optional.ofNullable(findUserByOpenId(openId)).orElse(new UserEntity());
        if (null == userEntity.getOpenId()) {
            userEntity.setIsFirstLogin(true);
            userEntity.setUuid(getUuid());
            userEntity.setNickName(getNickName());
        }
        log.info("userEntity {}", userEntity);
        userEntity.setOpenId(openId);
        userEntity.setPhone(phone);
        saveOrUpdate(userEntity);

        return userEntity;
    }

    private String getUuid() {
        return  "30" + RandomUtil.randomNumbers(17);
    }

    private String getNickName() {
        return "用户_" + RandomUtil.randomString(7);
    }

    @Override
    public Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        Page page = new Page<UserEntity>(pageDto.getPage(),pageDto.getSize());
        Page<UserEntity> pageResult = page(page, wrapper);
//        Optional.ofNullable(pageResult).ifPresent(this::convert);
        log.info("getPageList");

        return pageResult;
    }


    @Override
    @Transactional()
    public void checkIn() {
        Integer uid = LoggedUser.get().getUserId();
        UserEntity user = getById(uid);
        if (null == user) {

            return;
        }

        //TODO 第一次签到,签到时间=当前时间，签到次数 = 1
        if (null == user.getLastCheckTime()) {
            user.setLastCheckTime(LocalDate.now());
            user.setCheckTimes(1);

        } else {
            //TODO 用户非第一次签到，且当天已签到
            if (user.getLastCheckTime().equals(LocalDate.now())) {

                throw new RuntimeException("签到失败，当天已签到！");
            }

            //TODO 昨天已经签到，签到次数 + 1
            if (user.getLastCheckTime().equals(LocalDate.now().minusDays(1))) {
                user.setLastCheckTime(LocalDate.now());
                user.setCheckTimes(user.getCheckTimes() + 1);
                //TODO 昨天未签到，签到次数 = 1
            } else {
                user.setLastCheckTime(LocalDate.now());
                user.setCheckTimes(1);
            }
        }

        saveOrUpdate(user);
        publisher.publishEvent(new UserCheckInEvent(this, uid));
    }

    @Override
    public CheckInStatusVo checkInStatus() {
        Boolean isCheckIn = false;
        UserEntity user = getById(LoggedUser.get().getUserId());
        if (null == user) {

            throw new RuntimeException("用户不存在！");
        }
        if (null != user.getLastCheckTime() && user.getLastCheckTime().equals(LocalDate.now())) {
            isCheckIn = true;
        }
        CheckInStatusVo resultVo = new CheckInStatusVo()
                .setCheckTimes(user.getCheckTimes())
                .setLastCheckTime(user.getLastCheckTime())
                .setIsCheckIn(isCheckIn);

        return resultVo;
    }

    @Override
    public void loginSuccess(Integer uid) {
        UserEntity userEntity = getById(uid);
        userEntity.setLastLoginTime(LocalDateTime.now());
        updateById(userEntity);
    }


    @Override
    public Long visitedTotal(String when) {
        LocalDateTime now = LocalDateTime.now();
        if ("today".equalsIgnoreCase(when)) {
            LocalDateTime startOfDay = now.with(LocalTime.MIN);
            LocalDateTime endOfDay = now.with(LocalTime.MAX);
            return lambdaQuery().between(UserEntity::getLastLoginTime, startOfDay, endOfDay).count();
        }
        if ("yesterday".equalsIgnoreCase(when)) {
            LocalDateTime yesterday = now.minusDays(1);
            LocalDateTime startOfDay = yesterday.with(LocalTime.MIN);
            LocalDateTime endOfDay = yesterday.with(LocalTime.MAX);
            return lambdaQuery().between(UserEntity::getLastLoginTime, startOfDay, endOfDay).count();
        }

        return 0L;
    }

    @Override
    public Long visitedTotal(LocalDateTime startTime, LocalDateTime endTime) {

        return lambdaQuery().between(UserEntity::getLastLoginTime, startTime, endTime).count();
    }

    @Override
    public Long registerTotal(String when) {
        LocalDateTime now = LocalDateTime.now();
        if ("today".equalsIgnoreCase(when)) {
            LocalDateTime startOfDay = now.with(LocalTime.MIN);
            LocalDateTime endOfDay = now.with(LocalTime.MAX);
            return lambdaQuery().between(UserEntity::getCreateTime, startOfDay, endOfDay).count();
        }
        if ("yesterday".equalsIgnoreCase(when)) {
            LocalDateTime yesterday = now.minusDays(1);
            LocalDateTime startOfDay = yesterday.with(LocalTime.MIN);
            LocalDateTime endOfDay = yesterday.with(LocalTime.MAX);
            return lambdaQuery().between(UserEntity::getCreateTime, startOfDay, endOfDay).count();
        }

        return 0L;
    }

    @Override
    public Long registerTotal(LocalDateTime startTime, LocalDateTime endTime) {

        return lambdaQuery().between(UserEntity::getCreateTime, startTime, endTime).count();
    }

    @Override
    public Map<LocalDate, Long> getLast30daysRegisterTotal() {
        Map<LocalDate, Long> result = new LinkedHashMap();
        result.putAll(defaultLast30days());
        List<UserTotalDateVo> records = baseMapper.groupByLast30days();
        if (CollectionUtils.isNotEmpty(records)) {
            Map<LocalDate, Long> existMap = records.stream()
                    .collect(Collectors.toMap(UserTotalDateVo::getDate, UserTotalDateVo::getTotal));
            result.putAll(existMap);
        }

        return result;
    }

    @Override
    public BigDecimal getBalanceTotal() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(balance) as totalPrice");
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(UserEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public BigDecimal getCreditTotal() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(credit) as totalPrice");
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(UserEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public BigDecimal getBrokerageTotal() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("sum(brokerage) as totalPrice");
        BigDecimal totalPrice = Optional.ofNullable(getOne(queryWrapper))
                .map(UserEntity::getTotalPrice)
                .orElse(BigDecimal.ZERO);

        return totalPrice;
    }

    @Override
    public void openVip(Integer uid, Integer days) {
        UserEntity userEntity = getById(uid);
        LocalDateTime startTime = Optional.ofNullable(userEntity.getExpiredTime()).orElse(LocalDateTime.now());
        userEntity.setIsVip(true);
        userEntity.setExpiredTime(startTime.plusDays(days));

        updateById(userEntity);
    }

    @Override
    public void closeVip() {
        lambdaUpdate()
                .apply("expired_time < now()")
                .set(UserEntity::getIsVip, false)
                .set(UserEntity::getExpiredTime, null).update();
    }

    private Map<LocalDate, Long> defaultLast30days() {
        LocalDate today = LocalDate.now();
        Map<LocalDate, Long> last30DaysList = new LinkedHashMap<>();
        for (int i = 30; i > 0; i--) {
            LocalDate date = today.minusDays(i);
            last30DaysList.put(date, 0L);
        }

        return last30DaysList;
    }
}
