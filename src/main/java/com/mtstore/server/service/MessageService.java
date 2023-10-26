package com.mtstore.server.service;

import java.math.BigDecimal;

public interface MessageService {

    void sendMessage();

    void balanceNotice(String openId, BigDecimal totalAmount);

    void rechargeSuccess(String idNo , String openId, BigDecimal total, String orderNo);
}
