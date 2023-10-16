package com.kinzhan.dev.platform.beans.dto.system;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

@Data
public class SysRoleDto extends BaseDto {
    private String roleName;
    private Boolean isSystem;
    private Boolean isHidden = false;
    private String description;
    private String dataScope;
}
