package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.entity.UserLevelEntity;
import com.mtstore.server.service.UserLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@Api(tags = "移动端-用户-会员等级")
@RestController
@RequestMapping("/app/user/level")
public class MUserLevelController {

    private final UserLevelService userLevelService;

    @ApiOperation("查询普通会员级别")
    @GetMapping("/list")
    public Object getAll() {
        List<UserLevelEntity> resultList = userLevelService.lambdaQuery()
                .eq(UserLevelEntity::getIsVip, false)
                .eq(UserLevelEntity::getEnabled, true)
                .orderByAsc(UserLevelEntity::getSort)
                .list();

        return R.ok("获取成功", resultList);
    }

    @ApiOperation("查询VIP会员级别")
    @GetMapping("/vip/list")
    public Object getVipAll() {
        List<UserLevelEntity> resultList = userLevelService.lambdaQuery()
                .eq(UserLevelEntity::getIsVip, true)
                .eq(UserLevelEntity::getEnabled, true)
                .orderByAsc(UserLevelEntity::getSort)
                .list();

        return R.ok("获取成功", resultList);
    }
}
