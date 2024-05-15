package com.surge.admin.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.admin.mapper.AdminUserMapper;
import com.surge.admin.service.AdminUserService;
import com.surge.common.dto.ResponseResult;
import com.surge.common.enums.HttpCodeEnum;
import com.surge.exception.RaiseException;
import com.surge.model.admin.dto.AdminUserDTO;
import com.surge.model.admin.pojo.AdminUser;
import com.surge.model.admin.vo.AdminUserVO;
import com.surge.model.admin.vo.LoginVO;
import com.surge.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    private AdminUserVO convertAdminUserToAdminUserVO(AdminUser adminUser) {
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, adminUserVO);
        return adminUserVO;
    }

    private LoginVO convertAdminUserVOToLoginVO(AdminUserVO adminUserVO, String token) {
        LoginVO loginVO = new LoginVO();
        loginVO.setUser(adminUserVO);
        loginVO.setToken(token);
        return loginVO;
    }

    @Override
    public ResponseResult<Object> login(AdminUserDTO dto) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUser::getName, dto.getName());
        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String decodePassword = dto.getPassword();
        if (!passwordEncoder.matches(decodePassword, adminUser.getPassword())) {
            RaiseException.raise(HttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        String token = JwtUtil.buildToken(adminUser.getId().longValue());
        this.updateLoginTime(adminUser);

        AdminUserVO adminUserVO = this.convertAdminUserToAdminUserVO(adminUser);
        LoginVO loginVO = this.convertAdminUserVOToLoginVO(adminUserVO, token);
        return ResponseResult.okResult(loginVO);
    }

    @Override
    public void updateLoginTime(AdminUser adminUser) {
        adminUser.setLoginTime(new Date());
        adminUserMapper.updateById(adminUser);
    }

}
