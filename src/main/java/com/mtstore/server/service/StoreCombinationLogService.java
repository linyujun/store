package com.mtstore.server.service;

import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.beans.dto.mall.combination.StoreCombinationLogDto;

import java.util.List;

/**
* @author songsir
* 拼团记录
*/
public interface StoreCombinationLogService extends IKService<StoreCombinationLogEntity, StoreCombinationLogDto>{

    /**
     * 获取分组id
     * @param groupId
     * @return
     */
    List<StoreCombinationLogEntity> findAllByGroupId(Integer groupId);

    /**
     * 查找在拼的拼团记录
     * @param combinationId
     * @return
     */
    List<StoreCombinationLogEntity> findAllByCombinationId(Integer combinationId);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 检查拼团状态
     */
    void check();
}
