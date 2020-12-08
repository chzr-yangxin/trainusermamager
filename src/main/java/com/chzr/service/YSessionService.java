package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YSessionEntity;

public interface YSessionService extends IService<YSessionEntity> {
    public YSessionEntity GetSessionByRoleId(String roleid);
    public void DeleteSessionByUserId(String userid);
    public void DeleteComputerSessions();
    public void DeleteComputerSessionByRoleid(String roleid);
}
