package com.surge.model.user.dto;

import com.surge.common.dto.PageRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserRealNameDTO extends PageRequestDTO {

    private Integer id;

    private Integer userId;

    private String name;

    private String idNumber;

    private String frontImage;

    private String backImage;

    private String holdImage;

    private String liveImage;

    private Short status;

    private String reason;

    private Date createdTime;

    private Date submittedTime;

    private Date updatedTime;

}
