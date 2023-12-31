package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.entity.sys.SysTenantEntity;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.TenantMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author songsir
 * 意见反馈
 */
@Getter
@Setter
@TableName(value = "kz_feedback", autoResultMap = true)
@ApiModel(value = "FeedbackEntity对象", description = "")
public class FeedbackEntity extends BaseEntity {

    private String nickName;

    private String phone;

    private String role;

    @ApiModelProperty("反馈类型")
    private String mtype;

    @ApiModelProperty("留言内容")
    private String content;

    @ApiModelProperty("处理状态")
    private Integer status;

    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("处理结果")
    private String result;

    @ApiModelProperty("图片")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] imgUrls;

    @ApiModelProperty("是否已读")
    private Integer isRead;

    @TableField(exist = false)
    @OneToOne(from = "tenantId", to = "agent", clazz = TenantMapper.class, method = "selectById")
    private SysTenantEntity agent;
}
