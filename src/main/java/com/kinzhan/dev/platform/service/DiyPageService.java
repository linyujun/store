package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.diy.DiyPageDto;
import com.kinzhan.dev.platform.beans.entity.DiyPageEntity;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author songsir
* @date 2023-04-17
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
