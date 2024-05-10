package com.surge.admin.controller;

import com.surge.admin.dto.AdminChannelDTO;
import com.surge.admin.service.AdminChannelService;
import com.surge.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channel")
public class AdminChannelController {

    private final AdminChannelService adminChannelService;

    public AdminChannelController(AdminChannelService adminChannelService) {
        this.adminChannelService = adminChannelService;
    }

    @PostMapping("/list")
    ResponseResult<Object> search(@RequestBody AdminChannelDTO dto) {
        return this.adminChannelService.search(dto);
    }

}
