package com.mtstore.server.beans.dto.mall.product;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "商品搜素实体")
public class ProductQueryDto {

    @ApiModelProperty(value = "分类id")
    @Query
    private Integer categoryId;

    @ApiModelProperty(value = "商品id")
    @Query(type = Query.Type.IN, propName = "id")
    private List<Integer> productIds;

    @ApiModelProperty(value = "商品名称")
    @Query(type = Query.Type.INNER_LIKE)
    private String productName;

    @ApiModelProperty(value = "价格")
    @Query(type = Query.Type.BETWEEN)
    private List<BigDecimal> price;

    @ApiModelProperty(value = "推荐类型, 1->热卖,2->新品,4->精品,8->促销")
    @Query(type = Query.Type.APPLY_SQL, template = "JSON_contains(%s, '%d', '$')")
    private Integer sellStatus;

    @ApiModelProperty(value = "活动信息, SECKILL,CREDIT")
    @Query(type = Query.Type.APPLY_SQL, template = "JSON_contains(%s, '%s', '$')")
    private String activity;
}
