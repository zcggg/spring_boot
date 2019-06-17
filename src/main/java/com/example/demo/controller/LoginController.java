package com.example.demo.controller;

import com.example.demo.component.EncryptorComponent;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String STUDENT_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
   @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    public User login(@RequestBody User user, HttpServletResponse response) {

        if(user.getUser_password()!="") {
            if (user.getUser_password().equals(userService.findUserByName(user.getUser_name()).getUser_password())) {
                //user.setUser_authority(userService.find(user.getUser_id()).getUser_authority());
                user=userService.findUserByName(user.getUser_name());
                Map map = Map.of("user_id", user.getUser_id());
                // 生成加密token
                String token = encryptorComponent.encrypt(map);
                // 在header创建自定义的权限
                Map map1=Map.of("role",user.getUser_authority());
                String role=encryptorComponent.encrypt(map1);
                response.setHeader("token", token);
                response.setHeader("role", role);
                return user;
            }
            else {
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "密码不正确");
            }
        }
        else
        {
           throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "密码为空");
        }
    }
   /* public User getUser(@RequestAttribute int user_id,@RequestBody User user){
        user=userService.getUser(user_id);
        return user;
    }*/

}