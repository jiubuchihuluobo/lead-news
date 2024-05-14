package com.surge.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ad_sensitive")
public class AdminSensitive {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("sensitives")
    private String sensitives;

    @TableField("created_time")
    private Date createdTime;

}
