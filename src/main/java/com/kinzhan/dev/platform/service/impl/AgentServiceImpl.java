package com.kinzhan.dev.platform.service.impl;

import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.AgentEntity;
import com.kinzhan.dev.platform.service.AgentService;
import com.kinzhan.dev.platform.beans.mapper.AgentMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
/**
* @author songsir
* @date 2023-04-18
*/
@Service
@RequiredArgsConstructor
public class AgentServiceImpl extends ServiceImpl<AgentMapper, AgentEntity> implements AgentService {
}
