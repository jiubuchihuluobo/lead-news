package com.surge.model.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ap_user")
public class AppUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("salt")
    private String salt;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("image")
    private String image;

    @TableField("sex")
    private Boolean sex;

    @TableField("is_certification")
    private Boolean isCertification;

    @TableField("is_identity_authentication")
    private Boolean isIdentityAuthentication;

    @TableField("status")
    private Boolean status;

    @TableField("flag")
    private Boolean flag;

    @TableField("created_time")
    private Date createdTime;

}
