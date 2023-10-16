package com.kinzhan.dev.platform.beans.dto.printer;

import lombok.Data;
import java.time.*;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-15
*/
@Data
public class PrinterDto extends BaseDto{
    /** 所属门店 */
    @ApiModelProperty("所属门店")
    private Integer storeId;

    /** 打印机名称 */
    @ApiModelProperty("打印机名称")
    @NotBlank(message = "打印机名称必填")
    private String printerName;

    /** 打印份数 */
    @ApiModelProperty("打印份数")
    @NotNull(message = "打印份数必填")
    private Integer printNum;

//    @ApiModelProperty("打印模板id")
//    @NotNull(message = "打印模板id必填")
//    private Integer templateId;

    /** 打印机类型 */
    @ApiModelProperty("打印机类型")
    private String printerType;

    /** 序列哈 */
    @ApiModelProperty("序列哈")
    private String snId;

    /** 设备密钥 */
    @ApiModelProperty("设备密钥")
    private String snSecret;

    /** appId */
    @ApiModelProperty("appId")
    private String appKey;

    /** app密钥 */
    @ApiModelProperty("app密钥")
    private String appSecret;

    /** 打印机位置 */
    @ApiModelProperty("打印机位置")
    private String position;

    /** 备注 */
    @ApiModelProperty("备注")
    private String description;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;
}
