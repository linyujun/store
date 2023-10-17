package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mtstore.server.beans.enums.CartScopeEnum;
import com.mtstore.server.beans.mapper.ProductDetailMapper;
import com.mtstore.server.beans.mapper.StoreProductMapper;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-19
*/
@Data
@TableName(value = "kz_store_cart", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城购物车对象", description = "商城购物车表")
public class StoreCartEntity extends BaseEntity{

    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    private Integer productId;

    private Integer storeId;

    private CartScopeEnum scope;

    @TableField(exist = false)
    @OneToOne(from = "productId", to = "product", clazz = StoreProductMapper.class, method = "selectById")
    private ProductEntity product;

    private Integer productDetailId;

    @ApiModelProperty(value = "拼团活动id")
    private Integer combinationId;

    @ApiModelProperty(value = "加团的id")
    private Integer combinationLogId;

    @ApiModelProperty(value = "砍价活动id")
    private Integer bargainId;

    @ApiModelProperty(value = "帮砍活动id")
    private Integer bargainLogId;

    @TableField(exist = false)
    @OneToOne(from = "productDetailId", to = "productDetail", clazz = ProductDetailMapper.class, method = "selectById")
    private ProductDetailEntity productDetail;

    private Integer cartNum;

    //用户直接购买
    private Boolean isHidden;
}
