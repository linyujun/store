package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.StoreServiceEntity;
import com.kinzhan.dev.platform.beans.dto.mall.StoreServiceDto;

/**
* @author songsir
* @date 2023-05-13
*/
public interface StoreServiceService extends IKService<StoreServiceEntity, StoreServiceDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
