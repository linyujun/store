package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreServiceEntity;
import com.mtstore.server.beans.dto.mall.StoreServiceDto;

/**
* @author songsir
* 商品服务
*/
public interface StoreServiceService extends IKService<StoreServiceEntity, StoreServiceDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
