package com.mtstore.server.controller.system;

import cn.hutool.core.lang.Dict;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.system.PropertyDto;
import com.mtstore.server.beans.dto.system.PropertyMapDto;
import com.mtstore.server.service.SysPropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统属性配置
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/property")
public class SysPropertyController {

    final SysPropertyService sysPropertyService;

    @PostMapping
    public Object save(@RequestBody @Validated PropertyDto dto) {

        return R.ok("操作成功", sysPropertyService.save(dto));
    }

    @GetMapping({ "/list" })
    public Object getTreeList() {

        return R.ok("获取成功", sysPropertyService.list());
    }

    @GetMapping({ "/root" })
    public Object getRoot() {

        return R.ok("获取成功", sysPropertyService.findAllRoot());
    }

    @GetMapping({ "/prefix/{name}" })
    public Object getProperty(@PathVariable("name") String key) {
        Map property = sysPropertyService.findAllByGroup(key);

        return R.ok("获取成功", property);
    }

    /**
     * 获取配置列表MAP
     * @param group
     * @return
     */
    @GetMapping("/form/{group}")
    public Object getGroup(@PathVariable("group") String group) {
        Dict dict = Dict.create()
                .set("formConfig", sysPropertyService.getConfigMap(group))
                .set("formData", sysPropertyService.getFormData(group));

        return R.ok("成功", dict);
    }

    @PostMapping("/form/keys")
    public Object getKeys(@RequestBody String[] requestKeys) {
        List keys = Arrays.asList(requestKeys);
        Dict dict = Dict.create()
                .set("formConfig", sysPropertyService.getConfigMap(keys))
                .set("formData", sysPropertyService.getFormValue(keys));

        return R.ok("成功", dict);
    }


    @GetMapping({ "/getChildren/{parent_id}" })
    public Object getChildren(@PathVariable("parent_id") Integer parentId) {

        return R.ok("获取成功", sysPropertyService.findChildren(parentId));
    }

    @PostMapping("update")
    public Object update(@RequestBody @Validated PropertyMapDto dto) {
        sysPropertyService.update(dto);

        return R.ok("操作成功", dto);
    }

    @PostMapping("/update/keys")
    public Object updateKeys(@RequestBody HashMap configMap) {
        sysPropertyService.save(configMap);

        return R.ok("操作成功", true);
    }
}
