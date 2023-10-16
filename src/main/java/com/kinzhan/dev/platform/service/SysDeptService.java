package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.system.DeptDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysDeptEntity;

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
