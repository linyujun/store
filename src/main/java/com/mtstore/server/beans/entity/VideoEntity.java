package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.SysUserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-04-11
 */
@Getter
@Setter
@TableName(value = "kz_video", autoResultMap = true)
@ApiModel(value = "VideoEntity对象", description = "")
public class VideoEntity extends BaseEntity{

    private String uuid;

    private Integer directoryId;

    private String directoryName;

    @ApiModelProperty("标题")
    private String videoName;

    @ApiModelProperty("视频封面")
    private String coverImgUrl;

    @ApiModelProperty("视频")
    private String videoUrl;

    @ApiModelProperty("要点讲解")
    private String keypoint;

    @ApiModelProperty("星级")
    private Integer level;

    @ApiModelProperty("播放次数")
    private Integer visited;

    @ApiModelProperty("常见错误")
    private String errors;


    @ApiModelProperty("所属对象")
    private String target;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> teacherIds;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> groups;

    @ApiModelProperty("是否属于计划")
    private Boolean isUsed;

    @ApiModelProperty("舞种")
    private String danceType;

    @ApiModelProperty("归属")
    private String belongsTo;

    private Boolean isPermit;

    @ApiModelProperty("时长")
    private Integer duration;

    private String description;

    @ApiModelProperty("tags")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] tags;

    @TableField(exist = false)
    @OneToOne(from = "createUser", to = "createBy", clazz = SysUserMapper.class, method = "selectByCreateUser")
    private String createBy;
}
