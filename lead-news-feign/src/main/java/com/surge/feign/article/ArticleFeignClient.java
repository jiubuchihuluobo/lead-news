package com.surge.feign.article;

import com.surge.common.dto.ResponseResult;
import com.surge.feign.config.FeignConfig;
import com.surge.feign.media.fallback.FeignFallback;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "lead-news-article", configuration = FeignConfig.class, fallback = FeignFallback.class)
public interface ArticleFeignClient {

    @GetMapping("/api/v1/author/findById/{id}")
    ResponseResult<AppAuthor> findById(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/author/save")
    ResponseResult<AppAuthor> add(@RequestBody AppAuthorDTO dto);

}
