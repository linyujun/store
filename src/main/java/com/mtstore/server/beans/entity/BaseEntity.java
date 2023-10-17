package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    public Integer id;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    public Integer createUser;

    @Builder.Default
    public Boolean enabled = true;

    @TableLogic
    @Builder.Default
    public Boolean isDelete = false;

    @TableField(fill = FieldFill.INSERT)
    private Integer tenantId;
}
