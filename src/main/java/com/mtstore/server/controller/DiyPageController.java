package com.mtstore.server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.mtstore.server.beans.dto.diy.DiyPageDto;
import com.mtstore.server.util.ImageColorReplaceUtil;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.DiyPageEntity;
import com.mtstore.server.service.DiyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author songsir
* @date 2023-04-17
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diy/page")
public class DiyPageController {

    private final DiyPageService diyPageService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        DiyPageEntity entity = diyPageService.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/copy/{id}")
    public Object copyOne(@PathVariable("id") Integer id){
        diyPageService.copyOne(id);

        return R.ok("操作成功", true);
    }

    @GetMapping("/convert/{color}")
    public Object convert(@PathVariable("color") String color){
        List<String> resultList = null;
        try {
            resultList = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                ClassPathResource classPathResource = new ClassPathResource(String.format("theme/%d.png", i));
                InputStream inputStream = classPathResource.getInputStream();
//                final String imgUrl = String.format("https://kmp-oss.oss-cn-hangzhou.aliyuncs.com/shieldImg/theme/%d.png", i);
                final String base64Img = ImageColorReplaceUtil.convert(inputStream, String.format("#%s", color));

                resultList.add("data:image/png;base64," + base64Img);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.ok("操作成功", resultList);
    }


    @PostMapping
    public Object save(@Validated @RequestBody DiyPageDto dto){
        diyPageService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", diyPageService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            diyPageService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/default/{id}")
    public Object setDefault(@PathVariable("id") Integer id) {
        Optional.ofNullable(diyPageService.getById(id)).ifPresent(v -> {
            if (v.getIsDefault()) throw new RuntimeException("请至少保留一个默认页面");
        });
        diyPageService.setDefault(id);

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
        Optional.ofNullable(diyPageService.getById(id)).ifPresent(v -> {
            if (v.getIsDefault()) throw new RuntimeException("默认页面不可禁用");
        });
        diyPageService.disable(id);
        return R.ok("操作成功", true);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        diyPageService.removeById(id);

        return R.ok("操作成功");
    }
}
