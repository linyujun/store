package com.kinzhan.dev.platform.service;


import com.kinzhan.dev.platform.beans.dto.tenant.TenantDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysTenantEntity;

/**
 * <p>
 * 代理商表 服务类
 * </p>
 *
 * @author songsir
 * @since 2022-02-09
 */
public interface TenantService extends IKService<SysTenantEntity, TenantDto> {

    Boolean save(TenantDto dto);

    SysTenantEntity getCurrent();
}
