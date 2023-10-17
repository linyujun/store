package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.OrderQueueEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
public interface OrderQueueMapper extends BaseMapper<OrderQueueEntity> {
    /**
     * 5分钟内的结果集
     * @return
     */
    @Select("SELECT * FROM kz_order_queue where is_sync < 1 AND create_time >= DATE_SUB(NOW(),INTERVAL 5 MINUTE)")
    List<OrderQueueEntity> getListInFiveMinute();


    /**
     * 5分钟外的结果集
     * @return
     */
    @Select("SELECT * FROM kz_order_queue where is_sync < 1 AND create_time < DATE_SUB(NOW(),INTERVAL 5 MINUTE)")
    List<OrderQueueEntity> getListAfterFiveMinute();
}
