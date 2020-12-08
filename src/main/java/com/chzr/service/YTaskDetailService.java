package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YTaskDetailEntity;

import java.util.List;

public interface YTaskDetailService extends IService<YTaskDetailEntity> {
    public List<YTaskDetailEntity> GetDetailByUserId(String userid);

    public List<YTaskDetailEntity> GetDetailByRunId(String runid);
}
