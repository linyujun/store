package com.mtstore.server.beans.dto.system;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

@Data
public class SysRoleDto extends BaseDto {
    private String roleName;
    private Boolean isSystem;
    private Boolean isHidden = false;
    private String description;
    private String dataScope;
}
