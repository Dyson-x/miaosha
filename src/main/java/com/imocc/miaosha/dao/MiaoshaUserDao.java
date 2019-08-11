package com.imocc.miaosha.dao;

import com.imocc.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Dyson
 * @date 2019/8/11 10:05
 */
@Mapper
public interface MiaoshaUserDao {
    @Select("select * from mioasha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);
}
