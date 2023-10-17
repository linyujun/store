package com.mtstore.server.beans.dto.mall.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author songsir
* @date 2023-04-11
*/
@Data
@Accessors(chain = true)
public class ActivityProductDto {

    @ApiModelProperty("商品id")
    @NotNull(message = "商品id必填")
    private Integer productId;

    @ApiModelProperty("返佣方式")
    private String rebateType;

    @ApiModelProperty("多规格详情")
    @NotNull(message = "多规格详情必填")
    private List<ProductDetailDto> details;

}
