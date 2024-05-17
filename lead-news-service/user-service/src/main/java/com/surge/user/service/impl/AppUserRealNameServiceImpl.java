package com.surge.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import com.surge.feign.article.ArticleFeignClient;
import com.surge.feign.media.MediaFeignClient;
import com.surge.model.article.dto.AppAuthorDTO;
import com.surge.model.article.pojo.AppAuthor;
import com.surge.model.media.dto.MediaUserDTO;
import com.surge.model.media.pojo.MediaUser;
import com.surge.model.user.dto.AppUserRealNameDTO;
import com.surge.model.user.pojo.AppUser;
import com.surge.model.user.pojo.AppUserRealName;
import com.surge.model.user.vo.AppUserRealNameVO;
import com.surge.user.mapper.AppUserRealNameMapper;
import com.surge.user.service.AppUserRealNameService;
import com.surge.user.service.AppUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppUserRealNameServiceImpl extends ServiceImpl<AppUserRealNameMapper, AppUserRealName> implements AppUserRealNameService {

    private final AppUserRealNameMapper appUserRealNameMapper;

    private final AppUserService appUserService;

    private final MediaFeignClient mediaFeignClient;

    private final ArticleFeignClient articleFeignClient;

    @Autowired
    public AppUserRealNameServiceImpl(AppUserRealNameMapper appUserRealNameMapper, AppUserService appUserService, MediaFeignClient mediaFeignClient, ArticleFeignClient articleFeignClient) {
        this.appUserRealNameMapper = appUserRealNameMapper;
        this.appUserService = appUserService;
        this.mediaFeignClient = mediaFeignClient;
        this.articleFeignClient = articleFeignClient;
    }

    private AppUserRealNameVO convertAppUserRealNameToAppUserRealNameVO(AppUserRealName appUserRealName) {
        AppUserRealNameVO appUserRealNameVO = new AppUserRealNameVO();
        BeanUtils.copyProperties(appUserRealName, appUserRealNameVO);
        appUserRealNameVO.setIdno(appUserRealName.getIdNumber());
        appUserRealNameVO.setFontImage(appUserRealName.getFrontImage());
        return appUserRealNameVO;
    }

    private AppUserRealName convertAppUserRealNameDTOToAppUserRealName(AppUserRealNameDTO appUserRealNameDTO) {
        AppUserRealName appUserRealName = new AppUserRealName();
        BeanUtils.copyProperties(appUserRealNameDTO, appUserRealName);
        return appUserRealName;
    }

    private MediaUser createMediaUser(AppUser appUser) {
        MediaUserDTO mediaUserDTO = new MediaUserDTO();
        mediaUserDTO.setSalt(appUser.getSalt());
        mediaUserDTO.setAppUserId(appUser.getId());
        mediaUserDTO.setName(appUser.getName());
        mediaUserDTO.setPassword(appUser.getPassword());
        mediaUserDTO.setPhone(appUser.getPhone());
        mediaUserDTO.setImage(appUser.getImage());
        mediaUserDTO.setCreatedTime(new Date());
        return this.mediaFeignClient.add(mediaUserDTO).getData();
    }

    private AppAuthor createAppAuthor(MediaUser mediaUser) {
        AppAuthorDTO appAuthorDTO = new AppAuthorDTO();
        appAuthorDTO.setName(mediaUser.getName());
        appAuthorDTO.setType(Short.valueOf("2"));
        appAuthorDTO.setUserId(mediaUser.getAppUserId());
        appAuthorDTO.setWmUserId(mediaUser.getId());
        appAuthorDTO.setCreatedTime(new Date());
        return this.articleFeignClient.add(appAuthorDTO).getData();
    }

    @Override
    public ResponseResult<Object> search(AppUserRealNameDTO dto) {
        LambdaQueryWrapper<AppUserRealName> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (dto.getStatus() != null)
            lambdaQueryWrapper.eq(AppUserRealName::getStatus, dto.getStatus());
        IPage<AppUserRealName> iPage = new Page<>(dto.getPage(), dto.getSize());
        IPage<AppUserRealName> pageResult = this.appUserRealNameMapper.selectPage(iPage, lambdaQueryWrapper);
        List<AppUserRealNameVO> appUserRealNameVOList = pageResult.getRecords().stream().map(this::convertAppUserRealNameToAppUserRealNameVO).toList();
        return new PageResponseResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), appUserRealNameVOList);
    }

    @Override
    public ResponseResult<Object> verifyById(AppUserRealNameDTO dto) {
        AppUserRealName appUserRealName = this.getById(dto.getId());
        appUserRealName.setStatus(Short.valueOf("9"));
        AppUser appUser = this.appUserService.getById(appUserRealName.getUserId());
        MediaUser mediaUser = this.createMediaUser(appUser);
        AppAuthor appAuthor = this.createAppAuthor(mediaUser);
        mediaUser.setAppAuthorId(appAuthor.getId());
        MediaUserDTO mediaUserDTO = new MediaUserDTO();
        BeanUtils.copyProperties(mediaUser, mediaUserDTO);
        this.mediaFeignClient.change(mediaUserDTO);
        return null;
    }

}
