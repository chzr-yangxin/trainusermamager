package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YTaskResultEntity;
import com.chzr.mapper.YTaskResultMapper;
import com.chzr.service.YTaskResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YTaskResultServiceImpl extends ServiceImpl<YTaskResultMapper, YTaskResultEntity> implements YTaskResultService {
    @Autowired
    private YTaskResultMapper taskResultMapper;

    @Override
    public Map<String, Object> GetTaskResultList(int pageindex, int pagesize) {
        IPage<YTaskResultEntity> resultPage = new Page<>(pageindex, pagesize);
        QueryWrapper<YTaskResultEntity> query = new QueryWrapper<>();
        query.groupBy("checkid");
        resultPage = taskResultMapper.selectPage(resultPage, query);
        Map<String, Object> res = new HashMap<>();
        res.put("total", resultPage.getTotal());
        res.put("curpage", resultPage.getCurrent());
        res.put("results", resultPage.getRecords());
        return res;
    }

    @Override
    public List<YTaskResultEntity> GetTaskDetails(String checkid) {
        QueryWrapper<YTaskResultEntity> query = new QueryWrapper<>();
        query.eq("checkid", checkid);
        List<YTaskResultEntity> details = taskResultMapper.selectList(query);
        return details;
    }

    @Override
    public List<YTaskResultEntity> GetTaskResultByUserid(String userid) {
        QueryWrapper<YTaskResultEntity> query = new QueryWrapper<>();
        query.eq("userid", userid);
        query.eq("status", 0);
        List<YTaskResultEntity> details = taskResultMapper.selectList(query);
        return details;
    }

    @Override
    public List<YTaskResultEntity> GetRunningTask() {
        QueryWrapper<YTaskResultEntity> query = new QueryWrapper<>();
        query.eq("status", 0);
        List<YTaskResultEntity> details = taskResultMapper.selectList(query);
        return details;
    }

    @Override
    public YTaskResultEntity GetLastFinishTask() {
        QueryWrapper<YTaskResultEntity> query = new QueryWrapper<>();
        query.groupBy("checkid");
        query.eq("status", 1);
        query.orderByDesc("ctime");
        YTaskResultEntity en = taskResultMapper.selectOne(query);
        return en;
    }

}
