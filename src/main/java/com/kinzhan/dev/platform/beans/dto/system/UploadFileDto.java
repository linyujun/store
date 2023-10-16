package com.kinzhan.dev.platform.beans.dto.system;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UploadFileDto extends BaseDto {

    @ApiModelProperty("文件名")
    @NotBlank(message = "原始文件名必填")
    private String fileName;

    @ApiModelProperty("文件的绝对路径，本地可以使用相对路径")
    @NotBlank(message = "上传路径必填")
    private String filePath;

    @ApiModelProperty("文件类型，如图片，视频等")
    private String fileType;

    @ApiModelProperty("目录id")
    @NotNull(message = "目录必填")
    private Integer directoryId;

    private String directory;

    @ApiModelProperty("文件hash")
    private String hashed;

    @ApiModelProperty("扩展字段")
    private String extra;
}
