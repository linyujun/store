package com.mtstore.server.beans.dto.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PermissionDto {
    @NotNull(message = "角色必填")
    Integer roleId;

    @NotNull(message = "权限必选")
    List<Integer> permissions;
}
