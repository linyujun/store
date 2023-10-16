package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysImportLogEntity;
import com.kinzhan.dev.platform.beans.mapper.SysImportLogMapper;
import com.kinzhan.dev.platform.service.SysImportLogService;
import com.kinzhan.dev.platform.util.FilterUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songsir
 * @since 2023-02-13
 */
@Service
public class SysImportLogServiceImp extends ServiceImpl<SysImportLogMapper, SysImportLogEntity> implements SysImportLogService {

    @Override
    public Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        Page page = new Page<SysImportLogEntity>(pageDto.getPage(),pageDto.getSize());
        Page result = page(page, wrapper);

        return result;
    }

    @Override
    public void saveLog(String result, String target, String filePath, Integer total, String error) {
        Integer success = result.equals("成功") ? total: 0;
        Integer errorCount = StringUtils.countMatches(error, "\n");
        SysImportLogEntity entity = new SysImportLogEntity()
                .setTarget(target)
                .setFilePath(filePath)
                .setTotalLine(total)
                .setFileName(FileNameUtil.getName(filePath))
                .setFailLine(errorCount)
                .setSuccessLine(success)
                .setError(error)
                .setResult(result);

        save(entity);
    }
}
