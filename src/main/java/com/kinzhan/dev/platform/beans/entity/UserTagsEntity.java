package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;

/**
* @author songsir
* @date 2023-05-26
*/
@Data
@TableName(value = "kz_user_tags", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户标签对象", description = "用户标签表")
public class UserTagsEntity extends BaseEntity{
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
