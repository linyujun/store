package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreEntity;
import com.mtstore.server.beans.dto.store.StoreDto;

/**
* @author songsir
* 门店对象
*/
public interface StoreService extends IKService<StoreEntity, StoreDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
