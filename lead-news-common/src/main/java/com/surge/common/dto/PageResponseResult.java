package com.surge.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageResponseResult<T> extends ResponseResult<Object> implements Serializable {

    private Long currentPage;

    private Long size;

    private Long total;

    public PageResponseResult() {
    }

    public PageResponseResult(Long currentPage, Long size, Long total) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
    }

    public PageResponseResult(Long currentPage, Long size, Long total, T data) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
        super.setData(data);
    }

}
