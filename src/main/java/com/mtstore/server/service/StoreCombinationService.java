package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreCombinationEntity;
import com.mtstore.server.beans.dto.mall.combination.StoreCombinationDto;
import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;

/**
* @author songsir
* 拼团
*/
public interface StoreCombinationService extends IKService<StoreCombinationEntity, StoreCombinationDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 检查活动规范
     * @param dto
     * @return
     */
    void valid(StoreCombinationDto dto);

    /**
     * 通过商品反查拼团活动
     * @param productId
     * @return
     */
    StoreCombinationEntity findByProductId(Integer productId);

    /**
     * 创建拼团记录
     * @return
     */
    StoreCombinationLogEntity create(StoreOrderEntity order);

    /**
     * 加入拼团
     * @param order
     * @return
     */
    StoreCombinationLogEntity join(StoreOrderEntity order);

    /**
     * 处理拼团活动状态
     */
    void check();
}
