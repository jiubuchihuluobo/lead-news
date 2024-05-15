package com.surge.model.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AppUserRealNameVO {

    private Integer id;

    private Integer userId;

    private String name;

    private String idno;

    private String fontImage;

    private String backImage;

    private String holdImage;

    private String liveImage;

    private Short status;

    private String reason;

    private Date createdTime;

    private Date submittedTime;

    private Date updatedTime;

}
