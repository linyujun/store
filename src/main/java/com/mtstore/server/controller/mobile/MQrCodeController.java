package com.mtstore.server.controller.mobile;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.code.MpQrCodeDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import io.swagger.annotations.Api;
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
@RequestMapping("/app/qrCode")
@CrossOrigin
@Api(tags="移动端-二维码模块")
public class MQrCodeController {

    final WxMaService wxService;

    @PostMapping("/createProduct")
    @ApiOperation("生成商品页二维码")
    public Object getProductsQrCode(@Validated @RequestBody MpQrCodeDto dto) {
        try {
            WxMaQrcodeService qrCodeService = wxService.getQrcodeService();
            Map<String, Object> requestMap = Optional.ofNullable(dto.getParams()).orElse(new HashMap<>());
            requestMap.put("inviteId", LoggedUser.get().getUserId());
            byte[] data = qrCodeService.createWxaCodeUnlimitBytes(convertToQuery(requestMap),
                    dto.getPage(),
                    dto.getEnvVersion().equals("release"), dto.getEnvVersion(), dto.getWidth(), true, null, false);

            return R.ok("获取成功", encodeFileToBase64Binary(data));
        } catch (WxErrorException e) {
            e.printStackTrace();

            return R.ok("生成失败");
        }
    }

    @PostMapping("/createUser")
    @ApiOperation("生成用户二维码")
    public Object getUserQrCode(@Validated @RequestBody MpQrCodeDto dto) {
        try {
            WxMaQrcodeService qrCodeService = wxService.getQrcodeService();
            Map<String, Object> requestMap = Optional.ofNullable(dto.getParams()).orElse(new HashMap<>());
            requestMap.put("inviteId", LoggedUser.get().getUserId());
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
