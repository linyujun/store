package com.mtstore.server.service;


import com.mtstore.server.beans.dto.push.PushDto;
import com.mtstore.server.beans.entity.sys.SysPushEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songsir
 * @since 2023-02-02
 */
public interface SysPushService extends IKService<SysPushEntity, PushDto> {

    Boolean save(PushDto dto);
}
