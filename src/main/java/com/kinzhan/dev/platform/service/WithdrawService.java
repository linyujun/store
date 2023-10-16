package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawCancelDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawConfirmDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawRejectDto;
import com.kinzhan.dev.platform.beans.entity.WithdrawEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 提现分润记录表 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-09-20
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
