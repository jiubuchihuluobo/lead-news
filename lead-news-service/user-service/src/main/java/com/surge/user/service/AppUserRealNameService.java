package com.surge.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.user.dto.AppUserRealNameDTO;
import com.surge.model.user.pojo.AppUserRealName;

public interface AppUserRealNameService extends IService<AppUserRealName> {

    ResponseResult<Object> search(AppUserRealNameDTO dto);

    ResponseResult<Object> verifyById(AppUserRealNameDTO dto);

}
