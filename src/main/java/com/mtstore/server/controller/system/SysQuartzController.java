package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.sys.SysQuartzJob;
import com.mtstore.server.service.QuartzJobService;
import com.mtstore.server.service.QuartzLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@RestController
//@Api(tags = "系统:定时任务管理")
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class SysQuartzController {

    private final QuartzJobService quartzJobService;

    private final QuartzLogService quartzLogService;


    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", quartzJobService.getPageList(pageDto, null));
    }

    @PostMapping("/logs/getPageList")
    public Object getLogs(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", quartzLogService.getPageList(pageDto, null));
    }

    @PostMapping
    public Object save(@RequestBody @Validated SysQuartzJob dto) {
        Boolean result = quartzJobService.saveOrUpdate(dto);

        return R.ok("保存成功", result);
    }

    @GetMapping(value = "/{id}")
    public Object getOne(@PathVariable Long id){
        SysQuartzJob entity = quartzJobService.getOne(new LambdaQueryWrapper<SysQuartzJob>()
                .eq(SysQuartzJob::getId,id));

        return R.ok("获取成功", entity);
    }

    @PutMapping(value = "/{id}")
    public Object updateIsPause(@PathVariable Long id){
        quartzJobService.updateIsPause(quartzJobService.getOne(new LambdaQueryWrapper<SysQuartzJob>()
                .eq(SysQuartzJob::getId,id)));

        return R.ok("操作成功");
    }

    @PutMapping(value = "/exec/{id}")
    public Object execution(@PathVariable Long id){
        quartzJobService.execution(quartzJobService.getOne(new LambdaQueryWrapper<SysQuartzJob>().eq(SysQuartzJob::getId,id)));

        return R.ok("操作成功");
    }

    @DeleteMapping
    public Object delete(@RequestBody Integer[] ids){
        quartzJobService.removeByIds(new ArrayList<>(Arrays.asList(ids)));

        return R.ok("操作成功");
    }
}
