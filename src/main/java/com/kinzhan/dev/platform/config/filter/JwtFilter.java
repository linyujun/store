package com.kinzhan.dev.platform.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.user.UserPayloadDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * JWT的拦截器，拦截所有API请求
 * @author songsir
 */
@WebFilter(filterName = "jwtFilter", urlPatterns = {"/api/*", "/admin/*", "/app/*"})
@CrossOrigin
@Slf4j
public class JwtFilter implements Filter {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("JWTFilter Working");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        try {
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);

                chain.doFilter(req, res);
            } else {
                check(request, authHeader);
                chain.doFilter(req, res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            log.error("ERROR:{}", e.getMessage());
            R resp = new R(1401, e.getMessage(), false);
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(resp));
        } finally {
            LoggedUser.logOut();
        }
    }

    /**
     * 将jwt中的claims解析出来，加入到request中
     * @param req
     * @param authHeader
     * @throws ServletException
     */
    public void check(HttpServletRequest req, String authHeader) throws ServletException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("当前用户未登录！");
        }
        final String token = authHeader.substring(7);
        try {
            final Claims claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
            this.setRequestAttribute(req, claims);
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserPayloadDto userPayload = objectMapper.convertValue(claims, UserPayloadDto.class);
            LoggedUser.logIn(userPayload);
            log.info("userPayload, {}", userPayload);
        } catch (final SignatureException e) {
            throw new ServletException("非法token!");
        } catch (final ExpiredJwtException e) {
            e.printStackTrace();
            throw new ServletException("登陆已过期!");
        } catch (final RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setRequestAttribute(HttpServletRequest req, Claims claims) throws RuntimeException{
        for (String key : claims.keySet()) {
            req.setAttribute(key, claims.get(key));
        }
    }

    @Override
    public void destroy() {
        LoggedUser.logOut();
    }
}
