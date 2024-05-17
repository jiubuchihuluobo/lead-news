package com.surge.model.article.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppAuthorDTO {

    private Integer id;

    private String name;

    private Short type;

    private Integer userId;

    private Date createdTime;

    private Integer wmUserId;

}
