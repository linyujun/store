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

/**
* @author hupeng
* 定时任务日志
*/
@Service
@AllArgsConstructor
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogMapper, SysQuartzLog> implements QuartzLogService {

}
