package com.mtstore.server.service;

import com.mtstore.server.beans.dto.mall.product.CartDto;
import com.mtstore.server.beans.entity.StoreCartEntity;

import java.util.List;

/**
* @author songsir
* @date 2023-04-19
*/
public interface StoreCartService extends IKService<StoreCartEntity, CartDto>{

    /**
     * 加购物车
     * @param cartDto
     * @return
     */
    StoreCartEntity add(CartDto cartDto);
    /**
     * 强制删除
     * @param id
     */
    void forceDelete(Integer id);

    /**
     * 清空购物车功能
     * @param userId
     */
    void forceDeleteByUserId(Integer userId);

    /**
     * 用户购买成功以后，清空购物车
     * @param ids
     */
    void forceDeleteByIds(List<Integer> ids);
}
