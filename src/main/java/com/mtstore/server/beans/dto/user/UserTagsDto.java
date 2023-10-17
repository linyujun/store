package com.mtstore.server.beans.dto.user;

import lombok.Data;
import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-26
*/
@Data
public class UserTagsDto extends BaseDto{
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
