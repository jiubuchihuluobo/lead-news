package com.surge.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.surge.model.article.pojo.AppAuthor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppAuthorMapper extends BaseMapper<AppAuthor> {
}
