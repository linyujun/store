package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.UserLevelEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserLevelDto;

/**
* @author songsir
* @date 2023-04-25
*/
public interface UserLevelService extends IKService<UserLevelEntity, UserLevelDto>{

    String findLevelNameById(Integer id);


    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 复制一个处理
     * @param id
     */
    void copyOne(Integer id);
}
