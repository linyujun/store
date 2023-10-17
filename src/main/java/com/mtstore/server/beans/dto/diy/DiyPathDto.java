package com.mtstore.server.beans.dto.diy;

import lombok.Data;
import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-25
*/
@Data
public class DiyPathDto extends BaseDto{
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
