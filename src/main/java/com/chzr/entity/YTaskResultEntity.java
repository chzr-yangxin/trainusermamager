package com.chzr.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("y_task_result")
public class YTaskResultEntity {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;

    @TableField("checkid")
    private String checkid;

    @TableField("checkname")
    private String checkname;

    @TableField("taskid")
    private String taskid;

    @TableField("userid")
    private String userid;

    @TableField("roleid")
    private String roleid;

    @TableField("status")
    private Integer status;

    @TableField("score")
    private Float score;

    @TableField("ctime")
    private Date ctime;
}
