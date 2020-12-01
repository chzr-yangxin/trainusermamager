package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YPermissionEntity;
import com.chzr.mapper.YPermissionMapper;
import com.chzr.service.YPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YPermissionServiceImpl extends ServiceImpl<YPermissionMapper, YPermissionEntity> implements YPermissionService {

    @Autowired
    YPermissionMapper permissionMapper;

    @Override
    public List<YPermissionEntity> getPermsByRoleId(String roleid) {
        QueryWrapper<YPermissionEntity> query = new QueryWrapper<>();
        query.eq("roleid", roleid);
        return permissionMapper.selectList(query);
    }
}
