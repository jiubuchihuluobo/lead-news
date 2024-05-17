package com.surge.media.controller;

import com.surge.common.dto.ResponseResult;
import com.surge.media.service.MediaUserService;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class MediaUserController {

    private final MediaUserService mediaUserService;

    public MediaUserController(MediaUserService mediaUserService) {
        this.mediaUserService = mediaUserService;
    }

    @GetMapping("/findByName/{name}")
    public ResponseResult<MediaUser> findByName(@PathVariable String name) {
        return this.mediaUserService.findByName(name);
    }

    @PostMapping("/save")
    public ResponseResult<MediaUser> save(@RequestBody MediaUserDTO dto) {
        return this.mediaUserService.add(dto);
    }

}
