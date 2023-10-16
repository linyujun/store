package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.StoreBargainLogEntity;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.StoreBargainLogDto;

/**
* @author songsir
* @date 2023-06-07
*/
public interface StoreBargainLogService extends IKService<StoreBargainLogEntity, StoreBargainLogDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    void check();
}
