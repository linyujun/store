package com.kinzhan.dev.platform.beans.dto.feedback;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-12-22
 */
@Data
@ApiModel(value = "答复对象", description = "")
public class ReplyDto {

    @NotNull(message = "反馈信息原始id必填")
    private Integer id;

    @NotNull(message = "处理状态必填")
    private Integer status;

    @NotBlank(message = "处理结果必填")
    private String result;
}
