package com.surge.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUserVO {

    private Integer id;

    private String name;

    private String nickname;

    private String image;

    private String email;

    private Date loginTime;

    private Date createdTime;

}
