package com.surge.feign.media;


import com.surge.common.dto.ResponseResult;
import com.surge.feign.config.FeignConfig;
import com.surge.feign.media.fallback.FeignFallback;
import com.surge.model.article.pojo.AppAuthor;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "lead-news-media", configuration = FeignConfig.class, fallback = FeignFallback.class)
public interface MediaFeignClient {

    @GetMapping("/api/v1/user/findBuName/{name}")
    ResponseResult<MediaUser> findByName(@PathVariable("name") String name);

    @PostMapping("/api/v1/user/save")
    ResponseResult<MediaUser> add(@RequestBody MediaUserDTO dto);

    @PostMapping("/api/v1/user/update")
    ResponseResult<AppAuthor> change(@RequestBody MediaUserDTO dto);

}
