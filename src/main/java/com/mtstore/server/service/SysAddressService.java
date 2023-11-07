package com.mtstore.server.service;

import com.mtstore.server.beans.entity.SysAddressEntity;
import com.mtstore.server.beans.dto.sysaddress.SysAddressDto;

/**
* @author songsir
* 系统售后收货地址
*/
public interface SysAddressService extends IKService<SysAddressEntity, SysAddressDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
