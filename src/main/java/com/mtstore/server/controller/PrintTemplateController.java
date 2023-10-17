package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.printer.template.PrintTemplateDto;
import com.mtstore.server.beans.entity.PrintTemplateEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.PrintTemplateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-06-15
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/printTemplate")
public class PrintTemplateController {

    private final PrintTemplateService printTemplateService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        PrintTemplateEntity entity = printTemplateService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody PrintTemplateDto dto){
        printTemplateService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", printTemplateService.getPageList(pageDto, null));
    }


    @GetMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<PrintTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PrintTemplateEntity::getEnabled, true);

        return R.ok("获取成功", printTemplateService.list(wrapper));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            printTemplateService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        printTemplateService.removeById(id);

        return R.ok("操作成功");
    }

    /**
    * 禁用，启用
    * @param id
    * @return
    */
    @GetMapping("/disable/{id}")
    @ResponseBody
    public Object disable(@PathVariable("id") Integer id) {
        printTemplateService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        printTemplateService.download(response, Map.class, pageDto, null);
    }
}
