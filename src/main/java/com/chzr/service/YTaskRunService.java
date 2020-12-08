package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YTaskRunEntity;

import java.util.List;
import java.util.Map;

public interface YTaskRunService extends IService<YTaskRunEntity> {
    public YTaskRunEntity GetRunningTask();

    public YTaskRunEntity GetLastFinishTask();

    public Map<String, Object> GetFinishTaskes(Integer pageindex, Integer pagesize);

}
