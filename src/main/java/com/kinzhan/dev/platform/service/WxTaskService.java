package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.WxTaskEntity;
import com.kinzhan.dev.platform.beans.dto.wx.WxTaskDto;

/**
* @author songsir
* @date 2023-05-23
*/
public interface WxTaskService extends IKService<WxTaskEntity, WxTaskDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
