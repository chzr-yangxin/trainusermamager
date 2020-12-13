package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Override
    public Integer GetNextStep(String runnid, Integer curstep) {
        QueryWrapper<YTaskDetailEntity> query = new QueryWrapper<>();
        query.eq("runid", runnid);
        query.eq("iscomputer", 0);
        query.gt("taskstep", curstep);
        query.orderByAsc("taskstep");
        List<YTaskDetailEntity> details = taskDetailMapper.selectList(query);
        if(details.size() > 0){
            return details.get(0).getTaskstep();
        }
        return -1;
    }

    @Override
    public void UpdateStatusGtStep(String runnid, Integer curstep) {
        UpdateWrapper<YTaskDetailEntity> query = new UpdateWrapper<>();
        query.gt("taskstep", curstep);
        query.eq("runid", runnid);
        query.set("status", 1);
        YTaskDetailEntity d = new YTaskDetailEntity();
        d.setStatus(1);
        taskDetailMapper.update(d, query);
    }

    @Override
    public void UpdateStatusLtStep(String runnid, Integer curstep) {
        UpdateWrapper<YTaskDetailEntity> query = new UpdateWrapper<>();
        query.lt("taskstep", curstep);
        query.eq("runid", runnid);
        query.set("status", 1);
        YTaskDetailEntity d = new YTaskDetailEntity();
        d.setStatus(1);
        taskDetailMapper.update(d, query);
    }
}
