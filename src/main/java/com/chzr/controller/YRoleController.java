package com.chzr.controller;

import com.chzr.common.ResponseData;
import com.chzr.common.ResponseUtil;
import com.chzr.entity.YRoleEntity;
import com.chzr.service.YRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YRoleController {
    @Autowired
    private YRoleService roleService;

    @RequestMapping("roles")
    private ResponseData GetRoles(){
        List<YRoleEntity> roles = roleService.list();
        return ResponseUtil.OK(roles);
    }
}
