package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单评论表
 * </p>
 *
 * @author songsir
 * @since 2022-04-06
 */
@Getter
@Setter
@TableName("kz_comment")
@ApiModel(value = "CommentEntity对象", description = "订单评论表")
public class CommentEntity extends BaseEntity{

    @ApiModelProperty("如果有回复")
    private Integer parentId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    private Integer orderId;

    @ApiModelProperty("骑手态度评分，星级")
    private Integer riderStars;

    @ApiModelProperty("送达时间评分，星级")
    private Integer timeStars;

    @ApiModelProperty("服务体验评分，星级")
    private Integer serviceStars;

    private String content;
}
