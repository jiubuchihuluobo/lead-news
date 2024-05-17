package com.surge.feign.media.fallback;

import com.surge.common.dto.ResponseResult;
import com.surge.common.enums.HttpCodeEnum;
import com.surge.feign.media.MediaFeignClient;
import com.surge.model.article.pojo.AppAuthor;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignFallback implements FallbackFactory<MediaFeignClient> {

    @Override
    public MediaFeignClient create(Throwable throwable) {
        return new MediaFeignClient() {
            @Override
            public ResponseResult<MediaUser> findByName(String name) {
                log.error("参数: {}", name);
                log.error("自媒体 findByName 远程调用出错啦 ~~~ !!!! {} ", throwable.getMessage());
                return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR);
            }

            @Override
            public ResponseResult<MediaUser> add(MediaUserDTO dto) {
                log.error("参数: {}", dto);
                log.error("自媒体 add 远程调用出错啦 ~~~ !!!! {} ", throwable.getMessage());
                return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR);
            }

            @Override
            public ResponseResult<AppAuthor> change(MediaUserDTO dto) {
                log.error("参数: {}", dto);
                log.error("自媒体 change 远程调用出错啦 ~~~ !!!! {} ", throwable.getMessage());
                return ResponseResult.errorResult(HttpCodeEnum.SERVER_ERROR);
            }
        };
    }

}
