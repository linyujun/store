package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 用户加入的计划
 * </p>
 *
 * @author songsir
 * @since 2022-04-12
 */
@Getter
@Setter
@TableName(value = "kz_user_follow", autoResultMap = true)
@ApiModel(value = "UserFollowEntity对象", description = "用户加入的计划")
public class UserFollowEntity extends BaseEntity{

    private Integer planId;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> finished;
}
