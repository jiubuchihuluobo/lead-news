package com.surge.admin.vo;

import com.surge.common.dto.PageResponseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class AdminChannelVO extends PageResponseResult {

    private Integer id;

    private String name;

    private String description;

    private Boolean is_default;

    private Boolean status;

    private Short ord;

    private Date createdTime;

}
