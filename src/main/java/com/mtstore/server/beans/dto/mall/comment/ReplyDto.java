package com.mtstore.server.beans.dto.mall.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-05-17
*/
@Data
public class ReplyDto{
    @NotNull(message = "id必填")
    public Integer id;

    /** 管理员回复内容 */
    @ApiModelProperty("管理员回复内容")
    @NotBlank(message = "答复内容必填")
    private String replyContent;

    /** 回复状态 */
    @ApiModelProperty("回复状态：0未回复1已回复")
    private Boolean isReply = true;
}
