package com.example.demo.Interceptor;

import com.example.demo.component.EncryptorComponent;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptorComponent encryptorComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!User.ADMIN.equals(request.getAttribute("role"))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
        return  true;
    }
}
