package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.user.UserLevelDto;
import com.mtstore.server.beans.entity.UserLevelEntity;
import com.mtstore.server.service.UserLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* 用户等级
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
