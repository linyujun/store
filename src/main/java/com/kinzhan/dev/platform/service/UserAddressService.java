package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.dto.user.UserAddressDto;
import com.kinzhan.dev.platform.beans.entity.UserAddressEntity;
import java.util.Map;
import java.util.List;
import java.io.IOException;
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
