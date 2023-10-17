package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.push.PushDto;
import com.mtstore.server.beans.entity.sys.SysPushEntity;
import com.mtstore.server.service.SysPushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2023-02-02
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/push")
public class SysPushController {
    final SysPushService service;

    @PostMapping
    public Object save(@RequestBody @Validated PushDto dto) {
        Boolean result = service.save(dto);

        return R.ok("操作成功", result);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        SysPushEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", service.getPageList(pageDto, null));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<SysPushEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }
}
