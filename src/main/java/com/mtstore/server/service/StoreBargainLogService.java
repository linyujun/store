package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreBargainLogEntity;
import com.mtstore.server.beans.dto.mall.bargain.StoreBargainLogDto;

/**
* @author songsir
* 砍价记录
*/
public interface StoreBargainLogService extends IKService<StoreBargainLogEntity, StoreBargainLogDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    void check();
}
