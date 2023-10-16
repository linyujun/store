package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.MerchantEntity;
import com.kinzhan.dev.platform.service.MerchantService;
import com.kinzhan.dev.platform.beans.mapper.MerchantMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
/**
* @author songsir
* @date 2023-04-06
*/
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, MerchantEntity> implements MerchantService {
}
