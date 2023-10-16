package com.kinzhan.dev.platform.beans.dto.video;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class VideoDto extends BaseDto {
    @ApiModelProperty("目录")
    @NotNull(message = "目录必选")
    private Integer directoryId;

    private String directoryName;

    private List<Integer> teacherIds;

    private List<String> groups;

    @ApiModelProperty("标题")
    @NotBlank(message = "视频标题必填")
    private String videoName;

    @ApiModelProperty("视频封面")
    private String coverImgUrl;

    @ApiModelProperty("视频")
    @NotBlank(message = "必须上传视频")
    private String videoUrl;

    @ApiModelProperty("要点讲解")
    private String keypoint;

    @ApiModelProperty("难度等级")
    @NotNull(message = "视频星级必填")
    private Integer level;

    @ApiModelProperty("常见错误")
    private String errors;

    @ApiModelProperty("是否属于计划")
    private Boolean isUsed;

    private Boolean isPermit = false;

    @ApiModelProperty("归属")
    @NotBlank(message = "归属必填")
    private String belongsTo;

    @ApiModelProperty("舞种")
    @NotBlank(message = "舞种必填")
    private String danceType;

    private String description;

    private String[] tags;

    @ApiModelProperty("时长")
    private Integer duration;
}
