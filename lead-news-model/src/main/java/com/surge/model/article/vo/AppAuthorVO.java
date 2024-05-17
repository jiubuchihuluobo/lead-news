package com.surge.model.article.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AppAuthorVO {

    private Integer id;

    private String name;

    private Short type;

    private Integer userId;

    private Date createdTime;

    private Integer wmUserId;

}
