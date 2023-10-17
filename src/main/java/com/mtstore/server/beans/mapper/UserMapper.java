package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.dto.user.UserTotalDateVo;
import com.mtstore.server.beans.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ww
 * @date 2021/7/15
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("SELECT * FROM kz_user where user_name = #{name} limit 1")
    UserEntity selectByName(@Param("name") String userName);

    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT * FROM kz_user where phone = #{phone} limit 1")
    UserEntity selectByPhone(@Param("phone") String phone);

    @Select("SELECT nick_name FROM kz_user where id = #{id} limit 1")
    String selectNameById(@Param("id") Integer id);

    @Select("SELECT DATE(create_time) AS date,COUNT(*) AS total\n" +
            "FROM kz_user\n" +
            "WHERE YEAR(create_time) = YEAR(CURDATE()) AND MONTH(create_time) = MONTH(CURDATE()) GROUP BY date;")
    List<UserTotalDateVo> groupByLast30days();
}
