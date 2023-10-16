package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.mall.StoreExpressDto;
import com.kinzhan.dev.platform.beans.entity.StoreExpressEntity;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author songsir
* @date 2023-05-31
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
