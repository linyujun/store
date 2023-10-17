package com.mtstore.server.config.interceptor;

import com.mtstore.server.beans.annotion.AccessControl;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author songsir
 * 控制层，权限检查
 */
@Slf4j
public class AuthenticationInterceptor  extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod && null != LoggedUser.get()) {
            String role = LoggedUser.get().getRole();
            if ("SA".equalsIgnoreCase(role)) {
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            checkClass(handlerMethod, role);
            checkMethod(handlerMethod, role);
        }

        return true;
    }

    /**
     * 检查类级别
     */
    private void checkClass(HandlerMethod handler, String currentRole) {
        AccessControl accessControl = handler.getMethod().getDeclaringClass().getAnnotation(AccessControl.class);
//        log.info("类级别检查 {}", accessControl);
        if ( null != accessControl && accessControl.roles().length > 0) {
            String[] roles = accessControl.roles();
            if (null != roles && !Arrays.asList(roles).contains(currentRole)) {
                throw new RuntimeException("当前角色无权限访问该功能!");
            }
        }
    }

    /**
     * 检查方法级别
     */
    private void checkMethod(HandlerMethod handler, String currentRole) {
        AccessControl accessControl = handler.getMethod().getAnnotation(AccessControl.class);
//        log.info("方法级别检查 {}", accessControl);
        if ( null != accessControl && accessControl.roles().length > 0) {
            String[] roles = accessControl.roles();
            if (null != roles && !Arrays.asList(roles).contains(currentRole)) {
                throw new RuntimeException("当前角色无权限访问该功能!");
            }
        }
    }
}
