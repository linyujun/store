package com.mtstore.server.beans.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-17
*/
@Data
@TableName(value = "kz_diy_page", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "DIY页面对象", description = "DIY页面表")
public class DiyPageEntity extends BaseEntity{
    /** 页面唯一id */
    @ApiModelProperty("页面唯一id")
    private String uuid;

    /** 页面标题 */
    @ApiModelProperty("页面标题")
    private String title;

    /** 备注信息 */
    @ApiModelProperty("备注信息")
    private String description;

    /** 页面类型，比如首页，分类，自定义，个人中心等等 */
    @ApiModelProperty("页面类型，比如首页，分类，自定义，个人中心等等")
    private String pageType;

    /** 跳转链接 */
    @ApiModelProperty("跳转链接")
    private String pagePath;

    /** 页面数据 */
    @ApiModelProperty("页面数据")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray pageData;

    /** 属性，备用 */
    @ApiModelProperty("属性，备用")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject attrData;

    /** 扩展等，备用 */
    @ApiModelProperty("扩展等，备用")
    private String extraData;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 是否默认 */
    @ApiModelProperty("是否默认")
    private Boolean isDefault;

}
