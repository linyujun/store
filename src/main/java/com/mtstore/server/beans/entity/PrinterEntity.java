package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.time.*;

/**
* @author songsir
* 打印机对象
*/
@Data
@TableName(value = "kz_printer", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "打印机对象", description = "打印机表")
public class PrinterEntity extends BaseEntity{
    /** 所属门店 */
    @ApiModelProperty("所属门店")
    private Integer storeId;

    /** 打印机名称 */
    @ApiModelProperty("打印机名称")
    private String printerName;

    /** 打印份数 */
    @ApiModelProperty("打印份数")
    private Integer printNum;

//    @ApiModelProperty("打印模板id")
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

    /** 是否在线 */
    @ApiModelProperty("是否在线")
    private Boolean isOnline;

    /** 最近一次检测时间 */
    @ApiModelProperty("最近一次检测时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastCheckTime;

    /** 备注 */
    @ApiModelProperty("备注")
    private String description;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

}
