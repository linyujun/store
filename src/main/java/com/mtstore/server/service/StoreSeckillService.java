package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreSeckillEntity;
import com.mtstore.server.beans.dto.mall.seckill.SeckillDto;

/**
* @author songsir
* 秒杀会场
*/
public interface StoreSeckillService extends IKService<StoreSeckillEntity, SeckillDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);


    /**
     * 处理秒杀的活动状态
     */
    void check();
}
