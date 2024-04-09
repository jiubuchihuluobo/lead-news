package com.surge.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ad_user")
public class AdminUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("image")
    private String image;

    @TableField("phone")
    private String phone;

    @TableField("status")
    private Boolean status;

    @TableField("email")
    private String email;

    @TableField("login_time")
    private Date loginTime;

    @TableField("created_time")
    private Date createdTime;

}
