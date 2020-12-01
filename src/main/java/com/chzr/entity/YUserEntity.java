package com.chzr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("y_user")
public class YUserEntity  {
    @TableId(value = "id", type= IdType.ASSIGN_UUID)
    private String id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("nickname")
    private String nickname;
    @TableField("salt")
    private String salt;
    @TableField("roleid")
    private String roleid;
    @TableField("ctime")
    private Date ctime;
}
