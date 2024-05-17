package com.surge.model.media.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("wm_user")
public class MediaUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("ap_user_id")
    private Integer appUserId;

    @TableField("ap_author_id")
    private Integer appAuthorId;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("nickname")
    private String nickname;

    @TableField("image")
    private String image;

    @TableField("location")
    private String location;

    @TableField("phone")
    private String phone;

    @TableField("status")
    private String status;

    @TableField("email")
    private String email;

    @TableField("type")
    private Short type;

    @TableField("score")
    private Short score;

    @TableField("login_time")
    private Date loginTime;

    @TableField("created_time")
    private Date createdTime;

}
