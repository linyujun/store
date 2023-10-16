package com.kinzhan.dev.platform.util.helper;

import com.alibaba.fastjson.JSON;
import com.kinzhan.dev.platform.beans.dto.user.UserPayloadDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;


@Component
@Data
public class JwtHelper {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiredTime}")
    private int expiredTime;

    /**
     * 解析jwt
     */
    public Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 构建jwt
     */
    public String createJWT(final UserPayloadDto userPayload) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Map claims = JSON.parseObject(JSON.toJSONString(userPayload), Map.class);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(signatureAlgorithm, jwtSecretKey);
        //添加Token过期时间
        if (expiredTime >= 0) {
            long expMillis = nowMillis + expiredTime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    /**
     * 构建jwt
     */
    public String createForeverJWT(Map userPayload) {
        LocalDateTime time = LocalDateTime.now();
        Date now = Date.from( time.atZone(ZoneId.systemDefault()).toInstant());
        //设置token过期时间99年
        Date before = Date.from( time.plus(99, ChronoUnit.YEARS).atZone(ZoneId.systemDefault()).toInstant());
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
       // Date now = new Date(nowMillis);
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(userPayload)
                .signWith(signatureAlgorithm, jwtSecretKey);
        //添加Token过期时间
        builder.setExpiration(before).setNotBefore(now);

        //生成JWT
        return builder.compact();
    }
}
