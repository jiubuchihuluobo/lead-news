package com.surge.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.surge.model.user.pojo.AppUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppUserMapper extends BaseMapper<AppUser> {
}
