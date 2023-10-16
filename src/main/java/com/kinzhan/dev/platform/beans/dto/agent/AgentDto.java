package com.kinzhan.dev.platform.beans.dto.agent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@ApiModel(value = "代理商实体", description = "代理商请求对象")
public class AgentDto extends BaseDto {

    private Integer parentId;

    private Integer userId;

    @NotBlank(message = "简称必填")
    private String shortName;

    @NotBlank(message = "名称必填")
    private String agentName;

    @NotBlank(message = "付费类型必填")
    private String payType;

    @NotBlank(message = "机构组名必填")
    private String groupName;

    @NotBlank(message = "机构联系人必填")
    private String contactUser;

    private String[] address;

    @NotNull(message = "专属销售必填")
    private int[] saleIds;

    private String addressDetail;

    private Integer storeNum;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    private List<String> dateRange;

    private Integer userLimit;

    @NotBlank(message = "APP名称必填")
    private String appName;

    @NotBlank(message = "手机号必填")
    private String mobile;

    /**
     * TODO 解锁定时任务开发
     */
    @ApiModelProperty("解锁方式")
    private String unlockMethod = "week";

    @ApiModelProperty("解锁时间")
    private String unlockTime = "22";

    /**
     * TODO 过期不可见天数功能开发
     */
    @ApiModelProperty("是否开启有效期")
    private Boolean expiredLimit = false;

    @ApiModelProperty("过期天数")
    private Integer expiredDays = 180;

    private String level;

    private String userName;

    private String password;
}
