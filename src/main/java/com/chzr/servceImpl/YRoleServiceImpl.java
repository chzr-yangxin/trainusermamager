package com.chzr.servceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chzr.entity.YRoleEntity;
import com.chzr.mapper.YRoleMapper;
import com.chzr.service.YRoleService;
import org.springframework.stereotype.Service;

@Service
public class YRoleServiceImpl extends ServiceImpl<YRoleMapper, YRoleEntity> implements YRoleService {
}
