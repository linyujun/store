package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-05-26
*/
@Data
@TableName(value = "kz_user_tags", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户标签对象", description = "用户标签表")
public class UserTagsEntity extends BaseEntity{
    /** 用户标签 */
    @ApiModelProperty("用户标签")
    private String title;

    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 权益信息 */
    @ApiModelProperty("权益信息")
    private String description;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

}
