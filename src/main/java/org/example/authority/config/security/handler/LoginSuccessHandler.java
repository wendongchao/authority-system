package org.example.authority.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.jsonwebtoken.Jwts;
import org.example.authority.config.redis.RedisService;
import org.example.authority.entity.User;
import org.example.authority.utils.JwtUtils;
import org.example.authority.utils.LoginResult;
import org.example.authority.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author bingo
 * @description  登录认证成功处理器类
 * @date 2022-11-03
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置响应编码的格式
        response.setContentType("application/json;charset=UTF-8");
        // 获取当前登录用户信息
        User user = (User) authentication.getPrincipal();
        // 生成token
        String token = jwtUtils.generateToken(user);
        // 设置token签名秘钥及过期时间
        long expireTime = Jwts.parser()     // 获取DefaultJwtParser对象
                .setSigningKey(jwtUtils.getSecret())    // 设置签名的秘钥
                .parseClaimsJws(token.replace("jwt_",""))
                .getBody().getExpiration().getTime();   // 获取token过期时间
        // 创建登录结果对象
        LoginResult loginResult = new LoginResult(user.getId(), ResultCode.SUCCESS,token,expireTime);
        // 将对象转换成JSON格式，消除循环引用
        String result = JSON.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        //把生成的token存到redis
        String tokenKey = "token_"+token;
        redisService.set(tokenKey,token,jwtUtils.getExpiration() / 1000);
    }
}
