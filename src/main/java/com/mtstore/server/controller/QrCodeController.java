package com.mtstore.server.controller;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.code.MpQrCodeDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrCode")
@CrossOrigin
public class QrCodeController {

    final WxMaService wxService;

    @PostMapping("/preview")
    @ApiOperation("生成预览二维码")
    public Object getProductsQrCode(@Validated @RequestBody MpQrCodeDto dto) {
        try {
            WxMaQrcodeService qrCodeService = wxService.getQrcodeService();
            Map<String, Object> requestMap = Optional.ofNullable(dto.getParams()).orElse(new HashMap<>());
            byte[] data = qrCodeService.createWxaCodeUnlimitBytes(convertToQuery(requestMap),
                    dto.getPage(),
                    dto.getEnvVersion().equals("release"), dto.getEnvVersion(), dto.getWidth(), true, null, false);

            return R.ok("获取成功", encodeFileToBase64Binary(data));
        } catch (WxErrorException e) {
            e.printStackTrace();

            return R.ok("生成失败");
        }
    }

    private String encodeFileToBase64Binary(byte[] data){

        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(data);
    }

    /**
     * Map转成QueryString
     * @param requestParams
     * @return
     */
    private String convertToQuery(Map<String, Object> requestParams) {
        return requestParams
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}
