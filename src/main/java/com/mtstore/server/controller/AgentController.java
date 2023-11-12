package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.dto.agent.AgentDto;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.AgentEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.AgentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
*代理商
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        AgentEntity entity = agentService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody AgentDto dto){
        agentService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", agentService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            agentService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        agentService.removeById(id);

        return R.ok("操作成功");
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        agentService.download(response, Map.class, pageDto, null);
    }
}
