package com.kinzhan.dev.platform.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.dto.ImportRequestDto;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.system.DeptDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysDeptEntity;
import com.kinzhan.dev.platform.beans.entity.sys.SysDictEntity;
import com.kinzhan.dev.platform.service.SysDeptService;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.util.tree.DeptBuilder;
import com.kinzhan.dev.platform.util.tree.TreeBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织架构
 * </p>
 *
 * @author songsir
 * @since 2023-03-03
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/dept")
public class SysDeptController {
    final SysDeptService service;

    @PostMapping
    public Object save(@RequestBody @Validated DeptDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        SysDeptEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        if (service.lambdaQuery().eq(SysDeptEntity::getParentId, id).exists()) {
            throw new RuntimeException("当前节点下尚有子节点，不可删除");
        }
        service.forceDelete(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", service.getPageList(pageDto, null));
    }


    @GetMapping("/children/{id}")
    @ResponseBody
    public Object getChildren(@PathVariable("id") Integer id) {
        QueryWrapper<SysDeptEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysDeptEntity::getEnabled, true);
        wrapper.lambda().eq(SysDeptEntity::getParentId, id);

        return R.ok("获取成功", service.list(wrapper));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<SysDeptEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }


    @GetMapping({ "/tree" })
    public Object getTree() {
        List<SysDeptEntity> listDict = service.lambdaQuery().list();
        List<DeptBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            DeptBuilder.Node node = new DeptBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        List<DeptBuilder.Node> result = new DeptBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }

    /**
    * 导出
    */
    @PostMapping(value = "/download")
    @ResponseBody
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {
    }

    /**
    * 导入
    */
    @PostMapping(value = "/import")
    @ResponseBody
    public Object importExcel(@RequestBody ImportRequestDto requestDto) throws IOException {
        log.info("request {}", requestDto);

        return R.ok("成功");
    }

    /**
     * 搜索自动完成
     * @param name
     * @return
     */
    @GetMapping("/search/{name}")
    public Object search(@PathVariable("name") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();

        return R.ok("获取成功", service.list(queryWrapper));
    }
}
