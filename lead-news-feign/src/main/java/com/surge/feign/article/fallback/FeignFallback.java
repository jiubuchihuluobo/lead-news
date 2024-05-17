package com.surge.feign.article.fallback;

import com.surge.common.dto.ResponseResult;
import com.surge.common.enums.HttpCodeEnum;
import com.surge.feign.article.ArticleFeignClient;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class FeignFallback implements FallbackFactory<ArticleFeignClient> {

    @Override
    public ArticleFeignClient create(Throwable throwable) {
        return new ArticleFeignClient() {
            @Override
            public ResponseResult<AppAuthor> findById(Integer userId) {
                log.error("参数 userId : {}", userId);
                log.error("ArticleFeign findByUserId 远程调用出错啦 ~~~ !!!! {} ", throwable.getMessage());
                return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR);
            }

            @Override
            public ResponseResult<AppAuthor> add(AppAuthorDTO dto) {
                log.error("参数 apAuthor: {}", dto);
                log.error("ArticleFeign add 远程调用出错啦 ~~~ !!!! {} ", throwable.getMessage());
                return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR);
            }
        };
    }
}
