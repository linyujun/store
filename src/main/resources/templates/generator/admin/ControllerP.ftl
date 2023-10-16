package ${package}.controller;

import java.util.*;
import lombok.RequiredArgsConstructor;
import ${package}.beans.common.R;
import ${package}.beans.dto.filter.PageDto;
import ${package}.beans.entity.${className}Entity;
import ${package}.beans.dto.${lowerCaseClassName}.${className}Dto;
import ${package}.service.${className}Service;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author ${author}
* @date ${date}
*/
@RequiredArgsConstructor
@Api(tags = "${apiAlias}管理")
@RestController
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

    private final ${className}Service ${changeClassName}Service;

    @GetMapping("{id}")
    @ApiOperation("查询${apiAlias}")
    public Object getOne(@PathVariable("id") Integer id){
        ${className}Entity entity = ${changeClassName}Service.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    @ApiOperation("新增${apiAlias}")
    public Object save(@Validated @RequestBody ${className}Dto dto){
        ${changeClassName}Service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询${apiAlias}")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", ${changeClassName}Service.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody ${pkColumnType}[] ids) {
        Arrays.asList(ids).forEach(id->{
            ${changeClassName}Service.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        ${changeClassName}Service.removeById(id);

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
        ${changeClassName}Service.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        ${changeClassName}Service.download(response, Map.class, pageDto, null);
    }
}
