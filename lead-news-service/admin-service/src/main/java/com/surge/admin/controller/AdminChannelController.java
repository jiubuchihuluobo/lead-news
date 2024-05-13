package com.surge.admin.controller;

import com.surge.admin.dto.AdminChannelDTO;
import com.surge.admin.service.AdminChannelService;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class AdminChannelController {

    private final AdminChannelService adminChannelService;

    public AdminChannelController(AdminChannelService adminChannelService) {
        this.adminChannelService = adminChannelService;
    }

    @PostMapping("/list")
    PageResponseResult<Object> search(@RequestBody AdminChannelDTO dto) {
        return this.adminChannelService.search(dto);
    }

    @PostMapping("/update")
    ResponseResult<Object> update(@RequestBody AdminChannelDTO dto) {
        return this.adminChannelService.change(dto);
    }

    @GetMapping("/del/{id}")
    ResponseResult<Object> delete(@PathVariable("id") Integer id) {
        return this.adminChannelService.delete(id);
    }

    @PostMapping("/save")
    ResponseResult<Object> add(@RequestBody AdminChannelDTO dto) {
        return this.adminChannelService.add(dto);
    }

}
