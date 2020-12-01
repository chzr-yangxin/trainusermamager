package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YUserEntity;
import com.chzr.mapper.YUserMapper;
import com.chzr.service.YUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YUserviceImpl extends ServiceImpl<YUserMapper, YUserEntity> implements YUserService {

    @Autowired
    YUserMapper userMapper;

    @Override
    public YUserEntity getUserByName(String username) {
        QueryWrapper<YUserEntity> query = new QueryWrapper<>();
        query.eq("username", username);
        YUserEntity entity = userMapper.selectOne(query);
        return entity;
    }
}
