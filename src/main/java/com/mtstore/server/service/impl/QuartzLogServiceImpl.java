/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.sys.SysQuartzLog;
import com.mtstore.server.beans.mapper.QuartzLogMapper;
import com.mtstore.server.service.QuartzLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogMapper, SysQuartzLog> implements QuartzLogService {

}
