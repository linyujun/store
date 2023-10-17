package com.mtstore.server.service;

import com.mtstore.server.beans.entity.UserLevelEntity;
import com.mtstore.server.beans.dto.user.UserLevelDto;

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
