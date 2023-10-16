package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawCancelDto;
import com.kinzhan.dev.platform.beans.dto.withdraw.WithdrawDto;
import com.kinzhan.dev.platform.beans.entity.WithdrawEntity;
import com.kinzhan.dev.platform.service.StudentService;
import com.kinzhan.dev.platform.service.UserService;
import com.kinzhan.dev.platform.service.WithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags="移动端-提现")
@RequestMapping("/app/withdraw")
public class MWithdrawController {

    final WithdrawService withdrawService;

    final UserService userService;

    @ApiOperation("提现申请-发起")
    @PostMapping("/apply")
    public Object save(@RequestBody @Validated WithdrawDto dto) {

        return R.ok("保存成功", withdrawService.saveOrUpdate(dto));
    }

    @ApiOperation("提现申请-记录分页")
    @PostMapping("/getPageList")
    public Object applyRecordList(@RequestBody PageDto pageDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", LoggedUser.get().getUserId());
        Page<WithdrawEntity> page = withdrawService.getPageList(pageDto, queryWrapper);

        return R.ok("获取成功", page);
    }

    @GetMapping("{id}")
    @ApiOperation("查看详情")
    public Object findOne(@PathVariable("id") Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", LoggedUser.get().getUserId());
        queryWrapper.eq("id", id);
        WithdrawEntity entity = withdrawService.getOne(queryWrapper, false);

        return R.ok("获取成功", entity);
    }

    @PostMapping("/cancel")
    @ApiOperation("取消")
    public Object cancel(@RequestBody @Validated WithdrawCancelDto dto) {
        Boolean result = withdrawService.cancel(dto);

        return R.ok("操作成功", result);
    }

}
