package com.mtstore.server.beans.enums;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public final class BillEnum {
    //账单类型，余额
    public final static String BILL_CATEGORY_BALANCE = "BALANCE";
    //账单类型，积分
    public final static String BILL_CATEGORY_CREDIT = "CREDIT";

    //账单操作类型，邀请用户
    public final static String BILL_ACTION_INVITE_USER = "INVITE_USER";
    //账单操作类型，购买商品
    public final static String BILL_ACTION_BUY_PRODUCT = "BUY_PRODUCT";
    //账单操作类型，用户提现
    public final static String BILL_ACTION_WITHDRAW = "WITHDRAW";
    //账单操作类型，签到
    public final static String BILL_ACTION_CHECK_IN = "CHECK_IN";
    //发布动态
    public final static String BILL_ACTION_ISSUE = "ISSUE";
    //用户反馈
    public final static String BILL_ACTION_FEEDBACK = "FEEDBACK";
    //管理员操作
    public final static String BILL_ACTION_SYSTEM_USER = "SYSTEM_USER_HANDLE";

    //账单操作类型，用户首次登录
    public final static String BILL_ACTION_FIRST_LOGIN = "FIRST_LOGIN";
    //账单操作类型，实名认证
    public final static String BILL_ACTION_REAL_NAME = "REAL_NAME";
    //账单操作类型，充值
    public final static String BILL_ACTION_RECHARGE = "RECHARGE";
    //账单操作类型，积分兑换商品
    public final static String BILL_ACTION_EXCHANGE = "EXCHANGE";
    //账单操作类型，开通VIP
    public final static String BILL_ACTION_VIP = "VIP";
    //佣金类型，商品返佣
    public final static String BILL_ACTION_BUY_PRODUCT_BROKERAGE = "BUY_PRODUCT_BROKERAGE";
    //佣金类型，佣金提取到余额
    public final static String BILL_ACTION_WITHDRAW_BROKERAGE = "WITHDRAW_BROKERAGE";


    public final static Map<String, String> BILL_ACTION_MAP = new HashMap<String, String>() {{
        put(BILL_ACTION_INVITE_USER, "邀请用户奖励");
        put(BILL_ACTION_BUY_PRODUCT, "消费：购买商品");
        put(BILL_ACTION_WITHDRAW, "提现：用户提现");
        put(BILL_ACTION_CHECK_IN, "签到");
        put(BILL_ACTION_RECHARGE, "充值");
        put(BILL_ACTION_VIP, "开通VIP");
        put(BILL_ACTION_FIRST_LOGIN, "用户注册");
        put(BILL_ACTION_REAL_NAME, "实名认证");
        put(BILL_ACTION_EXCHANGE, "积分兑换商品");
        put(BILL_ACTION_ISSUE, "发布动态");
        put(BILL_ACTION_SYSTEM_USER,  "管理员操作");
        put(BILL_ACTION_FEEDBACK, "用户反馈");
        put(BILL_ACTION_BUY_PRODUCT_BROKERAGE, "分销商品返佣");
        put(BILL_ACTION_WITHDRAW_BROKERAGE, "佣金提取到余额");
    }};
    //账单方向，增加
    public final static String BILL_DIRECTION_INCR = "+";
    //账单方向，减少
    public final static String BILL_DIRECTION_DECREASE = "-";
    //账单方向，最终
    public final static String BILL_DIRECTION_LASt = "0";
}
