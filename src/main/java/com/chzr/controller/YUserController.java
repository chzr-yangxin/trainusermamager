package com.chzr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chzr.common.ResponseData;
import com.chzr.common.ResponseUtil;
import com.chzr.config.JWTUtil;
import com.chzr.entity.YRoleEntity;
import com.chzr.entity.YSessionEntity;
import com.chzr.entity.YUserEntity;
import com.chzr.service.YRoleService;
import com.chzr.service.YSessionService;
import com.chzr.service.YUserService;
import org.apache.coyote.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class YUserController {
    @Autowired
    private YUserService userService;
    @Autowired
    private YRoleService roleService;
    @Autowired
    private YSessionService sessionService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private ResponseData Login(@RequestParam String username, @RequestParam String password){
        YUserEntity user = new YUserEntity();
        user.setUsername(username);
        user.setPassword(password);

        ResponseData res = null;
        YUserEntity entity = userService.getUserByName(user.getUsername());
        if(entity != null){
            YSessionEntity exsess = sessionService.GetSessionByRoleId(entity.getRoleid());
            if(exsess != null && !exsess.getUserid().equals(entity.getId())){
                res = ResponseUtil.FAIL("该角色已经有人登录了！");
            }else if(entity.getPassword().equals(user.getPassword())){
                String token = JWTUtil.sign(user.getUsername(), user.getPassword());
                if(exsess == null && !username.equals("admin")) {
                    // 插入session
                    YSessionEntity sess = new YSessionEntity();
                    sess.setLogintype("userlogin");
                    sess.setUserid(entity.getId());
                    sess.setRoleid(entity.getRoleid());
                    sessionService.save(sess);
                }
                JSONObject obj = new JSONObject();
                obj.put("token", token);
                if(entity.getRoleid().equals("admin")) {
                    obj.put("page", "admin");
                }else{
                    obj.put("page", "user");
                }

                res = ResponseUtil.OK(obj);
            }else{
                res = ResponseUtil.FAIL("用户名密码错误！");
            }
        }else{
            res = ResponseUtil.FAIL("用户名密码错误！");
        }
        return res;
    }

    @RequestMapping(value = "/userlogout", method = RequestMethod.POST)
    private ResponseData UserLogout(){
        YUserEntity user = (YUserEntity)SecurityUtils.getSubject().getPrincipal();
        if(user != null) {
            sessionService.DeleteSessionByUserId(user.getId());
        }
        return ResponseUtil.OK(null);
    }

    @RequestMapping("/userinfo")
    private ResponseData GetUserInfo(){
        YUserEntity user = (YUserEntity)SecurityUtils.getSubject().getPrincipal();
        JSONObject obj = new JSONObject();
        obj.put("id", user.getId());
        obj.put("code", user.getUsercode());
        obj.put("username", user.getUsername());
        obj.put("nickname", user.getNickname());
        obj.put("sex", user.getSex());
        obj.put("phone", user.getPhone());
        YRoleEntity role = roleService.getById(user.getRoleid());
        obj.put("role", role);
        return ResponseUtil.OK(obj);
    }

    @RequestMapping("/users")
    private ResponseData GetUserList(@RequestParam(defaultValue = "1", name = "pageindex", required = false) Integer pageindex, @RequestParam(defaultValue = "10", name = "pagesize", required = false) Integer pagesize){
        Map<String, Object> users = userService.getUsersForPage(pageindex, pagesize);
        List<YUserEntity> entities = (List<YUserEntity>)users.get("users");

        JSONArray arr = new JSONArray();
        for(YUserEntity user: entities){
            JSONObject obj = new JSONObject();
            obj.put("id", user.getId());
            obj.put("code", user.getUsercode());
            obj.put("username", user.getUsername());
            obj.put("nickname", user.getNickname());
            obj.put("sex", user.getSex());
            obj.put("phone", user.getPhone());
            YRoleEntity role = roleService.getById(user.getRoleid());
            obj.put("role", role);
            arr.add(obj);
        }
        users.put("users", arr);
        return ResponseUtil.OK(users);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    private ResponseData AddUser(@RequestBody YUserEntity user){
        YUserEntity exUser = userService.getUserByName(user.getUsername());
        if(exUser != null){
            return ResponseUtil.FAIL("用户名已存在");
        }else{
            user.setCtime(new Date());
            userService.save(user);
            return ResponseUtil.OK(user);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    private ResponseData UpdateUser(@PathVariable String id, @RequestBody YUserEntity user){
        YUserEntity exuser = userService.getById(id);
        if(exuser == null){
            return ResponseUtil.FAIL("用户不存在");
        }else{
            YUserEntity exnameuser = userService.getUserByName(user.getUsername());
            if(exnameuser != null && !exnameuser.getId().equals(id)){
                return ResponseUtil.FAIL("用户名已存在");
            }else {
                user.setId(id);
                if(user.getPassword() == null || user.getPassword().equals("")){
                    user.setPassword(exuser.getPassword());
                }
                userService.updateById(user);
                return ResponseUtil.OK(user);
            }
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    private ResponseData DeleteUser(@PathVariable String id){
        userService.removeById(id);
        sessionService.DeleteSessionByUserId(id);
        return ResponseUtil.OK(null);
    }

    @RequestMapping("/401")
    private ResponseData noAuth(){
        return ResponseUtil.FAIL("没有权限");
    }
}
