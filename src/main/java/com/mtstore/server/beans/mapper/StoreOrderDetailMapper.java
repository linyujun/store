package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.dto.user.UserTotalDateVo;
import com.mtstore.server.beans.entity.StoreOrderDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author songsir
* @date 2023-04-20
*/
public interface StoreOrderDetailMapper extends BaseMapper<StoreOrderDetailEntity> {

    @Select("SELECT DATE(create_time) AS date,sum(cart_num) AS total\n" +
            "FROM kz_store_order_detail\n" +
            "WHERE  create_time > DATE_SUB(CURDATE(),INTERVAL 30 DAY) and create_time < DATE(now()) GROUP BY date;")
    List<UserTotalDateVo> groupByLast30days();
}
