package com.mtstore.server.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.system.DictDto;
import com.mtstore.server.beans.dto.system.DictSortDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.sys.SysDictEntity;
import com.mtstore.server.service.SysDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 系统字典
 **/
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/sys/dict")
public class SysDictController {
    private final SysDictService sysDictService;

    @PostMapping
    public Object save(@RequestBody @Validated DictDto dto) {
        sysDictService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    /**
     * 排序
     * @param dto
     * @return
     */
    @PostMapping("/setSort")
    @ResponseBody
    public Object setSort(@RequestBody @Validated DictSortDto dto) {
        sysDictService.setSort(dto.getId(), dto.getAction());

        return R.ok("操作成功");
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Object deleteOne(@PathVariable("id") Integer id) {

        return R.ok("操作成功", sysDictService.removeById(id));
    }

    /**
     * 禁用字典
     * @param id
     * @return
     */
    @GetMapping("/disable/{id}")
    @ResponseBody
    public Object disable(@PathVariable("id") Integer id) {
        sysDictService.disable(id);
        return R.ok("操作成功", true);
    }

    /**
     * 字典分页
     * @param pageDto
     * @return
     */
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<SysDictEntity> queryWrapper = new QueryWrapper();
        queryWrapper
                .lambda()
                .orderByAsc(SysDictEntity::getSort);
        return R.ok("获取成功", sysDictService.getPageList(pageDto, queryWrapper));
    }

}
