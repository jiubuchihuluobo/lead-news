package com.surge.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.surge.model.user.pojo.AppUser;

public interface AppUserService extends IService<AppUser> {

    AppUser findAppUserById(Integer id);

}
