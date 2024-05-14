package com.surge.admin.controller;

import com.surge.admin.dto.AdminSensitiveDTO;
import com.surge.admin.service.AdminSensitiveService;
import com.surge.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sensitive")
public class AdminSensitiveController {

    private final AdminSensitiveService adminSensitiveService;

    public AdminSensitiveController(AdminSensitiveService adminSensitiveService) {
        this.adminSensitiveService = adminSensitiveService;
    }

    @PostMapping("/list")
    public ResponseResult<Object> search(@RequestBody AdminSensitiveDTO dto) {
        return this.adminSensitiveService.search(dto);
    }

    @PostMapping("/update")
    ResponseResult<Object> update(@RequestBody AdminSensitiveDTO dto) {
        return this.adminSensitiveService.change(dto);
    }

    @DeleteMapping("/del/{id}")
    ResponseResult<Object> delete(@PathVariable("id") Integer id) {
        return this.adminSensitiveService.delete(id);
    }

    @PostMapping("/save")
    ResponseResult<Object> add(@RequestBody AdminSensitiveDTO dto) {
        return this.adminSensitiveService.add(dto);
    }

}
