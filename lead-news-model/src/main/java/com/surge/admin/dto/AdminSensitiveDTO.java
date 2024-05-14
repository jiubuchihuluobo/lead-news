package com.surge.admin.dto;

import com.surge.common.dto.PageRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminSensitiveDTO extends PageRequestDTO {

    private Integer id;

    private String name;

    private String sensitives;

    private Date createdTime;

}

