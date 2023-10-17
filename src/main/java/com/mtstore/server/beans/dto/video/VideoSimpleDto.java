package com.mtstore.server.beans.dto.video;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoSimpleDto{
    @ApiModelProperty("标题")
    private String videoName;

    @ApiModelProperty("视频封面")
    private String coverImgUrl;

    @ApiModelProperty("视频")
    private String videoUrl;

    @ApiModelProperty("难度等级")
    private Integer level;

    @ApiModelProperty("归属")
    private String belongsTo;

    @ApiModelProperty("舞种")
    private String danceType;

    @ApiModelProperty("时长")
    private Integer duration;
}
