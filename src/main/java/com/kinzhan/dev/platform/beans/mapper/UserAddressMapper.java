package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.UserAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-04-19
*/
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {

    /**
     * 强制删除
     */
    @Delete("delete from kz_user_address where id = #{id} and uid = #{userId}")
    void forceDelete(Integer id, Integer userId);
}
