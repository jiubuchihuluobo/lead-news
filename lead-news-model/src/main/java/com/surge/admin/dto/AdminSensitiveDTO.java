package com.surge.admin.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.surge.common.dto.PageRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminSensitiveDTO extends PageRequestDTO {

    private Integer id;

    @JsonAlias({"sensitives", "name"})
    private String sensitives;

    private Date createdTime;

}

