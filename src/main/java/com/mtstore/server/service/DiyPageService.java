package com.mtstore.server.service;

import com.mtstore.server.beans.dto.diy.DiyPageDto;
import com.mtstore.server.beans.entity.DiyPageEntity;

/**
* @author songsir
* DIY页面
*/
public interface DiyPageService extends IKService<DiyPageEntity, DiyPageDto>{

    /**
     * 禁用，启用
     * @param id
     */
    void disable(Integer id);

    void setDefault(Integer id);


    /**
     * 复制一个处理
     * @param id
     */
    void copyOne(Integer id);
}
