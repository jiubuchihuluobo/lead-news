package com.surge.admin.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.admin.dto.AdminUserDTO;
import com.surge.admin.mapper.AdminUserMapper;
import com.surge.admin.pojo.AdminUser;
import com.surge.admin.service.LoginService;
import com.surge.admin.vo.AdminUserVO;
import com.surge.common.dto.ResponseResult;
import com.surge.common.enums.HttpCodeEnum;
import com.surge.exception.RaiseException;
import com.surge.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class LoginServiceImplement extends ServiceImpl<AdminUserMapper, AdminUser> implements LoginService {

    private final AdminUserMapper adminUserMapper;

    public LoginServiceImplement(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    @Override
    public ResponseResult<Map<String, Object>> login(AdminUserDTO dto) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String decodePassword = dto.getPassword();

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUser::getName, dto.getName());

        AdminUser adminUser = this.getOne(queryWrapper);
        if (!passwordEncoder.matches(decodePassword, adminUser.getPassword())) {
            RaiseException.raise(HttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        String token = JwtUtil.buildToken(adminUser.getId().longValue());
        this.updateLoginTime(adminUser);
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, adminUserVO);
        return ResponseResult.okResult(Map.of("token", token, "user", adminUserVO));
    }

    @Override
    public void updateLoginTime(AdminUser adminUser) {
        adminUser.setLoginTime(new Date());
        this.updateById(adminUser);
    }

}
