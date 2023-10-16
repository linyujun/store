package com.kinzhan.dev.platform.beans.dto.mall.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.List;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
import lombok.experimental.Accessors;

/**
* @author songsir
* @date 2023-04-11
*/
@Data
@Accessors(chain = true)
public class ProductDto extends BaseDto{
    /** 商品名称 */
    @ApiModelProperty("商品名称")
    @NotBlank(message = "商品名称必填")
    private String productName;

    /** 商品类型，如普通商品，虚拟商品 */
    @ApiModelProperty("商品类型，如普通商品，虚拟商品")
    private String productType = "product";

    /** 商品简介 */
    @ApiModelProperty("商品简介")
    private String productInfo;

    /** 分类id */
    @ApiModelProperty("分类id")
    @NotNull(message = "分类必填")
    private Integer categoryId;

    /** 分类id */
    @ApiModelProperty("分类数组")
    private List<Integer> categoryList;

    /** 显示图片 */
    @ApiModelProperty("商品图必填")
    @NotBlank(message = "商品图必填")
    private String imgUrl;

    /** 图片列表 */
    @ApiModelProperty("图片列表")
    private List<String> imgUrls;

    /** 视频链接 */
    @ApiModelProperty("视频链接")
    private String videoUrl;

    /** 单位 */
    @ApiModelProperty("单位")
    private String unitName;

    /** 价格 */
    @ApiModelProperty("价格")
    private BigDecimal price = BigDecimal.ZERO;

    /** 销量 */
    @ApiModelProperty("销量")
    private Integer sales;

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

    @ApiModelProperty("多规格属性值")
    private List<AttrDto> attrs;

    @ApiModelProperty("多规格详情")
    private List<ProductDetailDto> details;

    @ApiModelProperty("商品对应服务")
    private List<Integer> serviceIds;

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
    private List<String> tags;

    /** 成本价 */
    @ApiModelProperty("成本价")
    private BigDecimal costPrice;

    /** 划线价 */
    @ApiModelProperty("划线价")
    private BigDecimal originalPrice;

    /** 库存 */
    @ApiModelProperty("库存")
    private Integer stockNum = 0;

    /** 快递，自提 */
    @ApiModelProperty("快递，自提")
    private String shippingType;

    /** 配送模板 */
    @ApiModelProperty("配送模板")
    private String shippingTemplate;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 重量 */
    @ApiModelProperty("重量")
    private BigDecimal weight;

    /** 体积 */
    @ApiModelProperty("体积")
    private BigDecimal volume;

    /** 访问次数 */
    @ApiModelProperty("访问次数")
    private Integer visited;

    /** 状态，审核 */
    @ApiModelProperty("状态，审核")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("推荐类型")
    private List<Integer> sellStatus;
}
