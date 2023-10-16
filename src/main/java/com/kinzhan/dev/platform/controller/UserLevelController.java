package com.kinzhan.dev.platform.controller;

import java.util.*;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.UserLevelEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserLevelDto;
import com.kinzhan.dev.platform.service.UserLevelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-04-25
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/level")
public class UserLevelController {

    private final UserLevelService userLevelService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        UserLevelEntity entity = userLevelService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody UserLevelDto dto){
        userLevelService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", userLevelService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            userLevelService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        userLevelService.removeById(id);

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
        userLevelService.disable(id);
        return R.ok("操作成功", true);
    }

    /**
     * 复制一条
     * @param id
     * @return
     */
    @GetMapping("/copy/{id}")
    @ResponseBody
    public Object copyOne(@PathVariable("id") Integer id) {
        userLevelService.copyOne(id);

        return R.ok("操作成功", true);
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        userLevelService.download(response, Map.class, pageDto, null);
    }
}
