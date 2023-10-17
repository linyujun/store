package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户收藏
 * </p>
 *
 * @author songsir
 * @since 2022-04-12
 */
@Getter
@Setter
@TableName(value = "kz_user_favorite", autoResultMap = true)
@ApiModel(value = "UserFavoriteEntity对象", description = "用户收藏")
public class UserFavoriteEntity extends BaseEntity{

    private Integer targetId;

    //支持，plan和video
    private String type;
}
