package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.StoreAttrTemplateEntity;
import com.mtstore.server.beans.mapper.StoreAttrTemplateMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreAttrTemplateService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* @author songsir
* 规格模板
*/
@Service
@RequiredArgsConstructor
public class StoreAttrTemplateServiceImpl extends ServiceImpl<StoreAttrTemplateMapper, StoreAttrTemplateEntity> implements StoreAttrTemplateService {
}
