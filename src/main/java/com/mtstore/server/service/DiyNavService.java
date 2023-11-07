package com.mtstore.server.service;

import com.mtstore.server.beans.dto.diy.DiyNavDto;
import com.mtstore.server.beans.entity.DiyNavEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 导航栏DIY
 * </p>
 *
 * @author songsir
 * @since 2023-03-31
 */
public interface DiyNavService extends IService<DiyNavEntity> {

    void save(List<DiyNavDto> todoList);

    void forceDelete();
}
