package com.surge.media.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.common.dto.ResponseResult;
import com.surge.media.mapper.MediaUserMapper;
import com.surge.media.service.MediaUserService;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaUserServiceImpl extends ServiceImpl<MediaUserMapper, MediaUser> implements MediaUserService {

    private final MediaUserMapper mediaUserMapper;

    @Autowired
    public MediaUserServiceImpl(MediaUserMapper mediaUserMapper) {
        this.mediaUserMapper = mediaUserMapper;
    }

    private MediaUser convertMediaUserDTOToMediaUser(MediaUserDTO mediaUserDTO) {
        MediaUser mediaUser = new MediaUser();
        BeanUtils.copyProperties(mediaUserDTO, mediaUser);
        return mediaUser;
    }

    @Override
    public ResponseResult<MediaUser> findByName(String name) {
        MediaUser mediaUser = this.getOne(Wrappers.<MediaUser>lambdaQuery().eq(MediaUser::getName, name));
        return ResponseResult.okResult(mediaUser);
    }

    @Override
    public ResponseResult<MediaUser> add(MediaUserDTO dto) {
        MediaUser mediaUser = this.convertMediaUserDTOToMediaUser(dto);
        this.save(mediaUser);
        return ResponseResult.okResult(mediaUser);
    }

}
