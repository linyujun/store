package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author songsir
* @date 2023-04-20
*/
@Data
@TableName(value = "kz_store_order", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城订单对象", description = "商城订单表")
public class StoreOrderGroupEntity extends StoreOrderEntity{

    @TableField(value = "count(id)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Long total;
}
