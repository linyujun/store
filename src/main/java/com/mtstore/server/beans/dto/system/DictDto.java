package com.mtstore.server.beans.dto.system;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Data
public class DictDto extends BaseDto {
    @NotBlank(message = "字典名必填")
    String dictName;

    @NotBlank(message = "字典值必填")
    String dictValue;

    @NotNull(message = "父级必选")
    Integer parentId;

    @NotNull(message = "排序必填")
    Integer sort;

    String[] extra;
}
