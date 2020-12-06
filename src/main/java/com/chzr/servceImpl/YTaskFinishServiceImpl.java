package com.chzr.servceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YTaskFinishEntity;
import com.chzr.mapper.YTaskFinishMapper;
import com.chzr.service.YTaskFinishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YTaskFinishServiceImpl extends ServiceImpl<YTaskFinishMapper, YTaskFinishEntity> implements YTaskFinishService {
    @Autowired
    private YTaskFinishMapper taskFinishMapper;

    @Override
    public YTaskFinishEntity GetLastFinishTask() {
        QueryWrapper<YTaskFinishEntity> query = new QueryWrapper<>();
        query.orderByDesc("ctime");
        List<YTaskFinishEntity> ens = taskFinishMapper.selectList(query);
        if(ens != null && ens.size() > 0){
            return ens.get(0);
        }
        return null;
    }

    @Override
    public YTaskFinishEntity GetTaskByCheckId(String checkid) {
        QueryWrapper<YTaskFinishEntity> query = new QueryWrapper<>();
        query.eq("checkid", checkid);
        YTaskFinishEntity en = taskFinishMapper.selectOne(query);
        return en;
    }
}
