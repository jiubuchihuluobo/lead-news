package com.surge.admin.dto;

import com.surge.common.dto.PageRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminChannelDTO extends PageRequestDTO {

    private Integer id;

    private String name;

    private String description;

    private Boolean status;

    private Short ord;

}
