package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import com.surge.model.admin.dto.AdminChannelDTO;
import com.surge.model.admin.pojo.AdminChannel;


public interface AdminChannelService extends IService<AdminChannel> {

    PageResponseResult<Object> search(AdminChannelDTO dto);

    ResponseResult<Object> change(AdminChannelDTO dto);

    ResponseResult<Object> add(AdminChannelDTO dto);

    ResponseResult<Object> delete(Integer id);

}
