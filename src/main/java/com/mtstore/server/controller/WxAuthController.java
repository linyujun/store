package com.mtstore.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/wx")
@RequiredArgsConstructor
public class WxAuthController {
    final WxOpenService wxOpenService;

    @GetMapping("/auth/goto_auth_url_show")
    @ResponseBody
    public String gotoPreAuthUrlShow(){
        return "<a href='goto_auth_url'>go</a>";
    }

    @GetMapping("/auth/goto_auth_url")
    public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response){
        String host = request.getHeader("host");
        String url = "http://"+host+"/wx/auth/jump";
        try {
            url = wxOpenService.getWxOpenComponentService().getPreAuthUrl(url);
            // 添加来源，解决302跳转来源丢失的问题
            response.addHeader("Referer", "http://"+host);
            response.sendRedirect(url);
        } catch (WxErrorException | IOException e) {
            log.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/auth/jump")
    @ResponseBody
    public WxOpenQueryAuthResult jump(@RequestParam("auth_code") String authorizationCode){
        try {
            WxOpenQueryAuthResult queryAuthResult = wxOpenService.getWxOpenComponentService().getQueryAuth(authorizationCode);
            log.info("getQueryAuth", queryAuthResult);
            return queryAuthResult;
        } catch (WxErrorException e) {
            log.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/get_authorizer_info")
    @ResponseBody
    public WxOpenAuthorizerInfoResult getAuthorizerInfo(@RequestParam String appId){
        try {
            return wxOpenService.getWxOpenComponentService().getAuthorizerInfo(appId);
        } catch (WxErrorException e) {
            log.error("getAuthorizerInfo", e);
            throw new RuntimeException(e);
        }
    }
}
