package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2023-02-14
 */
@Getter
@Setter
@TableName(value = "kz_notice", autoResultMap = true)
@ApiModel(value = "NoticeEntity对象", description = "")
@Accessors(chain = true)
public class NoticeEntity extends BaseEntity {

    private String title;

    private String content;

    private Integer uid;

    @ApiModelProperty("图片地址")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] imgUrls;

    private String urlPath;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private Map arguments;

    @ApiModelProperty("是否已读")
    private Boolean isRead;
}
