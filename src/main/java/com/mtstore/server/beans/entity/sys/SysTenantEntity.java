package com.mtstore.server.beans.entity.sys;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtstore.server.beans.entity.StoreEntity;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.SysUserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author songsir
 * 代理商表
 */
@Data
@TableName(value = "kz_sys_tenant", autoResultMap = true)
@ApiModel(value = "机构对象", description = "机构表")
public class SysTenantEntity extends SysBaseEntity {

    private Integer parentId;

    @ApiModelProperty("依赖路径，如0-1-2")
    private String parentPath;

    private String agentName;

    private String shortName;

    private String groupName;

    private String mobile;

    private String contactUser;

    @ApiModelProperty("付费类型")
    private String payType;

    private String level;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private int[] saleIds;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] address;

    private String addressDetail;

    private String appName;

    /**
     * TODO 解锁定时任务开发
     */
    @ApiModelProperty("解锁方式")
    private String unlockMethod;

    @ApiModelProperty("解锁时间")
    private String unlockTime;

    /**
     * TODO 过期不可见天数功能开发
     */
    @ApiModelProperty("是否开启有效期")
    private Boolean expiredLimit;

    @ApiModelProperty("过期天数")
    private Integer expiredDays;

    @TableField(exist = false)
    private Integer storeNum;

    @ApiModelProperty("开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate startTime;

    @ApiModelProperty("结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endTime;

    @TableField(exist = false)
    private List<String> dateRange;

    public List<String> getDateRange() {
        List<String> resultList = new ArrayList<>();
        if (null != endTime) {
            resultList.add(DateUtil.format(startTime.atStartOfDay(), "yyyy-MM-dd"));
            resultList.add(DateUtil.format(endTime.atStartOfDay(), "yyyy-MM-dd"));
        }
        return resultList;
    }

    @TableField(exist = false,typeHandler = FastjsonTypeHandler.class)
    private List<StoreEntity> children;

    @TableField(exist = false)
    @OneToOne(from = "id", to = "uid", clazz = SysUserMapper.class, method = "findUserIdByTenantId")
    private Integer uid;
}
