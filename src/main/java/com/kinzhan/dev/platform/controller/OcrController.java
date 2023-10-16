package com.kinzhan.dev.platform.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.merchant.MerchantDto;
import com.kinzhan.dev.platform.beans.entity.MerchantEntity;
import com.kinzhan.dev.platform.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    private final MerchantService merchantService;

    private final WxMaService wxService;

    @PostMapping
    public Object save(@Validated @RequestBody MerchantDto dto){

        return null;
    }
}
