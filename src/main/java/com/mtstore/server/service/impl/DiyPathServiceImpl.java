package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.DiyPathEntity;
import com.mtstore.server.beans.mapper.DiyPathMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.DiyPathService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* @author songsir
* 页面路径
*/
@Service
@RequiredArgsConstructor
public class DiyPathServiceImpl extends ServiceImpl<DiyPathMapper, DiyPathEntity> implements DiyPathService {
}
