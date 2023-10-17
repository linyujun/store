package com.mtstore.server.beans.mapper;

import com.mtstore.server.beans.dto.order.OrderStatusVo;
import com.mtstore.server.beans.dto.order.OrderTotalDateVo;
import com.mtstore.server.beans.dto.order.OrderTotalHourVo;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author songsir
* @date 2023-04-20
*/
public interface StoreOrderMapper extends BaseMapper<StoreOrderEntity> {


    @Select("select status,status_desc,count(id) as total from kz_store_order where uid = #{userId} group by status")
    List<OrderStatusVo> findOrderStatusByUserId(Integer userId);

    /**
     * 当天分时段数据
     * @return
     */
    @Select("SELECT DATE_FORMAT(create_time, '%H:00') AS hour,COUNT(*) AS total, sum(pay_price) AS total_price \n" +
            "FROM kz_store_order\n" +
            "WHERE status > 1 and DATE(create_time) = CURDATE() GROUP BY hour;")
    List<OrderTotalHourVo> groupByHour();


    /**
     * 一周数据
     * @return
     */
    @Select("SELECT DAYNAME(create_time) AS date,COUNT(*) AS total, sum(pay_price) AS total_price \n" +
            "FROM kz_store_order\n" +
            "WHERE status > 1 and date_sub(curdate(), interval 7 day) <= date(create_time) GROUP BY date;")
    List<OrderTotalDateVo> groupByWeek();



    /**
     * 最近30天
     * @return
     */
    @Select("SELECT DATE(create_time) AS date,COUNT(*) AS total, sum(pay_price) AS total_price \n" +
            "FROM kz_store_order\n" +
            "WHERE status > 1 and  create_time > DATE_SUB(CURDATE(),INTERVAL 30 DAY) and create_time < DATE(now()) GROUP BY date;")
    List<OrderTotalDateVo> last30days();


    /**
     * 当月数据
     * @return
     */
    @Select("SELECT DATE(create_time) AS date,COUNT(*) AS total, sum(pay_price) AS total_price \n" +
            "FROM kz_store_order\n" +
            "WHERE status > 1 and  YEAR(create_time) = YEAR(CURDATE()) AND MONTH(create_time) = MONTH(CURDATE()) GROUP BY date;")
    List<OrderTotalDateVo> groupByMonth();


    /**
     * 当年数据
     * @return
     */
    @Select("SELECT MONTH(create_time) AS date,COUNT(*) AS total, sum(pay_price) AS total_price \n" +
            "FROM kz_store_order\n" +
            "WHERE status > 1 and  YEAR(create_time) = YEAR(CURDATE()) GROUP BY date;")
    List<OrderTotalDateVo> groupByYear();
}
