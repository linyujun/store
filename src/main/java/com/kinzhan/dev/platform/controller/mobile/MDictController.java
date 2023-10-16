package com.kinzhan.dev.platform.controller.mobile;

import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.entity.sys.SysDictEntity;
import com.kinzhan.dev.platform.service.SysDictService;
import com.kinzhan.dev.platform.util.tree.TreeBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-字典模块")
public class MDictController {
    private final SysDictService sysDictService;

    @ApiOperation("返回所有字典和枚举值")
    @GetMapping({ "/dict" })
    public Object list() {
//        List<SysDictEntity> listDict = sysDictService.lambdaQuery().eq(SysDictEntity::getEnabled,true).list();
        List<SysDictEntity> listDict = sysDictService.lambdaQuery().list();
        List<TreeBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            TreeBuilder.Node node = new TreeBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        log.info("dict {}", nodes);
//            return null;
        List<TreeBuilder.Node> result = new TreeBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }

    @GetMapping({ "/dict/simple" })
    public Object simpleList() {
        List<SysDictEntity> listDict = sysDictService.list(null);

        return R.ok("获取成功", listDict);
    }

    @GetMapping({ "/dict/root" })
    public Object getRoot() {
        List<SysDictEntity> listDict = sysDictService.findAllByParentId(0);

        return R.ok("获取成功", listDict);
    }

    @GetMapping({ "/dict/parent/{parentId}" })
    public Object getParent(@PathVariable("parentId") Integer parentId) {
        List<SysDictEntity> listDict = sysDictService.findAllChildren(parentId);
        List<TreeBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            TreeBuilder.Node node = new TreeBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        log.info("dict {}", nodes);
//            return null;
        List<TreeBuilder.Node> result = new TreeBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }

    @GetMapping({ "/dict/getChildren/{name}" })
    public Object getChildren(@PathVariable("name") String key) {
        List listDict = sysDictService.findAllByName(key);

        return R.ok("获取成功", listDict);
    }
}
