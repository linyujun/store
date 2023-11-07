package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.login.WxUserInfo;
import com.mtstore.server.beans.dto.user.UserInfoDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.vo.CheckInStatusVo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author songsir
 * 小程序端用户信息
 **/
public interface UserService extends IKService<UserEntity, UserInfoDto> {

    UserEntity findCurrentUser();

    UserEntity findByPhone(String phone);

    UserEntity findUserByOpenIdOrPhone(String openId, String phone);

    UserEntity loginByPhone(String phone);

    UserEntity loginByWx(String phone, String openId, WxUserInfo dto);

    /**
     * 更新用户余额
     * @param uid y用户id
     * @param amount 金额
     */
    BigDecimal incBalance(Integer uid, BigDecimal amount);

    /**
     * 减少余额
     * @param uid
     * @param amount
     * @return
     */
    BigDecimal decBalance(Integer uid, BigDecimal amount);

    /**
     * 设置余额
     * @param uid
     * @param amount
     * @return
     */
    BigDecimal setBalance(Integer uid, BigDecimal amount);

    /**
     * 设置积分
     * @param uid
     * @param amount
     * @return
     */
    BigDecimal setCredit(Integer uid, BigDecimal amount);

    /**
     * 增加积分
     * @param uid uid
     */
    BigDecimal incCredit(Integer uid, BigDecimal amount);

    /**
     * 减少积分
     * @param uid
     * @param amount
     * @return
     */
    BigDecimal decCredit(Integer uid, BigDecimal amount);

    /**
     * 获取当前用户积分
     * @param uid
     * @return
     */
    BigDecimal getCredit(Integer uid);

    /**
     * 获取当前用户余额
     * @param uid
     * @return
     */
    BigDecimal getBalance(Integer uid);


    /**
     * 增加佣金
     * @param uid uid
     */
    BigDecimal incBrokerage(Integer uid, BigDecimal amount);

    /**
     * 减少佣金
     * @param uid
     * @param amount
     * @return
     */
    BigDecimal decBrokerage(Integer uid, BigDecimal amount);

    /**
     * 配合前端二次封装分页查询
     * @param pageDto
     * @param wrapper
     * @return
     */
    @Override
    Page getPageList(PageDto pageDto, QueryWrapper wrapper);


    /**
     * 签到
     */
    void checkIn();

    /**
     * 签到状态
     */
    CheckInStatusVo checkInStatus();


    void loginSuccess(Integer uid);

    /**
     * 统计访问人数
     * @param when
     * @return
     */
    Long visitedTotal(String when);

    /**
     * 查看注册量
     * @param when
     * @return
     */
    Long registerTotal(String when);

    Long visitedTotal(LocalDateTime startTime, LocalDateTime endTime);

    Long registerTotal(LocalDateTime startTime, LocalDateTime endTime);

    Map<LocalDate, Long> getLast30daysRegisterTotal();

    BigDecimal getBalanceTotal();

    BigDecimal getCreditTotal();

    BigDecimal getBrokerageTotal();

    void openVip(Integer uid, Integer days);

    void closeVip();
}
