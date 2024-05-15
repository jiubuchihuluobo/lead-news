package com.surge.model.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import com.surge.model.user.dto.AppUserRealNameDTO;
import com.surge.model.user.mapper.AppUserRealNameMapper;
import com.surge.model.user.pojo.AppUserRealName;
import com.surge.model.user.service.AppUserRealNameService;
import com.surge.model.user.vo.AppUserRealNameVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserRealNameServiceImpl extends ServiceImpl<AppUserRealNameMapper, AppUserRealName> implements AppUserRealNameService {

    private final AppUserRealNameMapper appUserRealNameMapper;

    public AppUserRealNameServiceImpl(AppUserRealNameMapper appUserRealNameMapper) {
        this.appUserRealNameMapper = appUserRealNameMapper;
    }

    private AppUserRealNameVO convertAppUserRealNameToAppUserRealNameVO(AppUserRealName appUserRealName) {
        AppUserRealNameVO appUserRealNameVO = new AppUserRealNameVO();
        BeanUtils.copyProperties(appUserRealName, appUserRealNameVO);
        appUserRealNameVO.setIdno(appUserRealName.getIdNumber());
        appUserRealNameVO.setFontImage(appUserRealName.getFrontImage());
        return appUserRealNameVO;
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

}
