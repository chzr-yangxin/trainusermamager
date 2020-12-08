package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YSessionEntity;
import com.chzr.mapper.YSessionMapper;
import com.chzr.service.YSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YSessionServiceImpl extends ServiceImpl<YSessionMapper, YSessionEntity> implements YSessionService {
    @Autowired
    private YSessionMapper sessionMapper;

    @Override
    public YSessionEntity GetSessionByRoleId(String roleid) {
        QueryWrapper<YSessionEntity> query = new QueryWrapper<>();
        query.eq("roleid", roleid);
        YSessionEntity entity = sessionMapper.selectOne(query);
        return entity;
    }

    @Override
    public void DeleteSessionByUserId(String userid) {
        QueryWrapper<YSessionEntity> query = new QueryWrapper<>();
        query.eq("userid", userid);
        sessionMapper.delete(query);
    }

    @Override
    public void DeleteComputerSessions() {
        QueryWrapper<YSessionEntity> query = new QueryWrapper<>();
        query.eq("logintype", "computerlogin");
        sessionMapper.delete(query);
    }

    @Override
    public void DeleteComputerSessionByRoleid(String roleid) {
        QueryWrapper<YSessionEntity> query = new QueryWrapper<>();
        query.eq("logintype", "computerlogin");
        query.eq("roleid", roleid);
        sessionMapper.delete(query);
    }
}
