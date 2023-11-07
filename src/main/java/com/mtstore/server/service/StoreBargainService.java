package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.bargain.BargainCreateDto;
import com.mtstore.server.beans.dto.mall.bargain.BargainHelpDto;
import com.mtstore.server.beans.entity.StoreBargainEntity;
import com.mtstore.server.beans.dto.mall.bargain.StoreBargainDto;
import com.mtstore.server.beans.entity.StoreBargainLogEntity;

/**
* @author songsir
* 砍价
*/
public interface StoreBargainService extends IKService<StoreBargainEntity, StoreBargainDto>{

    /**
     * 发起砍价
     * @param dto
     * @return
     */
    StoreBargainLogEntity create(BargainCreateDto dto);


    /**
     * 砍价助力
     * @param dto
     * @return
     */
    StoreBargainLogEntity help(BargainHelpDto dto);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);


    /**
     * 检测活动状态
     */
    void check();
}
