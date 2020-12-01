package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YPermissionEntity;

import java.util.List;

public interface YPermissionService extends IService<YPermissionEntity> {
    public List<YPermissionEntity> getPermsByRoleId(String id);
}
