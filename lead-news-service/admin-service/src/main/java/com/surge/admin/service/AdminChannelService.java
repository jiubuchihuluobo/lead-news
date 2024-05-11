package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.admin.dto.AdminChannelDTO;
import com.surge.admin.pojo.AdminChannel;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;


public interface AdminChannelService extends IService<AdminChannel> {

    PageResponseResult<Object> search(AdminChannelDTO dto);

    ResponseResult<Object> change(AdminChannelDTO dto);

}
