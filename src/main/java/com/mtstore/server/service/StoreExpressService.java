package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.StoreExpressDto;
import com.mtstore.server.beans.entity.StoreExpressEntity;

import java.util.List;

/**
* @author songsir
* 运费模板
*/
public interface StoreExpressService extends IKService<StoreExpressEntity, StoreExpressDto>{


    StoreExpressEntity getDefault();

    StoreExpressEntity getDetail(Integer id);

    List<StoreExpressEntity> findAllByParentId(Integer id);
    /**
     * 删除子集
     * @param id
     */
    void deleteByParentId(Integer id);
    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 设置默认
     * @param id
     */
    void setDefault(Integer id);
}
