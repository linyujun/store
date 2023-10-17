package com.mtstore.server.service;

import com.mtstore.server.beans.dto.system.DeptDto;
import com.mtstore.server.beans.entity.sys.SysDeptEntity;

/**
 * <p>
 * 组织架构 服务类
 * </p>
 *
 * @author songsir
 * @since 2023-03-03
 */
public interface SysDeptService extends IKService<SysDeptEntity, DeptDto> {


    void forceDelete(Integer id);
}
