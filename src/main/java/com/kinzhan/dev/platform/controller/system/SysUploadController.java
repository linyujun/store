package com.kinzhan.dev.platform.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.dto.ImportRequestDto;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.system.UploadFileDto;
import com.kinzhan.dev.platform.beans.dto.system.UploadFileMoveDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysUploadEntity;
import com.kinzhan.dev.platform.service.SysUploadService;
import com.kinzhan.dev.platform.beans.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags="上传模块-文件管理")
@RequestMapping("/api/sys/upload")
public class SysUploadController {
    final SysUploadService service;

    @PostMapping
    @ApiOperation("添加/修改文件")
    public Object save(@RequestBody @Validated UploadFileDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        SysUploadEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @ApiOperation("删除文件")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.forceDelete(id);

        return R.ok("操作成功");
    }

    @ApiOperation("批量移动文件")
    @PostMapping("/move")
    public Object move(@RequestBody @Validated UploadFileMoveDto dto) {
        service.moveByIds(dto);

        return R.ok("操作成功");
    }


    @ApiOperation("批量删除文件")
    @PostMapping("/delete")
    public Object delete(@RequestBody List<Integer> ids) {
        service.forceDeleteByIds(ids);

        return R.ok("操作成功");
    }

    /**
     * 分页
     * @param pageDto
     * @return
     */
    @ApiOperation("分页")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", service.getPageList(pageDto, null));
    }

    /**
     * 全部
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<SysUploadEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
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
