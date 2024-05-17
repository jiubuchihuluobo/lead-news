package com.surge.article.controller;

import com.surge.article.service.AppAuthorService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/author")
public class AppAuthorController {

    private final AppAuthorService appAuthorService;

    public AppAuthorController(AppAuthorService appAuthorService) {
        this.appAuthorService = appAuthorService;
    }

    @GetMapping("/findById/{id}")
    public ResponseResult<AppAuthor> findById(@PathVariable("id") Integer id) {
        return this.appAuthorService.findById(id);
    }

    @PostMapping("/save")
    public ResponseResult<AppAuthor> add(@RequestBody AppAuthorDTO dto) {
        return this.appAuthorService.add(dto);
    }

    @PostMapping("/update")
    public ResponseResult<AppAuthor> change(@RequestBody AppAuthorDTO dto) {
        return this.appAuthorService.change(dto);
    }

}
