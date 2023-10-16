package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.SysAddressEntity;
import com.kinzhan.dev.platform.service.SysAddressService;
import com.kinzhan.dev.platform.beans.mapper.SysAddressMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-06-13
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
