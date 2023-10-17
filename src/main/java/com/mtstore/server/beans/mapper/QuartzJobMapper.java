/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.sys.SysQuartzJob;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
public interface QuartzJobMapper extends BaseMapper<SysQuartzJob> {

}
