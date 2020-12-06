package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YUserEntity;

import java.util.Map;

public interface YUserService extends IService<YUserEntity> {
    public YUserEntity getUserByName(String username);
    public Map<String, Object> getUsersForPage(int pageindex, int pagesize);
}
