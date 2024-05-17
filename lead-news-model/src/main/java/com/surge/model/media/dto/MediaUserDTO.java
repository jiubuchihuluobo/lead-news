package com.surge.model.media.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MediaUserDTO {

    private Integer id;

    private Integer appUserId;

    private String appAuthorId;

    private String name;

    private String password;

    private String salt;

    private String nickname;

    private String image;

    private String location;

    private String phone;

    private String status;

    private String email;

    private Short type;

    private Short score;

    private Date loginTime;

    private Date createdTime;

}
