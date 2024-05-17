package com.surge.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.surge.model.user.pojo.AppUser;
import com.surge.user.mapper.AppUserMapper;
import com.surge.user.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {

    @Override
    public AppUser findAppUserById(Integer id) {
        return null;
    }

}
