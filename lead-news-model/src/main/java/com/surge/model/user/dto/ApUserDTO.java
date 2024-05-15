package com.surge.model.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApUserDTO {

    private Integer id;

    private String salt;

    private String name;

    private String password;

    private String phone;

    private String image;

    private Boolean sex;

    private Boolean isCertification;

    private Boolean isIdentityAuthentication;

    private Boolean status;

    private Boolean flag;

    private Date createdTime;

}
