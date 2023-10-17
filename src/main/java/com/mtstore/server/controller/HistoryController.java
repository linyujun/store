package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.sys.SysImportLogEntity;
import com.mtstore.server.service.SysImportLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-04-11
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    final SysImportLogService importLogService;

    @PostMapping("/import/getPageList")
    public Object findImportPage(@RequestBody PageDto pageDto) {
        QueryWrapper<SysImportLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user", LoggedUser.get().getUserId());

        return R.ok("获取成功", importLogService.getPageList(pageDto, queryWrapper));
    }
}
