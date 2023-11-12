package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.entity.DiyNavEntity;
import com.mtstore.server.beans.entity.DiyPageEntity;
import com.mtstore.server.service.DiyNavService;
import com.mtstore.server.service.DiyPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-DIY装修模块")
@RequestMapping({ "/open/diy" })
public class NDiyController {
    final DiyNavService diyNavService;
    final DiyPageService diyPageService;

    @ApiOperation(value ="底部导航")
    @GetMapping("/nav")
    public Object getAllNav() {
        List<DiyNavEntity> resultList = diyNavService.lambdaQuery().eq(DiyNavEntity::getEnabled, true).list();

        return R.ok("获取成功", resultList);
    }

    @ApiOperation(value ="获取页面数据")
    @GetMapping("/page/{uuid}")
    public Object getPageData(@PathVariable("uuid") String uuid) {
        DiyPageEntity entity = diyPageService.getOne(diyPageService.lambdaQuery()
                .eq(DiyPageEntity::getEnabled, true)
                .eq(DiyPageEntity::getUuid, uuid)
                .getWrapper());

        return R.ok("获取成功", entity);
    }

    @ApiOperation(value ="获取默认页面数据")
    @GetMapping("/default/{page}")
    public Object getDefaultPage(@PathVariable("page") String page) {
        DiyPageEntity entity = diyPageService.getOne(diyPageService.lambdaQuery()
                .eq(DiyPageEntity::getEnabled, true)
                .eq(DiyPageEntity::getIsDefault, true)
                .eq(DiyPageEntity::getPageType, page)
                .getWrapper());

        return R.ok("获取成功", entity);
    }
}
