package com.zking.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Controller
public class IndexController {
/*isudbiussoido*/
    @Size(min = 1,max = 2)
    @RequestMapping("/")
    private String index(){
        return "login";
    }

    @RequiresPermissions("用户管理")
    @RequestMapping("/update")
    public String updateUser(){
        return "admin/updateUser";
    }

}
