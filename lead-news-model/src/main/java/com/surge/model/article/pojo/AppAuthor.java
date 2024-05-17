package com.surge.model.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("ap_author")
@Data
public class AppAuthor {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("type")
    private Short type;

    @TableField("userId")
    private Integer userId;

    @TableField("created_time")
    private Date createdTime;

    @TableField("wm_user_id")
    private Integer wmUserId;

}
