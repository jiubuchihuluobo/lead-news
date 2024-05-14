package com.surge.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.admin.dto.AdminSensitiveDTO;
import com.surge.admin.pojo.AdminSensitive;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;

public interface AdminSensitiveService extends IService<AdminSensitive> {

    PageResponseResult<Object> search(AdminSensitiveDTO dto);

    ResponseResult<Object> change(AdminSensitiveDTO dto);

    ResponseResult<Object> add(AdminSensitiveDTO dto);

    ResponseResult<Object> delete(Integer id);

}

