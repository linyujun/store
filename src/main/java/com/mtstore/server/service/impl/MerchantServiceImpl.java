package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.MerchantEntity;
import com.mtstore.server.beans.mapper.MerchantMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.MerchantService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* @author songsir
* @date 2023-04-06
*/
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, MerchantEntity> implements MerchantService {
}
