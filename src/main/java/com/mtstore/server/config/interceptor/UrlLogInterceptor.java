package com.mtstore.server.config.interceptor;

import com.mtstore.server.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UrlLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try {
            String ip = request.getHeader("X-Real-IP");
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
            }
            log.info(ip + " -> " + request.getRequestURI());
        } catch (Exception e) {
            log.error("preHandle error : " + e.getMessage());
        }
        if (request.getMethod().equals("OPTIONS")) {
            return false;
        }
        return true;
    }
}
