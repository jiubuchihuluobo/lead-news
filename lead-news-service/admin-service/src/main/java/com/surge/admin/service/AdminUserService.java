package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.admin.dto.AdminUserDTO;
import com.surge.model.admin.pojo.AdminUser;


public interface AdminUserService extends IService<AdminUser> {

    ResponseResult<Object> login(AdminUserDTO dto);

    void updateLoginTime(AdminUser adminUser);
}
