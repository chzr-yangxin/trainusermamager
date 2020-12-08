package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("y_task_run")
@Data
public class YTaskRunEntity {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;

    @TableField("taskid")
    private String taskid;

    @TableField("taskname")
    private String taskname;

    @TableField("tasksteps")
    private Integer tasksteps;

    @TableField("curstep")
    private Integer curstep;

    @TableField("taskstatus")
    private String taskstatus;

    @TableField("tasktype")
    private String tasktype;

    @TableField("ctime")
    private Date ctime;

    @TableField("ftime")
    private Date ftime;
}
