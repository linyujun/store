package com.mtstore.server.service.impl;

import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserAddressDto;
import com.mtstore.server.beans.entity.UserAddressEntity;
import com.mtstore.server.beans.mapper.UserAddressMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Optional;

/**
* @author songsir
* @date 2023-04-19
*/
@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {

    @Override
    public UserAddressEntity saveOrUpdate(UserAddressDto dto) {
        UserAddressEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElseGet(UserAddressEntity::new);
        if (!isExist(dto)){
            BeanUtils.copyProperties(dto, entity);
            if (!exists()) {
                entity.setIsDefault(true);
            }

            saveOrUpdate(entity);
            if (null != dto.getIsDefault() && dto.getIsDefault()) {
                setDefault(entity.getId());
            }
        }

        return entity;
    }

    /**
     * 检查用户是否有地址，第一个设置为默认
     * @return
     */
    private Boolean exists(){
        return lambdaQuery()
                .eq(UserAddressEntity::getUid, LoggedUser.get().getUserId()).exists();
    }

    /**
     * 通过微信一键获取地址添加去重
     * @param dto
     * @return
     */
    private Boolean isExist(UserAddressDto dto) {
        if (null != dto.getId()) return false;
        return lambdaQuery()
                .eq(UserAddressEntity::getAddressDetail, dto.getAddressDetail())
                .eq(UserAddressEntity::getPhone, dto.getPhone())
                .eq(UserAddressEntity::getNickName, dto.getNickName()).exists();
    }

    /**
     * 设置默认地址
     * @param id
     */
    @Override
    public void setDefault(Integer id) {
        lambdaUpdate()
                .eq(UserAddressEntity::getUid, LoggedUser.get().getUserId())
                .set(UserAddressEntity::getIsDefault, false).update();

        lambdaUpdate().eq(UserAddressEntity::getId, id)
                .eq(UserAddressEntity::getUid, LoggedUser.get().getUserId())
                .set(UserAddressEntity::getIsDefault, true).update();
    }

    /**
     * 强制删除
     * @param id
     */
    @Override
    public void forceDelete(Integer id) {
        baseMapper.forceDelete(id, LoggedUser.get().getUserId());
    }

    /**
     * 获取默认地址
     * @param userId
     * @return
     */
    @Override
    public UserAddressEntity getDefault(Integer userId) {
        return getOne(lambdaQuery().eq(UserAddressEntity::getIsDefault, true)
                .eq(UserAddressEntity::getUid, userId)
                .eq(UserAddressEntity::getEnabled, true).getWrapper());
    }

    /**
     * 查询所有地址
     * @param userId
     * @return
     */
    @Override
    public List<UserAddressEntity> getAll(Integer userId) {
        return lambdaQuery()
                .eq(UserAddressEntity::getUid, userId)
                .eq(UserAddressEntity::getEnabled, true).list();
    }
}
