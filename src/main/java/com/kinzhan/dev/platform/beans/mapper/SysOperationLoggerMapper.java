package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinzhan.dev.platform.beans.entity.sys.SysOperationLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ww
 * @date 2021/7/20
 **/
@Mapper
public interface SysOperationLoggerMapper extends BaseMapper<SysOperationLogEntity> {
}
