package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("y_task_detail")
@Data
public class YTaskDetailEntity {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;

    @TableField("runid")
    private String runid;

    @TableField("iscomputer")
    private Integer iscomputer;

    @TableField("roleid")
    private String roleid;

    @TableField("userid")
    private String userid;

    @TableField("taskstep")
    private Integer taskstep;

    @TableField("status")
    private Integer status;

    @TableField("score")
    private Float score;

    @TableField("ctime")
    private Date ctime;
}
