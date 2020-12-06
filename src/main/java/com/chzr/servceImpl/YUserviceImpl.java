package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YUserEntity;
import com.chzr.mapper.YUserMapper;
import com.chzr.service.YUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> getUsersForPage(int pageindex, int pagesize) {
        IPage<YUserEntity> userPage = new Page<>(pageindex, pagesize);
        userPage = userMapper.selectPage(userPage, null);
        Map<String, Object> res = new HashMap<>();
        res.put("total", userPage.getTotal());
        res.put("curpage", userPage.getCurrent());
        res.put("users", userPage.getRecords());
        return res;
    }
}
