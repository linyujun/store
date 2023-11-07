package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.dto.mall.product.AttrDto;
import com.mtstore.server.beans.mapper.CategoryMapper;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;
import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* spu
*/
@Data
@TableName(value = "kz_store_product", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "普通商品对象", description = "普通商品表")
public class ProductEntity extends BaseEntity{
    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 商品类型，如普通商品，虚拟商品 */
    @ApiModelProperty("商品类型，如普通商品，虚拟商品")
    private String productType;

    /** 商品简介 */
    @ApiModelProperty("商品简介")
    private String productInfo;

    /** 分类id */
    @ApiModelProperty("分类id")
    private Integer categoryId;

    /** 分类id */
    @ApiModelProperty("分类数组")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> categoryList;

    /** 显示图片 */
    @ApiModelProperty("显示图片")
    private String imgUrl;

    /** 图片列表 */
    @ApiModelProperty("图片列表")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> imgUrls;

    /** 视频链接 */
    @ApiModelProperty("视频链接")
    private String videoUrl;

    /** 单位 */
    @ApiModelProperty("单位")
    private String unitName;

    /** 价格 */
    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("所需积分")
    private BigDecimal credit;

    @ApiModelProperty("积分差价")
    private BigDecimal creditPrice;

    @ApiModelProperty("预计返佣")
    private BigDecimal rebatePrice;

    @ApiModelProperty("返佣方式")
    private String rebateType;

    /** 销量 */
    @ApiModelProperty("销量")
    private Integer sales;

    /** 属性集合 */
    @ApiModelProperty("销量")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<AttrDto> attrs;

    /** sku */
    @TableField(exist = false)
    private List<ProductDetailEntity> details;

    /** 详细描述 */
    @ApiModelProperty("详细描述")
    private String description;

    /** 商户id */
    @ApiModelProperty("商户id")
    private Integer merchantId;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

    /** 规格类型，如single, multiple */
    @ApiModelProperty("规格类型，如single, multiple")
    private String propType;

    /** 自编码id */
    @ApiModelProperty("自编码id")
    private String internalId;

    /** 条形码 */
    @ApiModelProperty("条形码")
    private String barCode;

    /** 关键词 */
    @ApiModelProperty("关键词")
    private String keyword;

    /** 标签 */
    @ApiModelProperty("标签")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> tags;

    /** 成本价 */
    @ApiModelProperty("成本价")
    private BigDecimal costPrice;

    /** 划线价 */
    @ApiModelProperty("划线价")
    private BigDecimal originalPrice;

    @ApiModelProperty("秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty("拼团价")
    private BigDecimal combinationPrice;

    @ApiModelProperty("砍价")
    private BigDecimal bargainPrice;

    /** 库存 */
    @ApiModelProperty("库存")
    private Integer stockNum;

    /** 快递，自提 */
    @ApiModelProperty("快递，自提")
    private String shippingType;

    /** 配送模板 */
    @ApiModelProperty("配送模板")
    private String shippingTemplate;

    /** 重量 */
    @ApiModelProperty("重量")
    private BigDecimal weight;

    @ApiModelProperty("商品对应服务")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> serviceIds;

    /** 体积 */
    @ApiModelProperty("体积")
    private BigDecimal volume;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 访问次数 */
    @ApiModelProperty("访问次数")
    private Integer visited;

    /** 状态，审核 */
    @ApiModelProperty("状态，审核")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @TableField(exist = false)
    @OneToOne(from = "categoryId", to = "categoryName", clazz = CategoryMapper.class, method = "selectNameById")
    private String categoryName;

    @ApiModelProperty("推荐类型")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> sellStatus;

    @ApiModelProperty("活动类型")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> activity;

    @ApiModelProperty("匹配优惠券")
    @TableField(exist = false)
    private List<CouponEntity> coupons;

    @ApiModelProperty("商城服务")
    @TableField(exist = false)
    private List<StoreServiceEntity> services;

    @ApiModelProperty("拼团活动信息")
    @TableField(exist = false)
    private StoreCombinationEntity combination;

    @ApiModelProperty("砍价活动信息")
    @TableField(exist = false)
    private StoreBargainEntity bargain;
}
