package com.surge.admin.controller;

import com.surge.admin.dto.AdminUserDTO;
import com.surge.admin.service.LoginService;
import com.surge.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/in")
    ResponseResult<Map<String, Object>> login(@RequestBody AdminUserDTO dto) {
        return this.loginService.login(dto);
    }

}
