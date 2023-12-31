package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.feedback.FeedbackDto;
import com.mtstore.server.beans.dto.feedback.ReplyDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.FeedbackEntity;
import com.mtstore.server.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 意见反馈
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {
    final FeedbackService service;

    @PostMapping
    public Object save(@RequestBody @Validated FeedbackDto dto) {
        service.saveOrUpdate(dto);
        log.info(LoggedUser.get().getUserId() + " 意见反馈：{}", dto);
        return R.ok("成功", true);
    }

    @PostMapping("/reply")
    public Object reply(@RequestBody @Validated ReplyDto replyDto) {
        service.doReplay(replyDto);

        return R.ok("操作成功");
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        FeedbackEntity entity = service.getById(id);

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
}
