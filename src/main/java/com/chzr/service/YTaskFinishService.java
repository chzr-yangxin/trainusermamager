package com.chzr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chzr.entity.YTaskFinishEntity;

public interface YTaskFinishService extends IService<YTaskFinishEntity> {
    public YTaskFinishEntity GetLastFinishTask();

    public YTaskFinishEntity GetTaskByCheckId(String checkid);
}
