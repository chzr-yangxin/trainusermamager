package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YTaskResultEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface YTaskResultService extends IService<YTaskResultEntity> {
    public Map<String, Object> GetTaskResultList(int pageindex, int pagesize);

    public List<YTaskResultEntity> GetTaskDetails(String checkid);

    public List<YTaskResultEntity> GetTaskResultByUserid(String userid);

    public List<YTaskResultEntity> GetRunningTask();

    public YTaskResultEntity GetLastFinishTask();
}
