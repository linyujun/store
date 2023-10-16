package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
import com.kinzhan.dev.platform.beans.mapper.SysUserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户绑定的机构
 * </p>
 *
 * @author songsir
 * @since 2023-01-03
 */
@Getter
@Setter
@TableName(value = "kz_student", autoResultMap = true)
@ApiModel(value = "学生对象", description = "学生")
public class StudentEntity extends BaseEntity {

    private Integer uid;

    @ApiModelProperty("用户唯一id")
    private String uuid;

    private String phone;

    @ApiModelProperty("学生姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("学生头像")
    private String avatarUrl;

    @ApiModelProperty("地址位置")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] address;

    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty("极光推送id")
    private String pushId;

    @ApiModelProperty("机构备注信息")
    private String description;

    @TableField(exist = false)
    @OneToOne(from = "createUser", to = "createBy", clazz = SysUserMapper.class, method = "selectByCreateUser")
    private String createBy;
}
