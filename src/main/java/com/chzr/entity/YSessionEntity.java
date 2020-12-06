package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("y_session")
public class YSessionEntity {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;

    @TableField("userid")
    private String userid;

    @TableField("roleid")
    private String roleid;

    @TableField("logintype")
    private String logintype;
}
