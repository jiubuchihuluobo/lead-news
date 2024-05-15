package com.surge.model.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ad_channel")
public class AdminChannel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("is_default")
    private Boolean is_default;

    @TableField("status")
    private Boolean status;

    @TableField("ord")
    private Short ord;

    @TableField("created_time")
    private Date createdTime;

}
