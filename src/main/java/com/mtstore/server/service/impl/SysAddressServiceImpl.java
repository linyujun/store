package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.SysAddressEntity;
import com.mtstore.server.beans.mapper.SysAddressMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.SysAddressService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 系统售后收货地址
*/
@Service
@RequiredArgsConstructor
public class SysAddressServiceImpl extends ServiceImpl<SysAddressMapper, SysAddressEntity> implements SysAddressService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
