package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("y_task")
public class YTaskEntity {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;

    @TableField("title")
    private String title;
}
