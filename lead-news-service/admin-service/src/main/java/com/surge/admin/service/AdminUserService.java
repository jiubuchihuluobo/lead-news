package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.admin.dto.AdminUserDTO;
import com.surge.admin.pojo.AdminUser;
import com.surge.common.dto.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AdminUserService extends IService<AdminUser> {

    ResponseResult<Object> login(AdminUserDTO dto);

    void updateLoginTime(AdminUser adminUser);
}
