package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreSeckillDetailEntity;
import com.mtstore.server.beans.dto.mall.seckill.SeckillDetailDto;

import java.util.List;

/**
* @author songsir
* 秒杀商品关系
*/
public interface StoreSeckillDetailService extends IKService<StoreSeckillDetailEntity, SeckillDetailDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 保存详情
     * @param details
     * @param seckillId
     */
    void saveAll(List<SeckillDetailDto> details, Integer seckillId);
}
