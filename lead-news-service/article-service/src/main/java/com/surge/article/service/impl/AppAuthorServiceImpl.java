package com.surge.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.article.mapper.AppAuthorMapper;
import com.surge.article.service.AppAuthorService;
import com.surge.common.dto.ResponseResult;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AppAuthorServiceImpl extends ServiceImpl<AppAuthorMapper, AppAuthor> implements AppAuthorService {

    private final AppAuthorMapper appAuthorMapper;

    public AppAuthorServiceImpl(AppAuthorMapper appAuthorMapper) {
        this.appAuthorMapper = appAuthorMapper;
    }

    private AppAuthor convertAppAuthorDTOToAppAuthor(AppAuthorDTO appAuthorDTO) {
        AppAuthor appAuthor = new AppAuthor();
        BeanUtils.copyProperties(appAuthorDTO, appAuthor);
        return appAuthor;
    }

    @Override
    public ResponseResult<AppAuthor> findById(Integer id) {
        AppAuthor appAuthor = this.getById(id);
        return ResponseResult.okResult(appAuthor);
    }

    @Override
    public ResponseResult<AppAuthor> add(AppAuthorDTO dto) {
        AppAuthor appAuthor = this.convertAppAuthorDTOToAppAuthor(dto);
        this.save(appAuthor);
        return ResponseResult.okResult(appAuthor);
    }

    @Override
    public ResponseResult<AppAuthor> change(AppAuthorDTO dto) {
        AppAuthor appAuthor = this.convertAppAuthorDTOToAppAuthor(dto);
        this.updateById(appAuthor);
        return ResponseResult.okResult();
    }

}
