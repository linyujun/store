package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.student.StudentDto;
import com.kinzhan.dev.platform.beans.dto.student.StudentExportDto;
import com.kinzhan.dev.platform.beans.dto.student.StudentImportRequestDto;
import com.kinzhan.dev.platform.beans.dto.student.StudentRemarkDto;
import com.kinzhan.dev.platform.beans.entity.StudentEntity;
import com.kinzhan.dev.platform.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author songsir
 * @since 2022-12-04
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {
    final StudentService service;

    @PostMapping
    public Object save(@RequestBody @Validated StudentDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        StudentEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
//        if (gradeUserService.lambdaQuery().eq(GradeUserEntity::getSid, id).exists()) {
//
//            throw new RuntimeException("学生已入班级，不可删除~");
//        }
        service.forceDelete(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();

        return R.ok("获取成功", service.getPageList(pageDto, wrapper));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);
        wrapper.orderByDesc("id");

        return R.ok("获取成功", service.list(wrapper));
    }

    @PostMapping(value = "/download")
    @ResponseBody
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        service.download(response, StudentExportDto.class, pageDto, null);
    }

    @PostMapping(value = "/import")
    @ResponseBody
    public Object importExcel(@RequestBody StudentImportRequestDto requestDto) throws IOException {
        log.info("request {}", requestDto);
        service.importExcel(requestDto);

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
        queryWrapper.like("nick_name", name);

        return R.ok("获取成功", service.list(queryWrapper));
    }


    @GetMapping("/valid/{phone}")
    @ResponseBody
    public Object findByPhone(@PathVariable("phone") String phone) {

        return R.ok("获取成功", service.findUserByPhone(phone));
    }
}
