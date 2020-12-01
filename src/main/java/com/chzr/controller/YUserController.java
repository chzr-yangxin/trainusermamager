package com.chzr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chzr.common.ResponseData;
import com.chzr.common.ResponseUtil;
import com.chzr.config.JWTUtil;
import com.chzr.entity.YUserEntity;
import com.chzr.service.YUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class YUserController {
    @Autowired
    private YUserService userService;

    @RequestMapping("/login")
    private ResponseData Login(){
        YUserEntity user = new YUserEntity();
        user.setUsername("yx");
        user.setPassword("123456");

        ResponseData res = new ResponseData();

        YUserEntity entity = userService.getUserByName(user.getUsername());
        if(entity != null && entity.getPassword().equals(user.getPassword())){
            String token = JWTUtil.sign(user.getUsername(), user.getPassword());
            res = ResponseUtil.OK(token);
        }else{
            res = ResponseUtil.FAIL("用户名密码错误！");
        }
        return res;
    }

    @RequestMapping("/add")
    private String AddUser(){
        YUserEntity user = new YUserEntity();
        user.setUsername("yx");
        user.setPassword("123456");
        user.setNickname("杨业");
        user.setCtime(new Date());
        user.setSalt("salt");
        //userService.save(user);
        return "登录成功！";
    }

    @RequestMapping("/401")
    private ResponseData noAuth(){
        return ResponseUtil.FAIL("没有权限");
    }
}
