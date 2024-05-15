package com.surge.model.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AdminSensitiveVO {

    private Integer id;

    private String sensitives;

    private Date createdTime;

}
