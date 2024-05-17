package com.surge.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;

public interface AppAuthorService extends IService<AppAuthor> {

    ResponseResult<AppAuthor> findById(Integer id);

    ResponseResult<AppAuthor> add(AppAuthorDTO dto);

    ResponseResult<AppAuthor> change(AppAuthorDTO dto);

}
