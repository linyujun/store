package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreRechargeEntity;
import com.mtstore.server.beans.dto.mall.recharge.RechargeDto;

/**
* @author songsir
* 商城充值
*/
public interface StoreRechargeService extends IKService<StoreRechargeEntity, RechargeDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
