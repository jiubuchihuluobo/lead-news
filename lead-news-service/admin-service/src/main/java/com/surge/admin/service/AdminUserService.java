package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.admin.dto.AdminUserDTO;
import com.surge.admin.pojo.AdminUser;
import com.surge.common.dto.ResponseResult;


public interface AdminUserService extends IService<AdminUser> {

    ResponseResult<Object> login(AdminUserDTO dto);

    void updateLoginTime(AdminUser adminUser);
}
