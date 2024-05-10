package com.surge.common.dto;

import lombok.Data;

@Data
public class PageRequestDTO {

    protected Long size;

    protected Long page;

    public void checkParam() {
        if (this.page == null || this.page <= 0) {
            setPage(1L);
        }
        if (this.size == null || this.size <= 0 || this.size > 100) {
            setSize(10L);
        }
    }

}
