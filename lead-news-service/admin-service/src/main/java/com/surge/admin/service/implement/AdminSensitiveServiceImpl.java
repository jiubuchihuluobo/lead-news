package com.surge.admin.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.admin.dto.AdminSensitiveDTO;
import com.surge.admin.mapper.AdminSensitiveMapper;
import com.surge.admin.pojo.AdminSensitive;
import com.surge.admin.service.AdminSensitiveService;
import com.surge.admin.vo.AdminSensitiveVO;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminSensitiveServiceImpl extends ServiceImpl<AdminSensitiveMapper, AdminSensitive> implements AdminSensitiveService {

    private final AdminSensitiveMapper adminSensitiveMapper;

    public AdminSensitiveServiceImpl(AdminSensitiveMapper adminSensitiveMapper) {
        this.adminSensitiveMapper = adminSensitiveMapper;
    }

    private AdminSensitiveVO convertAdminSensitiveToAdminSensitiveVO(AdminSensitive adminSensitive) {
        AdminSensitiveVO adminSensitiveVO = new AdminSensitiveVO();
        BeanUtils.copyProperties(adminSensitive, adminSensitiveVO);
        return adminSensitiveVO;
    }

    private AdminSensitive convertAdminSensitiveDTOToAdminSensitive(AdminSensitiveDTO adminSensitiveDTO) {
        AdminSensitive adminSensitive = new AdminSensitive();
        BeanUtils.copyProperties(adminSensitiveDTO, adminSensitive);
        adminSensitive.setCreatedTime(new Date());
        return adminSensitive;
    }

    @Override
    public PageResponseResult<Object> search(AdminSensitiveDTO dto) {
        LambdaQueryWrapper<AdminSensitive> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtil.isBlank(dto.getSensitives())) {
            queryWrapper.like(AdminSensitive::getSensitives, dto.getSensitives());
        }
        IPage<AdminSensitive> iPage = new Page<>(dto.getPage(), dto.getSize());
        IPage<AdminSensitive> pageResult = this.adminSensitiveMapper.selectPage(iPage, queryWrapper);
        List<AdminSensitiveVO> adminSensitiveVOList = pageResult.getRecords().stream().map(this::convertAdminSensitiveToAdminSensitiveVO).toList();
        return new PageResponseResult<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), adminSensitiveVOList);
    }

    @Override
    public ResponseResult<Object> change(AdminSensitiveDTO dto) {
        AdminSensitive adminSensitive = this.convertAdminSensitiveDTOToAdminSensitive(dto);
        this.updateById(adminSensitive);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> add(AdminSensitiveDTO dto) {
        AdminSensitive adminSensitive = this.convertAdminSensitiveDTOToAdminSensitive(dto);
        this.save(adminSensitive);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> delete(Integer id) {
        this.removeById(id);
        return ResponseResult.okResult();
    }

}
