package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YTaskDetailEntity;

import java.util.List;

public interface YTaskDetailService extends IService<YTaskDetailEntity> {
    public List<YTaskDetailEntity> GetDetailByUserId(String userid);

    public List<YTaskDetailEntity> GetDetailByRunId(String runid);

    public Integer GetNextStep(String runnid, Integer curstep);

    public void UpdateStatusGtStep(String runnid, Integer curstep);

    public void UpdateStatusLtStep(String runnid, Integer curstep);

    public void DeleteByRunid(String runid);
}
