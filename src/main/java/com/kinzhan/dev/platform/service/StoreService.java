package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.StoreEntity;
import com.kinzhan.dev.platform.beans.dto.store.StoreDto;
import java.util.Map;
import java.util.List;
import java.io.IOException;
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
