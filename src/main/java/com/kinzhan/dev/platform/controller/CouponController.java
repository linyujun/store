package com.kinzhan.dev.platform.controller;

import java.util.*;

import com.kinzhan.dev.platform.beans.dto.mall.coupon.SendCouponDto;
import com.kinzhan.dev.platform.beans.dto.user.UserCouponDto;
import com.kinzhan.dev.platform.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.CouponEntity;
import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponDto;
import com.kinzhan.dev.platform.service.CouponService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-05-06
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeCoupon")
public class CouponController {

    private final CouponService couponService;
    private final UserCouponService userCouponService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        CouponEntity entity = couponService.getDetail(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody CouponDto dto){
        couponService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    /**
     * 定向发送优惠券
     * @param dto
     * @return
     */
    @PostMapping("/send")
    public Object send(@Validated @RequestBody SendCouponDto dto){
        couponService.send(dto.getCouponId(), dto.getNum(), dto.getUserIds());

        return R.ok("操作成功", true);
    }

    /**
     * 系统优惠券分页
     * @param pageDto
     * @return
     */
    @PostMapping("/getPageList")
    public Object getPageList(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", couponService.getPageList(pageDto, null));
    }

    /**
     * 获取可用的优惠券
     * @return
     */
    @GetMapping("/getAll")
    public Object getAll() {
        List<CouponEntity> resultList = couponService.lambdaQuery()
                .eq(CouponEntity::getStatus, 1)
                .eq(CouponEntity::getEnabled, true).list();

        return R.ok("获取成功", resultList);
    }

    /**
     * 用户持有的优惠券分页
     * @param pageDto
     * @return
     */
    @PostMapping("/user/getPageList")
    public Object getUserCouponPage(@RequestBody PageDto pageDto) {

        return R.ok("获取成功", userCouponService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            couponService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        couponService.removeById(id);

        return R.ok("操作成功");
    }

    /**
    * 禁用，启用
    * @param id
    * @return
    */
    @GetMapping("/disable/{id}")
    @ResponseBody
    public Object disable(@PathVariable("id") Integer id) {
        couponService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        couponService.download(response, Map.class, pageDto, null);
    }
}
