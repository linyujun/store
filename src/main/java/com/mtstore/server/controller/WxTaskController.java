package com.mtstore.server.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.mtstore.server.beans.dto.wx.CompanyMpDto;
import com.mtstore.server.beans.dto.wx.PersonalMpDto;
import com.mtstore.server.service.WxThirdPartService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.wx.WxTaskDto;
import com.mtstore.server.beans.entity.WxTaskEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.WxTaskService;
import me.chanjar.weixin.open.bean.result.WxOpenRegisterPersonalWeappResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-05-23
*/
@RequiredArgsConstructor
@Api(tags = "应用管理管理")
@RestController
@RequestMapping("/api/wxTask")
public class WxTaskController {

    private final WxThirdPartService thirdPartService;

    private final WxTaskService wxTaskService;

    @GetMapping("{id}")
    @ApiOperation("查询应用管理")
    public Object getOne(@PathVariable("id") Integer id){
        WxTaskEntity entity = wxTaskService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping("/company")
    @ApiOperation("新增企业小程序")
    public Object createCompany(@Validated @RequestBody CompanyMpDto dto){
        WxOpenResult result = thirdPartService.createCompanyMp(dto);
        WxTaskDto taskDto = new WxTaskDto();
        taskDto.setPayload(convertToJSONObject(dto));
        taskDto.setAppType("company");
        wxTaskService.saveOrUpdate(taskDto);

        return R.ok("创建成功", result);
    }


    @PostMapping("/personal")
    @ApiOperation("新增个人小程序")
    public Object createPersonal(@Validated @RequestBody PersonalMpDto dto) {
        WxOpenRegisterPersonalWeappResult result = thirdPartService.createPersonalMp(dto);
        WxTaskDto taskDto = new WxTaskDto();
        taskDto.setPayload(convertToJSONObject(dto));
        taskDto.setAppType("personal");
        taskDto.setTaskId(result.getTaskid());
        taskDto.setAuthorizeUrl(result.getAuthorizeUrl());

        wxTaskService.saveOrUpdate(taskDto);

        return R.ok("创建成功", result);
    }


    @ApiOperation("分页查询应用管理")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", wxTaskService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            wxTaskService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        wxTaskService.removeById(id);

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
        wxTaskService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        wxTaskService.download(response, Map.class, pageDto, null);
    }

    private JSONObject convertToJSONObject(Object target) {
        return (JSONObject) JSONObject.toJSON(target);
    }
}
