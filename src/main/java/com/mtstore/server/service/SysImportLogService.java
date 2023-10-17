package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.sys.SysImportLogEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songsir
 * @since 2023-02-13
 */
public interface SysImportLogService extends IService<SysImportLogEntity> {

    Page getPageList(PageDto pageDto, QueryWrapper wrapper);

    void saveLog(String result, String target, String filePath, Integer total, String error);
}
