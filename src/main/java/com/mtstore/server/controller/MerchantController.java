package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.merchant.MerchantDto;
import com.mtstore.server.beans.entity.MerchantEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.MerchantService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* 商家
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        MerchantEntity entity = merchantService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody MerchantDto dto){
        merchantService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", merchantService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            merchantService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        merchantService.removeById(id);

        return R.ok("操作成功");
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        merchantService.download(response, Map.class, pageDto, null);
    }
}
