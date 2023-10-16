package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.StoreRechargeEntity;
import com.kinzhan.dev.platform.beans.dto.mall.recharge.RechargeDto;

import java.time.LocalDateTime;

/**
* @author songsir
* @date 2023-05-26
*/
public interface StoreRechargeService extends IKService<StoreRechargeEntity, RechargeDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
