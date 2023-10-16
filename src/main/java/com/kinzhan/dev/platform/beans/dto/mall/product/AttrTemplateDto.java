package com.kinzhan.dev.platform.beans.dto.mall.product;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-14
*/
@Data
public class AttrTemplateDto extends BaseDto{
    /** 规格名称 */
    @ApiModelProperty("规格名称")
    private String attrKey;

    /** 规格值 */
    @ApiModelProperty("规格值")
    private JSONArray values;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

}
