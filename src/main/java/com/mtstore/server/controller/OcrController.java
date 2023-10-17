package com.mtstore.server.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.mtstore.server.beans.dto.merchant.MerchantDto;
import com.mtstore.server.service.MerchantService;
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
