package com.surge.admin.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.admin.mapper.AdminChannelMapper;
import com.surge.admin.service.AdminChannelService;
import com.surge.common.dto.PageResponseResult;
import com.surge.common.dto.ResponseResult;
import com.surge.model.admin.dto.AdminChannelDTO;
import com.surge.model.admin.pojo.AdminChannel;
import com.surge.model.admin.vo.AdminChannelVO;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminChannelServiceImpl extends ServiceImpl<AdminChannelMapper, AdminChannel> implements AdminChannelService {

    private final AdminChannelMapper adminChannelMapper;

    public AdminChannelServiceImpl(AdminChannelMapper adminChannelMapper) {
        this.adminChannelMapper = adminChannelMapper;
    }

    private AdminChannelVO convertAdminChannelToAdminChannelVO(AdminChannel adminChannel) {
        AdminChannelVO adminChannelVO = new AdminChannelVO();
        BeanUtils.copyProperties(adminChannel, adminChannelVO);
        return adminChannelVO;
    }

    private AdminChannel convertAdminChannelDTOToAdminChannel(AdminChannelDTO dto) {
        AdminChannel adminChannel = new AdminChannel();
        BeanUtils.copyProperties(dto, adminChannel);
        return adminChannel;
    }

    @Override
    public PageResponseResult<Object> search(AdminChannelDTO dto) {
        // 使用LambdaQueryWrapper而非QueryWrapper
        LambdaQueryWrapper<AdminChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 动态添加条件
        if (!StringUtil.isBlank(dto.getName())) {
            lambdaQueryWrapper.like(AdminChannel::getName, dto.getName());
        }
        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(AdminChannel::getStatus, dto.getStatus());
        }

        // 分页
        IPage<AdminChannel> iPage = new Page<>(dto.getPage(), dto.getSize());
        IPage<AdminChannel> pageResult = this.adminChannelMapper.selectPage(iPage, lambdaQueryWrapper);

        // 转化为VO
        List<AdminChannelVO> adminChannelVreOList = pageResult.getRecords().stream().map(this::convertAdminChannelToAdminChannelVO).toList();
        return new PageResponseResult<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal(), adminChannelVreOList);
    }

    @Override
    public ResponseResult<Object> change(AdminChannelDTO dto) {
        AdminChannel adminChannel = this.convertAdminChannelDTOToAdminChannel(dto);
        this.adminChannelMapper.updateById(adminChannel);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> add(AdminChannelDTO dto) {
        AdminChannel adminChannel = this.convertAdminChannelDTOToAdminChannel(dto);
        this.save(adminChannel);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> delete(Integer id) {
        this.removeById(id);
        return ResponseResult.okResult();
    }

}
