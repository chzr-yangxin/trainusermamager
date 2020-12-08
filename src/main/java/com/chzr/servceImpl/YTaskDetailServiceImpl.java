package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YTaskDetailEntity;
import com.chzr.mapper.YTaskDetailMapper;
import com.chzr.service.YTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YTaskDetailServiceImpl extends ServiceImpl<YTaskDetailMapper, YTaskDetailEntity> implements YTaskDetailService {
    @Autowired
    private YTaskDetailMapper taskDetailMapper;

    @Override
    public List<YTaskDetailEntity> GetDetailByUserId(String userid) {
        QueryWrapper<YTaskDetailEntity> query = new QueryWrapper<>();
        query.eq("status", 0);
        query.eq("userid", userid);
        List<YTaskDetailEntity> details = taskDetailMapper.selectList(query);
        return details;
    }

    @Override
    public List<YTaskDetailEntity> GetDetailByRunId(String runid) {
        QueryWrapper<YTaskDetailEntity> query = new QueryWrapper<>();
        query.eq("runid", runid);
        query.orderByAsc("taskstep");
        List<YTaskDetailEntity> details = taskDetailMapper.selectList(query);
        return details;
    }
}
