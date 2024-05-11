package com.surge.admin.vo;

import lombok.Data;

@Data
public class LoginVO {

    private AdminUserVO user;

    private String token;

}
