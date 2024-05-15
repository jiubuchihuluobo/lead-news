package com.surge.model.admin.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class AdminChannelVO {

    private Integer id;

    private String name;

    private String description;

    private Boolean is_default;

    private Boolean status;

    private Short ord;

    private Date createdTime;

}
