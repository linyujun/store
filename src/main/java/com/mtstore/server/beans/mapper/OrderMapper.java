package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.order.OrderTotalPriceVo;
import com.mtstore.server.beans.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
public interface OrderMapper extends BaseMapper<OrderEntity> {

    @Select("SELECT * FROM kz_order ${ew.customSqlSegment}")
    Page<OrderEntity> getPage(Page<OrderEntity> page, @Param("ew") Wrapper<OrderEntity> wrapper);

    /**
     * 当天数据
     * @return
     */
    @Select("SELECT SUM(total) as total_result FROM kz_order WHERE DATE(create_time) = CURDATE();")
    BigDecimal getToday();

    /**
     * 昨天数据
     * @return
     */
    @Select("SELECT SUM(total) as total_result FROM kz_order WHERE DATE(create_time) = DATE_SUB(CURDATE(), INTERVAL 1 DAY);")
    BigDecimal getYesterday();


    /**
     * 本月数据
     * @return
     */
    @Select("SELECT SUM(total) as total_result FROM kz_order WHERE YEAR(create_time) = YEAR(CURDATE()) " +
            "AND MONTH(create_time) = MONTH(CURDATE());")
    BigDecimal getCurrentMonth();

    /**
     * 一周数据
     * @return
     */
    @Select("select DATE_FORMAT(create_time,'%Y-%m-%d') days,count(*) count from " +
            "(SELECT * FROM kz_order WHERE date_sub(curdate(), interval 7 day) <= date(create_time)) sm " +
            "group by days;")
    List<Map> getWeek();


    /**
     * 一个月数据
     * @return
     */
    @Select("select DATE_FORMAT(create_time,'%Y-%m-%d') days,count(*) count from " +
            "(SELECT * FROM kz_order WHERE date_sub(curdate(), interval 30 day) <= date(create_time)) sm " +
            "group by days;")
    List<Map> getMonth();


    @Select("SELECT DATE(create_time) AS date,\n" +
            "sum(total) AS total_price, \n" +
            "sum(if (biz_type = 1,total,0)) as product_price,\n" +
            "sum(if (biz_type = 2,total,0)) as recharge_price\n" +
            "            FROM kz_order\n" +
            "            WHERE  create_time > DATE_SUB(CURDATE(),INTERVAL 30 DAY) and create_time < DATE(now()) GROUP BY date;")
    List<OrderTotalPriceVo> groupByLast30days();

    /**
     * 平均客单价数据
     * @return
     */
    @Select("SELECT AVG(total) as total_result FROM kz_order WHERE biz_type = 1 and status = 1 and" +
            " create_time between #{startTime} and #{endTime};")
    BigDecimal getAveragePrice(LocalDateTime startTime, LocalDateTime endTime);
}
