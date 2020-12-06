package com.chzr.servceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YTaskEntity;
import com.chzr.mapper.YTaskMapper;
import com.chzr.service.YTaskService;
import org.springframework.stereotype.Service;

@Service
public class YTaskServiceImpl extends ServiceImpl<YTaskMapper, YTaskEntity> implements YTaskService {
}
