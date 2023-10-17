package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-25
*/
@Data
@TableName(value = "kz_diy_path", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "页面路径对象", description = "页面路径表")
public class DiyPathEntity extends BaseEntity{
    /** 页面名称 */
    @ApiModelProperty("页面名称")
    private String title;

    /** H5链接 */
    @ApiModelProperty("H5链接")
    private String h5Url;

    /** 小程序链接 */
    @ApiModelProperty("小程序链接")
    private String mpUrl;

    /** 页面类型 */
    @ApiModelProperty("页面类型")
    private String pageType;

    /** 备注 */
    @ApiModelProperty("备注")
    private String description;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
