package com.mtstore.server.beans.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UploadFileMoveDto {

    @ApiModelProperty("目录id")
    @NotNull(message = "目录必填")
    private Integer directoryId;

    @ApiModelProperty("文件列表id")
    @NotNull(message = "文件列表id")
    private List<Integer> fileIds;
}
