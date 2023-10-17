package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.user.UserBillDto;
import com.mtstore.server.beans.entity.UserBillEntity;

import java.math.BigDecimal;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-10-12
 */
public interface UserBillService extends IKService<UserBillEntity, UserBillDto> {

    /**
     * 收入
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
     */
    void income(Integer uid, String action, String category, BigDecimal amount, String description);


    /**
     * 积分收入
     * @param uid
     * @param action
     */
    void incomeCredit(Integer uid, String action);

    /**
     * 支出
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
     */
    void expand(Integer uid, String action, String category, BigDecimal amount, String description);


    /**
     * 直接设置最终金额
     * @param uid
     * @param action
     * @param category
     * @param amount
     * @param description
     */
    void last(Integer uid, String action, String category, BigDecimal amount, String description);

    /**
     * 积分明细分页
     * @param dto
     * @param wrapper
     * @return
     */
    Page<UserBillEntity> getCreditPageList(PageDto dto, QueryWrapper wrapper);

    /**
     * 余额明细分页
     * @param dto
     * @param wrapper
     * @return
     */
    Page<UserBillEntity> getBalancePageList(PageDto dto, QueryWrapper wrapper);

    /**
     * 查看某个动作的积分总额
     * @param action
     * @return
     */
    BigDecimal getTotalByAction(String action);

    /**
     * 查看某个分类的积分总额
     * @param category
     * @return
     */
    BigDecimal getTotalByCategory(String category);
}

