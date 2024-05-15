package com.surge.model.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ap_user_real_name")
public class AppUserRealName {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("name")
    private String name;

    @TableField("id_number")
    private String idNumber;

    @TableField("front_image")
    private String frontImage;

    @TableField("back_image")
    private String backImage;

    @TableField("hold_image")
    private String holdImage;

    @TableField("live_image")
    private String liveImage;

    @TableField("status")
    private Short status;

    @TableField("reason")
    private String reason;

    @TableField("created_time")
    private Date createdTime;

    @TableField("submitted_time")
    private Date submittedTime;

    @TableField("updated_time")
    private Date updatedTime;

}
