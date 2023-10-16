package com.kinzhan.dev.platform.beans.dto.feedback;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-12-22
 */
@Data
@ApiModel(value = "反馈对象", description = "")
public class FeedbackDto extends BaseDto {

    @ApiModelProperty(value = "反馈内容", required = true)
    @NotBlank(message = "反馈内容必填")
    private String content;

    @ApiModelProperty("图片")
    private String[] imgUrls;

    private Integer status = 0;

    private String statusDesc = "未处理";
}
