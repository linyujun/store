package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.entity.sys.SysDictEntity;
import com.mtstore.server.service.SysDictService;
import com.mtstore.server.util.tree.TreeBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-字典模块")
@RequestMapping("/dict")
public class MDictController {
    private final SysDictService sysDictService;

    @ApiOperation("返回所有字典和枚举值")
    @GetMapping({ "/list" })
    public Object list() {

        List<SysDictEntity> listDict = sysDictService.lambdaQuery().list();
        List<TreeBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            TreeBuilder.Node node = new TreeBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        log.info("dict {}", nodes);

        List<TreeBuilder.Node> result = new TreeBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }

    @GetMapping({ "/simple" })
    public Object simpleList() {
        List<SysDictEntity> listDict = sysDictService.list(null);

        return R.ok("获取成功", listDict);
    }

    @GetMapping({ "/root" })
    public Object getRoot() {
        List<SysDictEntity> listDict = sysDictService.findAllByParentId(0);

        return R.ok("获取成功", listDict);
    }

    @GetMapping({ "/parent/{parentId}" })
    public Object getParent(@PathVariable("parentId") Integer parentId) {
        List<SysDictEntity> listDict = sysDictService.findAllChildren(parentId);
        List<TreeBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            TreeBuilder.Node node = new TreeBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        log.info("dict {}", nodes);

        List<TreeBuilder.Node> result = new TreeBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }

    @GetMapping({ "/getChildren/{name}" })
    public Object getChildren(@PathVariable("name") String key) {
        List listDict = sysDictService.findAllByName(key);

        return R.ok("获取成功", listDict);
    }
}
