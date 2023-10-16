package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.DiyPathEntity;
import com.kinzhan.dev.platform.service.DiyPathService;
import com.kinzhan.dev.platform.beans.mapper.DiyPathMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
/**
* @author songsir
* @date 2023-04-25
*/
@Service
@RequiredArgsConstructor
public class DiyPathServiceImpl extends ServiceImpl<DiyPathMapper, DiyPathEntity> implements DiyPathService {
}
