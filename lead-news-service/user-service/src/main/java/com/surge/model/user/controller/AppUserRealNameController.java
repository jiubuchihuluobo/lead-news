package com.surge.model.user.controller;

import com.surge.common.dto.ResponseResult;
import com.surge.model.user.dto.AppUserRealNameDTO;
import com.surge.model.user.service.AppUserRealNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AppUserRealNameController {

    private final AppUserRealNameService appUserRealNameService;

    public AppUserRealNameController(AppUserRealNameService appUserRealNameService) {
        this.appUserRealNameService = appUserRealNameService;
    }

    @PostMapping("/list")
    public ResponseResult<Object> search(@RequestBody AppUserRealNameDTO dto) {
        return this.appUserRealNameService.search(dto);
    }

}
