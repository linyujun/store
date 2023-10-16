package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.UserBrokerageBillEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserBrokerageBillDto;

import java.math.BigDecimal;

/**
* @author songsir
* @date 2023-06-19
*/
public interface UserBrokerageBillService extends IKService<UserBrokerageBillEntity, UserBrokerageBillDto>{

    /**
     * 收入
     * @param uid
     * @param action
     * @param amount
     * @param description
     */
    void income(Integer uid, String action, BigDecimal amount, String description);

    /**
     * 支出
     * @param uid
     * @param action
     * @param amount
     * @param description
     */
    void consume(Integer uid, String action, BigDecimal amount, String description);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    BigDecimal getWithdrawTotal();

    BigDecimal getPendingTotal();
}
