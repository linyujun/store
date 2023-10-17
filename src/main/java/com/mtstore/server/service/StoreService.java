package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreEntity;
import com.mtstore.server.beans.dto.store.StoreDto;

/**
* @author songsir
* @date 2023-04-27
*/
public interface StoreService extends IKService<StoreEntity, StoreDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
