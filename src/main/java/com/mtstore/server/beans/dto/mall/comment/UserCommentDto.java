package com.mtstore.server.beans.dto.mall.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author songsir
* @date 2023-05-17
*/
@Data
@ApiModel(value = "用户评价")
public class UserCommentDto{

    @ApiModelProperty(value = "评价id", required = true)
    @NotNull(message = "评价id必填")
    private Integer id;

    /** 1好评，2中评，3差评 */
    @ApiModelProperty(value = "1好评，2中评，3差评", required = true)
    @NotNull(message = "评价必填")
    private Integer level;

    /** 商品分数 */
    @ApiModelProperty(value = "商品分数", required = true)
    private Integer productLevel;

    /** 物流分数 */
    @ApiModelProperty(value = "物流分数", required = true)
    private Integer deliveryLevel;

    /** 服务分数 */
    @ApiModelProperty(value = "服务分数", required = true)
    private Integer serviceLevel;

    /** 评论内容 */
    @ApiModelProperty("评论内容")
    private String description;

    /** 评论图片 */
    @ApiModelProperty("评论图片")
    private List<String> imgUrls;

    private Integer status = 1;

    private String statusDesc = "已评价";
}
