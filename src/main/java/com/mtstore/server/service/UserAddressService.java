package com.mtstore.server.service;

import com.mtstore.server.beans.dto.user.UserAddressDto;
import com.mtstore.server.beans.entity.UserAddressEntity;

import java.util.List;

/**
* @author songsir
* @date 2023-04-19
*/
public interface UserAddressService extends IKService<UserAddressEntity, UserAddressDto>{

    void setDefault(Integer id);

    void forceDelete(Integer id);

    UserAddressEntity getDefault(Integer userId);

    List<UserAddressEntity> getAll(Integer userId);
}
