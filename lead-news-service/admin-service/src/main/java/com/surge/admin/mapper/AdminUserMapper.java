package com.surge.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.surge.model.admin.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
}
