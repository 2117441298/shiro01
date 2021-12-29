package com.zking.controller;

import com.zking.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(User user) {
        //先获取主体对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        boolean f = true;
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            f = false;
        }
        if (f) {
            return "main";
        } else {
            return "login";
        }
    }

}
