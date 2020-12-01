package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("y_permission")
public class YPermissionEntity {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("roleid")
    private String roleid;
    @TableField("perm")
    private String perm;
}
