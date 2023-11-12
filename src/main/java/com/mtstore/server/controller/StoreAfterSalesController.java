package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.aftersales.StoreAfterSalesAuditDto;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.StoreAfterSalesEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreAfterSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* 售后服务
*/
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/storeAfterSales")
public class StoreAfterSalesController {

    private final StoreAfterSalesService storeAfterSalesService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreAfterSalesEntity entity = storeAfterSalesService.getById(id);

        return R.ok("获取成功", entity);
    }

    /**
     * 订单审核
     * @param dto
     * @return
     */
    @PostMapping("/audit")
    public Object audit(@Validated @RequestBody StoreAfterSalesAuditDto dto){
        storeAfterSalesService.audit(dto);
        log.info(LoggedUser.get().getUserId() + " 售后退款订单审核 {}" , dto);
        return R.ok("操作成功", true);
    }

    /**
     * 后台分页
     * @param pageDto
     * @return
     */
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", storeAfterSalesService.getPageList(pageDto, null));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeAfterSalesService.removeById(id);
        });
        log.info(LoggedUser.get().getUserId() + " 批量删除售后退款订单");
        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeAfterSalesService.removeById(id);
        log.info(LoggedUser.get().getUserId() + " 删除售后退款订单" + id);
        return R.ok("操作成功");
    }
}
