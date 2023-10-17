package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.time.*;

/**
* @author songsir
* @date 2023-04-18
*/
@Data
@TableName(value = "kz_agent", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "代理商对象", description = "代理商表")
public class AgentEntity extends BaseEntity{
    /** 代理商名称 */
    @ApiModelProperty("代理商名称")
    private String agentName;

    /** 父级代理商 */
    @ApiModelProperty("父级代理商")
    private Integer parentId;

    /** 代理级别 */
    @ApiModelProperty("代理级别")
    private Integer level;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    private String contactPhone;

    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contactUser;

    /** 区域 */
    @ApiModelProperty("区域")
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expiredTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
