package com.example.demo.Interceptor;;


import com.example.demo.component.EncryptorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptorComponent encryptorComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Optional.ofNullable(request.getHeader("token"))
                .ifPresentOrElse(token -> {
                    Map map =encryptorComponent.decrypt(token);
                    request.setAttribute("user_id",map.get("user_id"));
                    Map map1 =encryptorComponent.decrypt(request.getHeader("role"));
                    request.setAttribute("role",map1.get("role"));
                    System.out.println(map1.get("role"));
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
                });

        return true;
    }
}
