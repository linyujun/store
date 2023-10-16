package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.SysAddressEntity;
import com.kinzhan.dev.platform.beans.dto.sysaddress.SysAddressDto;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author songsir
* @date 2023-06-13
*/
public interface SysAddressService extends IKService<SysAddressEntity, SysAddressDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
