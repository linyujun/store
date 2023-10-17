package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户账户余额表
 * </p>
 *
 * @author songsir
 * @since 2022-04-06
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/balance")
public class UserBalanceController {
    final UserBalanceService service;

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", service.getPageList(pageDto, null));
    }
}
