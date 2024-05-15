package com.surge.admin.controller;

import com.surge.admin.service.AdminUserService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.admin.dto.AdminUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/in")
    ResponseResult<Object> login(@RequestBody AdminUserDTO dto) {
        return this.adminUserService.login(dto);
    }

}
