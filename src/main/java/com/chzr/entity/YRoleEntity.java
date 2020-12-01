package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("y_role")
@Data
public class YRoleEntity {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("name")
    private String name;
    @TableField("description")
    private String description;
}
