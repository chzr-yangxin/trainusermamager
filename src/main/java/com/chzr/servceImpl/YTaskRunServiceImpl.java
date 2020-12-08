package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YTaskRunEntity;
import com.chzr.mapper.YTaskRunMapper;
import com.chzr.service.YTaskRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YTaskRunServiceImpl extends ServiceImpl<YTaskRunMapper, YTaskRunEntity> implements YTaskRunService {

    @Autowired
    private YTaskRunMapper taskRunMapper;

    @Override
    public YTaskRunEntity GetRunningTask() {
        QueryWrapper<YTaskRunEntity> query = new QueryWrapper<>();
        query.eq("taskstatus", "进行中");
        List<YTaskRunEntity> entity = taskRunMapper.selectList(query);
        if(entity.size() > 0){
            return entity.get(0);
        }
        return null;
    }

    @Override
    public YTaskRunEntity GetLastFinishTask() {
        QueryWrapper<YTaskRunEntity> query = new QueryWrapper<>();
        query.eq("taskstatus", "已完成");
        query.orderByDesc("ftime");
        List<YTaskRunEntity> entities = taskRunMapper.selectList(query);
        if(entities.size() > 0){
            return entities.get(0);
        }
        return null;
    }

    @Override
    public Map<String, Object> GetFinishTaskes(Integer pageindex, Integer pagesize) {
        IPage<YTaskRunEntity> resultPage = new Page<>(pageindex, pagesize);
        QueryWrapper<YTaskRunEntity> query = new QueryWrapper<>();
        query.eq("taskstatus", "已完成");
        query.orderByDesc("ftime");
        resultPage = taskRunMapper.selectPage(resultPage, query);
        Map<String, Object> res = new HashMap<>();
        res.put("total", resultPage.getTotal());
        res.put("curpage", resultPage.getCurrent());
        res.put("results", resultPage.getRecords());
        return res;
    }
}
