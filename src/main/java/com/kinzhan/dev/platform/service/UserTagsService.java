package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.UserTagsEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserTagsDto;

/**
* @author songsir
* @date 2023-05-26
*/
public interface UserTagsService extends IKService<UserTagsEntity, UserTagsDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
