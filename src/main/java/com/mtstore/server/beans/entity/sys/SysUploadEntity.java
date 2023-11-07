package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mtstore.server.beans.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author songsir
 * 系统上传路径
 */
@Getter
@Setter
@TableName("kz_sys_upload")
@ApiModel(value = "SysUploadEntity对象", description = "")
public class SysUploadEntity extends BaseEntity {

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("文件的绝对路径，本地可以使用相对路径")
    private String filePath;

    @ApiModelProperty("文件类型，如图片，视频等")
    private String fileType;

    @ApiModelProperty("目录id")
    private Integer directoryId;

    @ApiModelProperty("文件hash")
    private String hashed;

    @ApiModelProperty("扩展字段")
    private String extra;
}
