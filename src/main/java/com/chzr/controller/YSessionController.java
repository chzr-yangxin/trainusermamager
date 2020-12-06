package com.chzr.controller;

import com.chzr.common.ResponseData;
import com.chzr.common.ResponseUtil;
import com.chzr.entity.YRoleEntity;
import com.chzr.entity.YSessionEntity;
import com.chzr.service.YRoleService;
import com.chzr.service.YSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YSessionController {

    @Autowired
    private YSessionService sessionService;
    @Autowired
    private YRoleService roleService;

    @RequestMapping("/session")
    private ResponseData GetSessions(){
        List<YSessionEntity> sesses = sessionService.list();
        return ResponseUtil.OK(sesses);
    }

    @RequestMapping("/computer/{roleid}")
    private ResponseData SetComputer(@PathVariable String roleid){
        YRoleEntity role = roleService.getById(roleid);
        ResponseData res = null;
        if(role != null){
            YSessionEntity exsession = sessionService.GetSessionByRoleId(roleid);
            if(exsession != null){
                res = ResponseUtil.FAIL("该角色已经有人登录了！");
            }else{
                // 插入session
                YSessionEntity sess = new YSessionEntity();
                sess.setLogintype("computerlogin");
                sess.setUserid("--");
                sess.setRoleid(roleid);
                sessionService.save(sess);
                res = ResponseUtil.OK(null);
            }
        }else{
            res = ResponseUtil.FAIL("角色不存在！");
        }
        return res;
    }
}
