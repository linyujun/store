package com.mtstore.server.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.tenant.TenantDto;
import com.mtstore.server.beans.entity.sys.SysTenantEntity;
import com.mtstore.server.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author songsir
 * @since 2022-02-09
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenant")
public class SysTenantController {
    final TenantService service;

    @PostMapping
    public Object save(@RequestBody @Validated TenantDto dto) {
        Boolean result = service.save(dto);

        return R.ok("保存成功", result);
    }

    /**
     * 获取当前租户的机构信息
     * @return
     */
    @GetMapping("/current")
    public Object findCurrent() {
        SysTenantEntity entity = service.getById(LoggedUser.get().getTenantId());

        return R.ok("获取成功", entity);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        SysTenantEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        return R.ok("获取成功", service.getPageList(pageDto, queryWrapper));
    }

    @GetMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        return R.ok("获取成功", service
                .lambdaQuery()
                .eq(SysTenantEntity::getEnabled, true).list());
    }

    @GetMapping("search/{name}")
    @ResponseBody
    public Object search(@PathVariable("name") String name) {
        return R.ok("获取成功", service
                .lambdaQuery()
                .eq(SysTenantEntity::getEnabled,true)
                .like(SysTenantEntity::getAgentName, name).list());
    }
}
