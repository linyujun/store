package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderActivityDto;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderAddressInfoDto;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderDeliveryDto;
import com.kinzhan.dev.platform.beans.enums.CartScopeEnum;
import com.kinzhan.dev.platform.beans.enums.OrderEnum;
import com.kinzhan.dev.platform.beans.enums.PayTypeEnum;
import com.kinzhan.dev.platform.beans.enums.ShippingTypeEnum;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToMany;
import com.kinzhan.dev.platform.service.StoreOrderDetailService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.EnumTypeHandler;

import java.math.BigDecimal;
import java.util.List;

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
