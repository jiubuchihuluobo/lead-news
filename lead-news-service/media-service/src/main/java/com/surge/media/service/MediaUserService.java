package com.surge.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;

public interface MediaUserService extends IService<MediaUser> {

    ResponseResult<MediaUser> findByName(String name);

    ResponseResult<MediaUser> add(MediaUserDTO dto);

}
