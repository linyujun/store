package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainCreateDto;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainHelpDto;
import com.kinzhan.dev.platform.beans.entity.StoreBargainEntity;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.StoreBargainDto;
import com.kinzhan.dev.platform.beans.entity.StoreBargainLogEntity;

/**
* @author songsir
* @date 2023-06-07
*/
public interface StoreBargainService extends IKService<StoreBargainEntity, StoreBargainDto>{

    /**
     * 创建一个砍价活动
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
