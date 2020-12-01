package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YUserEntity;

public interface YUserService extends IService<YUserEntity> {
    public YUserEntity getUserByName(String username);
}
