package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreAfterSalesAuditDto;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreReturnDto;
import com.kinzhan.dev.platform.beans.entity.StoreAfterSalesEntity;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreAfterSalesDto;

/**
* @author songsir
* @date 2023-06-12
*/
public interface StoreAfterSalesService extends IKService<StoreAfterSalesEntity, StoreAfterSalesDto>{

    /**
     * 通过订单查找售后记录
     * @param orderId
     * @return
     */
    StoreAfterSalesEntity findByOrderId(String orderId);

    /**
     * 审核售后
     * @return
     */
    StoreAfterSalesEntity audit(StoreAfterSalesAuditDto dto);

    /**
     * 退货方式
     * @param dto
     * @return
     */
    StoreAfterSalesEntity returned(StoreReturnDto dto);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
