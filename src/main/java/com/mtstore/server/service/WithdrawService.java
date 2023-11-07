package com.mtstore.server.service;

import com.mtstore.server.beans.dto.withdraw.WithdrawCancelDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawConfirmDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawDto;
import com.mtstore.server.beans.dto.withdraw.WithdrawRejectDto;
import com.mtstore.server.beans.entity.WithdrawEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author songsir
 * 提现记录表
 */
public interface WithdrawService extends IKService<WithdrawEntity, WithdrawDto> {

    /**
     * 驳回
     * @param dto
     * @return
     */
    Boolean reject(WithdrawRejectDto dto);

    /**
     * 确认打款
     * @param dto
     * @return
     */
    Boolean confirm(WithdrawConfirmDto dto);

    /**
     * 取消
     * @param dto
     * @return
     */
    Boolean cancel(WithdrawCancelDto dto);

    /**
     * 支出金额
     * @return
     */
    BigDecimal expendTotalPrice(LocalDateTime startTime, LocalDateTime endTime);
}
