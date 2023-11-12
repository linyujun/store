package com.mtstore.server.service;

import com.mtstore.server.beans.dto.diy.DiyNavDto;
import com.mtstore.server.beans.entity.DiyNavEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 导航栏DIY
 */
public interface DiyNavService extends IService<DiyNavEntity> {

    void save(List<DiyNavDto> todoList);

    void forceDelete();
}
