package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.AgentEntity;
import com.mtstore.server.beans.mapper.AgentMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.AgentService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* @author songsir
* 代理商表
*/
@Service
@RequiredArgsConstructor
public class AgentServiceImpl extends ServiceImpl<AgentMapper, AgentEntity> implements AgentService {
}
