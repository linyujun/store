package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.ImportRequestDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.system.DirectoryDto;
import com.mtstore.server.beans.entity.sys.SysDirectoryEntity;
import com.mtstore.server.beans.entity.sys.SysUploadEntity;
import com.mtstore.server.service.SysDirectoryService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.service.SysUploadService;
import com.mtstore.server.util.FilterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 目录表
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags="上传模块-目录管理")
@RequestMapping("/api/sys/directory")
public class SysDirectoryController {
    final SysDirectoryService service;
    final SysUploadService sysUploadService;

    @ApiOperation("添加/修改目录")
    @PostMapping
    public Object save(@RequestBody @Validated DirectoryDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }


    @ApiOperation("排序")
    @PostMapping("/sort")
    public Object sort(@RequestBody @Validated List<DirectoryDto> directoryDtoList) {
        directoryDtoList.forEach( v3 -> {
            service.saveOrUpdate(v3);
        });

        return R.ok("保存成功", true);
    }


    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        SysDirectoryEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @ApiOperation("删除目录")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        if (sysUploadService.lambdaQuery()
                .eq(SysUploadEntity::getDirectoryId, id)
                .eq(SysUploadEntity::getIsDelete, false)
                .exists()) {
            throw new RuntimeException("当前目录下尚有文件，不可删除");
        }
        if (service.count() <= 1) {
            throw new RuntimeException("请至少保留一个文件夹");
        }
        service.forceDelete(id);

        return R.ok("操作成功");
    }

    @ApiOperation("目录分页")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<SysDirectoryEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().orderByAsc(SysDirectoryEntity::getSort);

        return R.ok("获取成功", service.getPageList(pageDto, queryWrapper));
    }


    @ApiOperation("全部目录")
    @PostMapping("/getAll")
    @ResponseBody
    public Object findAll(@RequestBody(required=false) PageDto pageDto) {
        QueryWrapper<SysDirectoryEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        QueryWrapper<SysDirectoryEntity> queryWrapper = FilterUtil.convertFilterDtoToWrapper(pageDto, wrapper);
        queryWrapper.eq("enabled", 1);
        queryWrapper.eq("is_delete", 0);

        return R.ok("获取成功", service.list(queryWrapper));
    }

    /**
    * 导出
    */
    @PostMapping(value = "/download")
    @ResponseBody
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {
    }

    /**
    * 导入
    */
    @PostMapping(value = "/import")
    @ResponseBody
    public Object importExcel(@RequestBody ImportRequestDto requestDto) throws IOException {
        log.info("request {}", requestDto);

        return R.ok("成功");
    }

    /**
     * 搜索自动完成
     * @param name
     * @return
     */
    @GetMapping("/search/{name}")
    public Object search(@PathVariable("name") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();

        return R.ok("获取成功", service.list(queryWrapper));
    }
}
