package com.kinzhan.dev.platform.beans.dto.mall.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.fastjson.JSONObject;
import com.kinzhan.dev.platform.beans.enums.OrderEnum;
import com.kinzhan.dev.platform.beans.enums.PayTypeEnum;
import com.kinzhan.dev.platform.beans.enums.ShippingTypeEnum;
import com.kinzhan.dev.platform.util.converter.PayStatusConverter;
import com.kinzhan.dev.platform.util.converter.PayTypeConverter;
import com.kinzhan.dev.platform.util.converter.ShippingTypeConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "订单导出对象", description = "订单")
public class OrderExportDto {
    @ColumnWidth(20)
    @ExcelProperty("订单id")
    private String orderId;

    @ColumnWidth(20)
    @ExcelProperty("下单时间")
    private LocalDateTime createTime;

    @ColumnWidth(50)
    @ExcelProperty("商品信息")
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String productInfo;

    /** 商品总量 */
    @ColumnWidth(20)
    @ExcelProperty("商品总量")
    private Integer totalNum;

    /** 订单总价 */
    @ColumnWidth(20)
    @ExcelProperty("订单总价")
    private BigDecimal totalPrice;

    /** 实付金额 */
    @ColumnWidth(20)
    @ExcelProperty("实付金额")
    private BigDecimal payPrice;

    /** 优惠券金额 */
    @ColumnWidth(20)
    @ExcelProperty("优惠券金额")
    private BigDecimal couponPrice;

    /** 邮费 */
    @ExcelProperty("运费")
    @ColumnWidth(20)
    private BigDecimal postagePrice;

    @ColumnWidth(20)
    @ExcelProperty("积分抵扣")
    private BigDecimal totalCredit;

    /** 配送方式 */
    @ColumnWidth(20)
    @ExcelProperty(value = "配送方式", converter = ShippingTypeConverter.class)
    private ShippingTypeEnum shippingType;

    @ColumnWidth(20)
    @ExcelProperty("自提时间")
    private String pickupTime;

    @ColumnWidth(20)
    @ExcelProperty("收件人")
    private String nickName;

    @ColumnWidth(20)
    @ExcelProperty("手机号")
    private String phone;

    @ColumnWidth(20)
    @ExcelProperty("省市区")
    private List<String> address;

    @ColumnWidth(20)
    @ExcelProperty("详细地址")
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String addressDetail;

    @ColumnWidth(20)
    @ExcelProperty("快递公司")
    private String deliveryCompany;

    @ColumnWidth(20)
    @ExcelProperty("快递单号")
    private String deliveryNo;

    /** 支付状态 */
    @ColumnWidth(20)
    @ExcelProperty(value = "支付状态", converter = PayStatusConverter.class)
    private Boolean isPaid;

    /** 支付方式 */
    @ColumnWidth(20)
    @ExcelProperty(value = "支付方式", converter = PayTypeConverter.class)
    private PayTypeEnum payType;

    /** 状态描述 */
    @ColumnWidth(20)
    @ExcelProperty("订单状态")
    private String statusDesc;

    /** 用户备注 */
    @ColumnWidth(20)
    @ExcelProperty("用户备注")
    private String userRemarks;

    @ColumnWidth(20)
    @ExcelProperty("商家备注")
    private String sellerRemarks;

//    /** 门店id */
//    @ColumnWidth(20)
//    @ExcelProperty("门店id")
//    private Integer storeId;
}
